package Tp_2_Algoritmos;

public class Nodo {
     private ObjArbol Dato;
     private Nodo Izq, Der;
     
     public Nodo (Nodo a, ObjArbol b, Nodo c){
    	Izq = a;
		Dato = b;
    	Der = c;
     }
	
	public Nodo getIzq(){
		return Izq;
	}
	public void setIzq(Nodo Izq){
		this.Izq = Izq;
	}
	
	public ObjArbol getDato(){
		return Dato;
	}

	public void setDato(ObjArbol Dato){
		this.Dato = Dato;
	}

	public Nodo getDer() {
		return Der;
	}
	public void setDer(Nodo Der){
		this.Der = Der;
	}
	
    public String toString(){
    	return Dato + " ";
    }
}
