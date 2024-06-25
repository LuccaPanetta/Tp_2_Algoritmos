package Tp_2_Algoritmos;

import javax.swing.JOptionPane;

public class Arbol{
	private Nodo Raiz;
	private static int CantNodos = 0;
	static int IndicesArray = 0 ;
	private ObjArbol DatoAux;

    public Arbol (){
    	Raiz = null;
    }

    public void insertar(ObjArbol x){
    	Raiz = insertar(Raiz, x);
    }

	private Nodo insertar(Nodo Raiz, ObjArbol b){
	if (Raiz == null){
		Raiz = new Nodo(null, b, null);
		CantNodos++;
	} else {
		if (b.compareTo(Raiz.getDato()) > 0){
			Raiz.setDer(insertar(Raiz.getDer(), b));
		} else{
			Raiz.setIzq(insertar(Raiz.getIzq(),b));
		}
		}
		return Raiz;
	}

	public ObjArbol[] preorden (){
		ObjArbol arrayInfo[] = new ObjArbol[CantNodos];
   	 	preorden(Raiz, arrayInfo);
		return arrayInfo;
    }

	private void preorden(Nodo Raiz, ObjArbol a[]){
		if (Raiz != null)
		{
			a[IndicesArray] = Raiz.getDato();
			IndicesArray++;
			preorden(Raiz.getIzq(), a);
			preorden(Raiz.getDer(), a);
		}	
	}

	public void inorden (){
   	 	inorden(Raiz,1);
    }

	private void inorden(Nodo Raiz, int h){
		if (Raiz != null){
			inorden(Raiz.getIzq(), h+1);
			for(int i = 1; i <= h; i++)
				System.out.print("    ");
			System.out.println(Raiz.getDato()+ " ");
			inorden(Raiz.getDer(), h+1);
		}	
	}

	public ObjArbol buscar(ObjArbol c){
        Nodo r = Raiz; 
        boolean encontrado = false;
        ObjArbol DatoBuscado = null;
        while (r != null && !encontrado){
            if (r.getDato().compareTo(c) == 0){
                encontrado= true;
                DatoBuscado = r.getDato();
            } else {
                if (r.getDato().compareTo(c) < 0){
                    r = r.getDer();
                } else {
                    r = r.getIzq();
                }
            }
        }
        return DatoBuscado;
   }

	public void eliminar(ObjArbol c){
		Nodo r = Raiz;
		boolean encontrado = false;
		while (r!= null && !encontrado ){
			if (r.getDato().compareTo(c) == 0){
				encontrado= true;
				r.getDato().setActivo(false);
				System.out.println("Se elimino el producto de código: " + r.getDato().getID());
			} else {
				if (r.getDato().compareTo(c) < 0){
					r = r.getDer();
				} else {
					r = r.getIzq();
				}
			}	
		}
		if (!encontrado){
			System.out.println("No se pudo eliminar el producto de Código: " + c.getID() + " debido a que no existe.");
		}	
	}

	public void ActualizarPorID(int ID, int cant, String Desc,  float precio, TablaRandomAccessFile RandomAccessFile) {
		CD a = new CD(ID, cant, Desc, precio);
		DatoAux = this.buscar(new ObjArbol(ID));
		if (DatoAux != null) {
			int pos = DatoAux.getDesplazamiento() / 32;
			RandomAccessFile.Actualizar((pos +1 ), a);
			this.crearArbol(RandomAccessFile);
		}
	}

	public void crearArbol(TablaRandomAccessFile RandomAccessFile){
			int n = 1;
			CD r = new CD();
			int Código;
			while (!RandomAccessFile.EndOfFile()){
				RandomAccessFile.get(n, r);
				Código = ((CD) r).getCódigo();
				DatoAux = new ObjArbol(Código);
				this.insertar(DatoAux);
				n++;
			}
			this.inorden();
	}

	public void consultarCDPorID(int ID, TablaRandomAccessFile RandomAccessFile){
		CD r = new CD();
		DatoAux = this.buscar(new ObjArbol(ID));
		if (DatoAux != null){
			int pos = DatoAux.getDesplazamiento() / 32;
			RandomAccessFile.get((pos+1), r);
			JOptionPane.showMessageDialog(null, "Producto: " + r, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "No se encontró ningun producto con la ID: " + ID, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void Insertar(TablaRandomAccessFile RandomAccessFile, CD prod){
		RandomAccessFile.Agregar(prod);
		DatoAux = new ObjArbol(prod.getCódigo());
		this.insertar(DatoAux);
		this.inorden();
		System.out.println("----------------------------------------------------------------------------------------------------------------");
	}

	public void eliminarLogico(int ID){
		DatoAux = new ObjArbol(ID);
		this.eliminar(DatoAux);
		this.inorden();
		System.out.println("----------------------------------------------------------------------------------------------------------------");
	}

}
