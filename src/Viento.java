public class Viento extends Instrumento{ //Hereda el abstract Instrumento

    /**
     * The constructor
     * @param nombre
     * @param material
     * @param codigo
     * @param precio
     * @param stock
     */
    public Viento(String nombre, String material, int codigo, int precio, int stock) {
        super(nombre, material, codigo, precio, stock);
    }

}
