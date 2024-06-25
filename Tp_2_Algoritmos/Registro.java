package Tp_2_Algoritmos;

import java.io.RandomAccessFile;

public interface Registro {
    public int Largo();
    public void leer(RandomAccessFile RandomAccessFile) throws Exception;
    public void grabar(RandomAccessFile RandomAccessFile) throws Exception;
   
}
