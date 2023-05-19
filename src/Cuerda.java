public class Cuerda extends Instrumento  { //Hereda el abstract Instrumento
    /**
     * The tipoCuerda
     */
    private String tipoCuerda;
    /**
     * The numCuerdas
     */
    private int numCuerdas;
    /**
     * The tipo
     */
    private String tipo; //Acustico o electrico

    /**
     * The constructor
     * @param nombre
     * @param material
     * @param codigo
     * @param precio
     * @param stock
     * @param tipoCuerda
     * @param numCuerdas
     * @param tipo
     */
    public Cuerda(String nombre, String material, int codigo, int precio, int stock, String tipoCuerda, int numCuerdas, String tipo) {
        super(nombre, material, codigo, precio, stock);
        this.tipoCuerda = tipoCuerda;
        this.numCuerdas = numCuerdas;
        this.tipo = tipo;
    }
    /**
     *
     * @return the tipoCuerda
     */
    public String getTipoCuerda() {
        return tipoCuerda;
    }
    /**
     *
     * @return numCuerdas
     */
    public int getNumCuerdas() {
        return numCuerdas;
    }
    /**
     *
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }




}
