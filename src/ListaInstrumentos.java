import ucn.StdOut;

public class ListaInstrumentos {
    /**
     * The max
     */
    private int max;
    /**
     * The tamanio
     */
    private int tamanio;
    /**
     * The listaInstrumentos[]
     */
    private Instrumento listaInstrumentos[];

    /**
     * The constructor
     * @param max
     */
    public ListaInstrumentos(int max) {
        this.max = max;
        this.tamanio = 0;
        this.listaInstrumentos = new Instrumento[max];
    }
    /**
     * Agrega un instrumento
     *
     * @param instrumento
     */
    public void agregarInstrumento(Instrumento instrumento) {

        // Validacion por si la lista de instrumentos se encuentra llena.
        if (tamanio == max) {
            StdOut.println("No se pueden almacenar mas intrumentos!");
            return;
        }
        // Si pasa la validacion se agrega el instrumento y se incrementa el tamanio.
        this.listaInstrumentos[tamanio] = instrumento;
        tamanio++;
    }
    /**
     * Busca la posicion del instrumento en base a un instrumento dado.
     *
     * @param instrumento
     * @return
     */
    public int buscarInstrumento(Instrumento instrumento) {
        // Busca la posicion dentro de la lista.
        for (int i = 0;i < max;i++) {

            // Si encuentra la posicion del instrumento retorna el valor de este.
            if (this.listaInstrumentos[i] == instrumento) {
                return i;
            }
        }
        // En caso de no encontrar la posicion del instrumento, retorna el valor -1 para realizar validaciones.
        return -1;
    }

    /**
     * Elimina un instrumento.
     *
     * @param instrumento
     * @return
     */
    public boolean eliminarInstrumento(Instrumento instrumento) {
        // Buscamos la posicion para eliminarlo
        int posicion = buscarInstrumento(instrumento);

        // Validacion por si la posicion fue encontrada.
        if (posicion != -1) {
            for (int i = posicion; i < this.tamanio; i++) {
                this.listaInstrumentos[i] = this.listaInstrumentos[i+i];
            }
            this.tamanio--;
            return true;
            // Retornamos true si se elimino
        }
        // Retornamos false si la posicion no fue encontrada.
        return false;
    }

}
