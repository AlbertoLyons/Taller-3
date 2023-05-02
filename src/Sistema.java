import java.io.IOException;

public interface Sistema {

    public void leerArchivo() throws IOException;
    public void agregarInstrumento(Instrumento instrumento);
    public void venderInstrumento();
    public void consultarInventario();


}
