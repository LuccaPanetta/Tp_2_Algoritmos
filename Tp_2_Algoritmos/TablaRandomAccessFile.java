package Tp_2_Algoritmos;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

class TablaRandomAccessFile {
    private RandomAccessFile RandomAccessFile;

    public TablaRandomAccessFile() {
    }

    public TablaRandomAccessFile(String nombre) {
        try {
            RandomAccessFile = new RandomAccessFile(nombre, "rw");
            RandomAccessFile.seek(0);
        } catch (Exception e) {
            System.err.println("ERROR: " + nombre + " no se pudo abrir" + e);
        }
    }

    public void Agregar(Registro registro) {
        try {
            RandomAccessFile.seek(RandomAccessFile.length()); 
            registro.grabar(RandomAccessFile);
        } catch (Exception e) {
            System.out.println("ERROR: al grabar " + e);
        }
    }

    public void Agregar(ObjArbol dataTree) {
        try {
            RandomAccessFile.seek(RandomAccessFile.length()); 
            dataTree.grabarRegistroIndice(RandomAccessFile);
        } catch (Exception e) {
            System.out.println("ERROR: al grabar en el Archivo indice" + e);
        }
    }

    public void Actualizar(int num,  Registro registro) {
        try {
            RandomAccessFile.seek((num - 1) * registro.Largo());
            registro.grabar(RandomAccessFile);
        } catch (Exception e) {
            System.out.println("ERROR: al Actualizar ");
        }
    }

    public void get(int num, Registro registro) {
        try {
            RandomAccessFile.seek((num - 1) * registro.Largo());
            registro.leer(RandomAccessFile);
        } catch (Exception e) {
            System.out.println("ERROR: en el get del maestro" + e);
        }
    }

    public void get(int num, ObjArbol treeData) {
        try {
            RandomAccessFile.seek((num - 1) * treeData.getLargo());
            treeData.leerRegistroIndice(RandomAccessFile);
        } catch (Exception e) {
            System.out.println("ERROR: en el get  de indice" + e);
        }
    }

    public boolean EndOfFile() {
        boolean registro = false;
        try {
            registro = (RandomAccessFile.getFilePointer() >= RandomAccessFile.length());
        } catch (Exception e) {
            System.out.println("ERROR: en End of File");
        }
        return registro;
    }

    public void Cerrar() {
        try {
            RandomAccessFile.close();
        } catch (Exception e) {
            System.out.println("ERROR: al cerrar");
        }
    }

    public void PasarData(TablaRandomAccessFile compacto, TablaRandomAccessFile archIn){
        ObjArbol data = new ObjArbol();
        CD prod = new CD();
        int n = 1;
        while (!archIn.EndOfFile()) {
            archIn.get(n, data);
            if (data.getActivo()) {
                int pos = 1 + (data.getDesplazamiento()/prod.Largo());
                this.get(pos, prod);
                compacto.Agregar(prod);
            }   
            n++;
        }
    }

    public void Recorrer() {
        int num = 0 ;
        CD registro = new CD();
        while (!this.EndOfFile()) {
            this.get(num, registro);
            System.out.println(registro);
            num++;
        }
    }

    public void compactar(TablaRandomAccessFile archivoCompacto, TablaRandomAccessFile archivoIndice) throws IOException {
        try {
            this.Cerrar();
            archivoCompacto.Cerrar();
            archivoIndice.Cerrar();
            Path path = Paths.get("Tp_2_Algoritmos/Principal.dat");
            Path path2 = Paths.get("Tp_2_Algoritmos/archivoCompacto.dat");
            Files.move(path2, path, StandardCopyOption.REPLACE_EXISTING);
            File indexFile = new File("Tp_2_Algoritmos/archivoIndice.dat");
            File file = new File("Tp_2_Algoritmos/archivoCompacto.dat");
            File teachFile = new File("Tp_2_Algoritmos/Principal.dat");
            if (file.renameTo(teachFile)) {
                System.out.println("Nombre y  ruta de Archivo  cambiado");
                }
            indexFile.delete();
                
            
        } catch (Exception e) {
            System.out.println("Exepcion al compactar: " + e);
        }
        
    }
}
