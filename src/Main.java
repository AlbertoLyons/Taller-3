import ucn.StdIn;
import ucn.StdOut;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        SistemaImpl sistema = new SistemaImpl();
        sistema.leerArchivo();

        String opcion = "";
        while (!opcion.equals("6")){

            StdOut.println("""
                    
                    [*] Beat the Rhythm
                    
                    [1] Agregar Instrumento
                    [2] Vender Instrumento
                    [3] Consultar Inventario
                    [4] Salir
                    
                    """);

            StdOut.print("Escoja una opcion: ");
            opcion =  StdIn.readString();

        }
        
    }
}