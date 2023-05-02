import ucn.ArchivoEntrada;
import ucn.Registro;
import ucn.StdOut;

import java.io.IOException;

public class SistemaImpl implements Sistema{

    ListaInstrumentos listaInstrumentosImpl;
    int contInstrumentos;

    public SistemaImpl() {
        listaInstrumentosImpl = new ListaInstrumentos(100);
        this.contInstrumentos = 0;
    }

    @Override
    public void leerArchivo() throws IOException {

        ArchivoEntrada archEnt = new ArchivoEntrada("instrumentos.txt");

        while (!archEnt.isEndFile()){

            Registro regEnt = archEnt.getRegistro();
            String tipoInstrumento = regEnt.getString();

            // Comparamos que tipo de instrumento es, de esta manera le leemos sus tipos de variables, ya que, cada instrumento tiene distintas
            if (tipoInstrumento.equalsIgnoreCase("Cuerda")){

                String instrumento = regEnt.getString();
                String tipoCuerda = regEnt.getString();
                int numCuerdas = regEnt.getInt();
                String materialInstrumento = regEnt.getString();
                String tipo = regEnt.getString();

                Instrumento instrumentoAux = new Cuerda(instrumento,materialInstrumento,tipoCuerda,numCuerdas,tipo);
                this.agregarInstrumento(instrumentoAux);
                this.contInstrumentos++;

            } else if (tipoInstrumento.equalsIgnoreCase("Percusion")) {

                String instrumento = regEnt.getString();
                String tipoPercusion = regEnt.getString();
                String materialInstrumento = regEnt.getString();
                String alturaAux = regEnt.getString();
                boolean altura;
                if (alturaAux.equals("definida")){
                    altura = true;
                }else {
                    altura = false;
                }
                Instrumento instrumentoAux = new Percusion(instrumento,materialInstrumento,tipoPercusion,altura);
                this.agregarInstrumento(instrumentoAux);
                this.contInstrumentos++;

            } else if (tipoInstrumento.equalsIgnoreCase("Viento")) {

                String instrumento = regEnt.getString();
                String materialInstrumento = regEnt.getString();

                Instrumento instrumentoAux = new Viento(instrumento,materialInstrumento);
                this.agregarInstrumento(instrumentoAux);
                this.contInstrumentos++;
            }
        }
    }

    public int getContInstrumentos() {
        return contInstrumentos;
    }

    @Override
    public void agregarInstrumento(Instrumento instrumento) {
        listaInstrumentosImpl.agregarInstrumento(instrumento);
    }

    @Override
    public void venderInstrumento() {

    }

    @Override
    public void consultarInventario() {

    }
}
