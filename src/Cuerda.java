public class Cuerda extends Instrumento {
    private String tipoCuerda;
    private int n_cuerdas;
    private String tipo; //Acustico o electrico


    public Cuerda(String nombre, String material, String tipoCuerda, int n_cuerdas, String tipo) {
        super(nombre, material);
        this.tipoCuerda = tipoCuerda;
        this.n_cuerdas = n_cuerdas;
        this.tipo = tipo;
    }

    public String getTipoCuerda() {
        return tipoCuerda;
    }

    public int getN_cuerdas() {
        return n_cuerdas;
    }

    public String getTipo() {
        return tipo;
    }




}
