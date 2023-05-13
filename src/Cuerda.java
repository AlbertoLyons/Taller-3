public class Cuerda extends Instrumento {
    private String tipoCuerda;
    private int numCuerdas;
    private String tipo; //Acustico o electrico


    public Cuerda(String nombre, String material, String tipoCuerda, int numCuerdas, String tipo) {
        super(nombre, material);
        this.tipoCuerda = tipoCuerda;
        this.numCuerdas = numCuerdas;
        this.tipo = tipo;
    }

    public String getTipoCuerda() {
        return tipoCuerda;
    }

    public int getNumCuerdas() {
        return numCuerdas;
    }

    public String getTipo() {
        return tipo;
    }




}
