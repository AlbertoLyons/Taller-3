import java.io.IOException;

public interface Sistema {

    public void leerArchivo() throws IOException;
    public void agregarInstrumentoMenu() throws IOException;
    public void venderInstrumento();
    public void consultarInventario();


}
