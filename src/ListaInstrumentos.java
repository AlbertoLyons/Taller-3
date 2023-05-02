import ucn.StdOut;

public class ListaInstrumentos {

    private int max;
    private int tamanio;
    private Instrumento listaInstrumentos[];

    public ListaInstrumentos(int max) {
        this.max = max;
        this.tamanio = 0;
        this.listaInstrumentos = new Instrumento[max];
    }
    public void agregarInstrumento(Instrumento instrumento) {
        if (tamanio == max) {
            StdOut.println("No se pueden almacenar mas intrumentos!");
            return;
        }
        this.listaInstrumentos[tamanio] = instrumento;
        tamanio++;
    }
    public int buscarInstrumento(Instrumento instrumento) {
        for (int i = 0;i < max;i++) {
            if (this.listaInstrumentos[i] == instrumento) {
                return i;
            }
        }
        return -1;
    }
    public boolean eliminarInstrumento(Instrumento instrumento) {
        int posicion = buscarInstrumento(instrumento);
        if (posicion != -1) {
            for (int i = posicion; i < tamanio; i++) {
                this.listaInstrumentos[i] = this.listaInstrumentos[i+i];
            }
            tamanio++;
            return true;
        }
        return false;
    }
}
