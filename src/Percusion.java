public class Percusion extends Instrumento{ //Hereda la clase Instrumento
    /**
     * The percusion
     */
    private String percusion; //tipo de percusion
    /**
     * The altura
     */
    private String altura;

    /**
     * The constructor
     * @param nombre
     * @param material
     * @param codigo
     * @param precio
     * @param stock
     * @param percusion
     * @param altura
     */
    public Percusion(String nombre, String material, String codigo, int precio, int stock, String percusion, String altura) {
        super(nombre, material, codigo, precio, stock);
        this.percusion = percusion;
        this.altura = altura;
    }
    /**
     *
     * @return percusion
     */
    public String getPercusion() {
        return percusion;
    }
    /**
     *
     * @return altura
     */
    public String getAltura() {
        return altura;
    }
}
