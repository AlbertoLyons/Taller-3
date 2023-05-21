import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Creamos la variable sistema para luego llamar a los metodos que inician el programa.
        SistemaImpl sistema = new SistemaImpl();
        // Leemos el archivo y en caso de tener instrumentos estos se agrega.
        sistema.leerArchivoCsv();
        // Desplegamos el menu principal dando inicio al sistema.
        sistema.menuPrincipal();

    }
}