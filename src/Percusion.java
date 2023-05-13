public class Percusion extends Instrumento{
    private String percusion;
    private String altura;

    public Percusion(String nombre, String material, String percusion, String altura) {
        super(nombre, material);
        this.percusion = percusion;
        this.altura = altura;

    }
}
