abstract class Instrumento { //La clase madre
    /**
     * The nombre
     */
    private String nombre;
    /**
     * The material
     */
    private String material;
    /**
     * The codigo
     */
    private String codigo;
    /**
     * The precio
     */
    private int precio;
    /**
     * The stock
     */
    private int stock;

    /**
     * The constructor
     * @param nombre
     * @param material
     * @param codigo
     * @param precio
     * @param stock
     */
    public Instrumento(String nombre, String material, String codigo, int precio, int stock) {
        this.nombre = nombre;
        this.material = material;
        this.codigo = codigo;
        this.precio = precio;
        this.stock = stock;
    }
    /**
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     *
     * @return the material
     */
    public String getMaterial() {
        return material;
    }
    /**
     *
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     *
     * @return the precio
     */
    public int getPrecio() {
        return precio;
    }
    /**
     *
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
