package Tp_2_Algoritmos;

import java.io.IOException;
import javax.swing.JOptionPane;

public class Principal {
    public static void main(String[] args) throws IOException {

        Registro CDs[] = {
        };
        
        TablaRandomAccessFile tabla = new TablaRandomAccessFile("Tp_2_Algoritmos/Principal.dat");
        for (int i = 0; i < CDs.length; i++) {
			tabla.Agregar(CDs[i]);
		}
		tabla.Cerrar();
        tabla = new TablaRandomAccessFile("Tp_2_Algoritmos/Principal.dat");
        
        Arbol arBin = new Arbol();
        arBin.crearArbol(tabla);
        int option;
        int ID;
        int Cant;
        String Descripción;
        int Precio;
        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un número en función de la tarea que desee realizar:\n 0.(Terminar) \n 1.(Dar de alta) \n 2.(Dar de baja) \n 3.(Modificar datos) \n 4.(Consultas)"));
            switch (option) {
                case 0:
                    break;
                case 1:
                    ID = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del nuevo producto para dar de alta"));
                    Cant = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la Cantidad en stock"));
                    Descripción = JOptionPane.showInputDialog("Ingrese el nombre del producto");
                    Precio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el precio"));
                    CD prod = new CD(ID, Cant, Descripción, Precio);
                    arBin.Insertar(tabla, prod);
                    break;
                case 2:
                    ID = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del producto que desea eliminar"));
                    arBin.eliminarLogico(ID);
                    break;
                case 3:
                    ID = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del producto que desea modificar"));
                    Cant = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la Cantidad en stock"));
                    Descripción = JOptionPane.showInputDialog("Ingrese el nombre del producto");
                    Precio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el precio"));
                    arBin.ActualizarPorID(ID, Cant, Descripción, Precio, tabla);
                    break;
                case 4:
                    ID = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del producto para mostrar su información"));
                    arBin.consultarCDPorID(ID, tabla);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "ERROR, intente usando un número válido: " + option, "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }while (option != 0);

        TablaRandomAccessFile archivoIndice = new TablaRandomAccessFile("Tp_2_Algoritmos/archivoIndice.dat");
        ObjArbol arregloDeInfo[] = arBin.preorden();
        for (int i = 0; i < arregloDeInfo.length; i++){
                archivoIndice.Agregar(arregloDeInfo[i]);
        }
        archivoIndice.Cerrar();
        archivoIndice = new TablaRandomAccessFile("Tp_2_Algoritmos/archivoIndice.dat");

        TablaRandomAccessFile archivoCompacto = new TablaRandomAccessFile("Tp_2_Algoritmos/archivoCompacto.dat");
        tabla.PasarData(archivoCompacto, archivoIndice);
        archivoCompacto.Cerrar();
        archivoCompacto = new TablaRandomAccessFile("Tp_2_Algoritmos/archivoCompacto.dat");
        
        tabla.compactar(archivoCompacto, archivoIndice);

    }

}
