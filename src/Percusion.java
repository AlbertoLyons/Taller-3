public class Percusion extends Instrumento{
    private String percusion;
    private boolean altura;

    public Percusion(String nombre, String material, String percusion, boolean altura) {
        super(nombre, material);
        this.percusion = percusion;
        this.altura = true;
    }
}
