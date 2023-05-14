import java.io.IOException;

public interface Sistema {

    public void leerArchivo() throws IOException;
    public boolean sobreescribirArchivo(String instrumentoString, Instrumento instrumento) throws IOException;
    public void agregarInstrumentoLectura(Instrumento instrumento);
    public void agregarInstrumentoMenu() throws IOException;
    public void venderInstrumento();
    public void consultarInventario();


}
