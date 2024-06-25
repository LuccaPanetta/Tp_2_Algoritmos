package Tp_2_Algoritmos;

import java.io.RandomAccessFile;

public class CD implements Registro {
    private int Código;
    private int Cantidad;
    private String Descripción; 
    private float Precio;
    private int Largo = 32;

    public CD(){
        
    }
    public CD(int Código, int Cantidad, String Descripción, float Precio){
        this.Código = Código;
        this.Cantidad = Cantidad;
        this.Descripción = Descripción;
        this.Precio = Precio;
    }

    public int getCódigo(){
        return this.Código;
    }

    public void setCódigo(int Código){
        this.Código = Código;
    }

    public String getDescripción(){
        return this.Descripción;
    }

    public void setDescripción(String Descripción){
        this.Descripción = Descripción;
    }

    public int getCantidad(){
        return this.Cantidad;
    }

    public void setCantidad(int Cantidad){
        this.Cantidad = Cantidad;
    }

    public float getPrecio(){
        return this.Precio;
    }

    public void setPrecio(float Precio){
        this.Precio = Precio;
    }

    @Override
    public String toString() {
        return "{" +
            " Código='" + Código + "'" +
            ", Cantidad='" + Cantidad + "'" +
            ", Descripción='" + Descripción + "'" +
            ", Precio='" + Precio + "'" +
            "}";
    }

	public int Largo() {
		return Largo;
	}

	public void grabar(RandomAccessFile RandomAccessFile) throws Exception {
        try {
		    RandomAccessFile.writeInt(Código);
            RandomAccessFile.writeUTF(uniformar(Descripción, 18));
		    RandomAccessFile.writeInt(Cantidad);
            RandomAccessFile.writeFloat(Precio);
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
	}
    public void leer(RandomAccessFile RandomAccessFile) throws Exception {
        try {
            this.Código = RandomAccessFile.readInt();
            this.Descripción = RandomAccessFile.readUTF();
            this.Cantidad = RandomAccessFile.readInt();
            this.Precio = RandomAccessFile.readFloat();
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }



	private String uniformar(String s, int t) {
		String u = "";
		for (int i = 0; i < t; i++)
			if (i < s.length()) {
				u = u + s.charAt(i);
			} else {
				u = u + " ";
			}
		return u;
	}

}
