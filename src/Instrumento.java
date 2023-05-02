abstract class Instrumento {

    private String nombre;
    private String material;

    public Instrumento(String nombre, String material) {
        this.nombre = nombre;
        this.material = material;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMaterial() {
        return material;
    }
}
