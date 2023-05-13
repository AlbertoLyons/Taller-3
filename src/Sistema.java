import java.io.IOException;

public interface Sistema {

    public void leerArchivo() throws IOException;
    public void agregarInstrumentoLectura(Instrumento instrumento);
    public void agregarInstrumentoMenu();
    public void venderInstrumento();
    public void consultarInventario();


}
