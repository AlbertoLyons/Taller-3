import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SistemaImpl sistema = new SistemaImpl();
        sistema.leerArchivoCsv();
        sistema.menuPrincipal();

    }
}