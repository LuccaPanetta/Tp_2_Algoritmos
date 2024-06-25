package Tp_2_Algoritmos;

import java.io.RandomAccessFile;

public class ObjArbol implements Comparable <ObjArbol>{
    private int ID;
    private int Desplazamiento = 0;
    private boolean Activo;
	private static int Cant = 0;
	private int Largo = 9; 

	public ObjArbol(){

	}

	public ObjArbol (int ID){
		this.ID = ID;
		this.Desplazamiento = Cant * 32;
		this.Activo = true;
		ObjArbol.Cant++;
	}

	public int getID(){
		return ID;
	}
	public void setID(int ID){
		this.ID = ID;
	}

	public int getLargo(){
		return Largo;
	}

	public void setLargo(int Largo){
		this.Largo = Largo;
	}

	public boolean isActivo(){
		return this.Activo;
	}

	public boolean getActivo(){
		return this.Activo;
	}

	public int getDesplazamiento(){
		return this.Desplazamiento;
	}

	public void setDesplazamiento(int Desplazamiento){
		this.Desplazamiento = Desplazamiento;
	}

	public void setActivo(boolean Activo){
		this.Activo = Activo;
	}

	@Override
	public int compareTo(ObjArbol o){
		return this.ID - o.ID;
	}
	
	public void grabarRegistroIndice(RandomAccessFile RandomAccessFile) throws Exception{
		try {
			RandomAccessFile.writeInt(ID);
			RandomAccessFile.writeInt(Desplazamiento);
			RandomAccessFile.writeBoolean(Activo);
		} catch (Exception e) {
			System.out.println("Excepcion al grabar en el Archivo indice " + e);
		}
	}

	public void leerRegistroIndice(RandomAccessFile RandomAccessFile) throws Exception{
		try {
			this.ID = RandomAccessFile.readInt();
			this.Desplazamiento = RandomAccessFile.readInt();
			this.Activo = RandomAccessFile.readBoolean();
		} catch (Exception e) {
			System.out.println("Excepcion al leer en el Archivo indice " + e);
		}
	}

	@Override
	public String toString() {
		return "{" +
			" ID='" + ID + "'" +
			", Desplazamiento='" + Desplazamiento + "'" +
			", Activo='" + Activo + "'" +
			"}";
	}
}
