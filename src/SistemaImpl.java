import ucn.*;

import java.io.IOException;

public class SistemaImpl implements Sistema{
    ListaInstrumentos listaInstrumentosImpl;
    int contInstrumentos;

    public SistemaImpl() {
        listaInstrumentosImpl = new ListaInstrumentos(100);
        this.contInstrumentos = 0;
    }
    public void menuPrincipal() throws IOException {
        String opcion = "";
        while (!opcion.equals("4")){

            StdOut.print("""
                    [*] Beat the Rhythm
                    
                    [1] Agregar Instrumento
                    [2] Vender Instrumento
                    [3] Consultar Inventario
                    [4] Salir
                    
                    """);

            StdOut.print("Escoja una opcion: ");
            StdOut.println("");
            opcion =  StdIn.readString();
            switch (opcion){
                case "1" -> this.agregarInstrumentoMenu();
                case "2" -> this.venderInstrumento();
                case "3" -> this.consultarInventario();
                case "4" -> StdOut.println("Cerrando sistema...");
                default -> StdOut.println("Opcion no valida, intente nuevamente");

            }
        }
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
                this.agregarInstrumentoLectura(instrumentoAux);
                this.contInstrumentos++;

            } else if (tipoInstrumento.equalsIgnoreCase("Percusion")) {

                String instrumento = regEnt.getString();
                String tipoPercusion = regEnt.getString();
                String materialInstrumento = regEnt.getString();
                String altura = regEnt.getString();

                Instrumento instrumentoAux = new Percusion(instrumento,materialInstrumento,tipoPercusion,altura);
                this.agregarInstrumentoLectura(instrumentoAux);
                this.contInstrumentos++;

            } else if (tipoInstrumento.equalsIgnoreCase("Viento")) {

                String instrumento = regEnt.getString();
                String materialInstrumento = regEnt.getString();

                Instrumento instrumentoAux = new Viento(instrumento,materialInstrumento);
                this.agregarInstrumentoLectura(instrumentoAux);
                this.contInstrumentos++;
            }
        }
    }

    @Override
    public void agregarInstrumentoLectura(Instrumento instrumento) {

        this.listaInstrumentosImpl.agregarInstrumento(instrumento);
    }

    @Override
    public void agregarInstrumentoMenu() throws IOException {

        String instrumentoAux = "";
        Instrumento instrumentoPorAgregar = new Viento("error","error");

        StdOut.print("""
                    [*] Seleccione un instrumento
                    
                    [1] Cuerda
                    [2] Percusion
                    [3] Viento
            
                    """);

        StdOut.print("Escriba el nombre del tipo de instrumento: ");
        instrumentoAux = StdIn.readString();


        // Agregar instrumento en caso de ser de cuerda.
        if (instrumentoAux.equalsIgnoreCase("Cuerda")){
            StdOut.println("Ingrese el instrumento (Guitarra, Bajo, Violin, Arpa): ");
            String nombreInstrumentoCuerda = StdIn.readString();

            if (!nombreInstrumentoCuerda.equalsIgnoreCase("Guitarra") || !nombreInstrumentoCuerda.equalsIgnoreCase("Bajo") || !nombreInstrumentoCuerda.equalsIgnoreCase("Violin") || !nombreInstrumentoCuerda.equalsIgnoreCase("Arpa")){
                StdOut.println("El instrumento ingresado no es valido.");
                return;
            }

            StdOut.println("Ingrese el tipo de cuerda (Nylon, Acero, Tripa): ");
            String tipoCuerda = StdIn.readString();
            if (!tipoCuerda.equalsIgnoreCase("Nylon") || !tipoCuerda.equalsIgnoreCase("Acero") || !tipoCuerda.equalsIgnoreCase("Tripa")) {
                StdOut.println("El tipo de cuerda no es valido");
                return;
            }

            int numCuerdas;
            while (true) {
                try {
                    StdOut.println("Ingrese el numero de cuerdas: ");
                    String numCuerdasString = StdIn.readString();
                    numCuerdas = Integer.parseInt(numCuerdasString);

                    if (numCuerdas <= 0) {
                        StdOut.println("Ingrese un numero valido");
                        return;
                    }else {
                        break;
                    }


                } catch (Exception e) {
                    StdOut.println("Ingrese un numero valido");
                }
            }

            StdOut.print("Ingrese el material del instrumento (Madera, Metal): ");
            String tipoMaterial = StdIn.readString();
            if (!tipoMaterial.equalsIgnoreCase("Madera") || !tipoMaterial.equalsIgnoreCase("Metal")) {
                StdOut.println("El tipo de material ingresado no es valido.");
                return;
            }
            StdOut.print("Ingrese el tipo del instrumento (Acústico, Eléctrico): ");
            String tipoInstrumento = StdIn.readString();
            if (!tipoInstrumento.equalsIgnoreCase("Acústico") || !tipoInstrumento.equalsIgnoreCase("Eléctrico")) {
                StdOut.println("El tipo de instrumento ingresado no es valido.");
                return;
            }
            // Se agrega el instrumento con los datos dados
            instrumentoPorAgregar = new Cuerda(nombreInstrumentoCuerda,tipoMaterial,tipoCuerda,numCuerdas,tipoInstrumento);
        }
        // Agregar instrumento en caso de ser de percusion.
        else if (instrumentoAux.equalsIgnoreCase("Percusion")){

            StdOut.print("Ingrese el instrumento (Bongó, Cajón, Campanas Tubulares, Bombo): ");
            String nombreInstrumentoPercusion = StdIn.readString();

            // Validacion por si el instrumento ingresado no es uno de los admitidos.
            if (!nombreInstrumentoPercusion.equalsIgnoreCase("Bongó") || !nombreInstrumentoPercusion.equalsIgnoreCase("Cajón") || !nombreInstrumentoPercusion.equalsIgnoreCase("Campanas Tubulares") || !nombreInstrumentoPercusion.equalsIgnoreCase("Bombo")){
                StdOut.println("El instrumento ingresado no es valido.");
                return;
            }

            StdOut.print("Ingrese el tipo de percusion(Membranófono, idiófono): ");
            String tipoPercusion =  StdIn.readString();

            // Validacion por si el tipo de percusion ingresada no es parte de los admitidos.
            if (!tipoPercusion.equalsIgnoreCase("Membranófono") || !tipoPercusion.equalsIgnoreCase("Idiófono")){
                StdOut.println("El tipo de instrumento ingresado no es valido.");
                return;
            }

            StdOut.print("Ingrese el material de construccion del instrumento(Madera, metal, piel): ");
            String materialInstrumento = StdIn.readString();

            if (!materialInstrumento.equalsIgnoreCase("Madera") || !materialInstrumento.equalsIgnoreCase("Metal") || !materialInstrumento.equalsIgnoreCase("Piel")){
                StdOut.println("El tipo de material ingresado no es valido.");
                return;
            }

            StdOut.print("Ingrese el tipo de altura(definida, indefinida): ");
            String altura = StdIn.readString();

            if (!altura.equalsIgnoreCase("Definida") || !altura.equalsIgnoreCase("Indefinida")){
                StdOut.println("El tipo de altura ingresada no es valida");
                return;
            }

            instrumentoPorAgregar = new Percusion(nombreInstrumentoPercusion,materialInstrumento,tipoPercusion,altura);

        }
        // Agregar instrumento en caso de ser de viento.
        else if (instrumentoAux.equalsIgnoreCase("Viento")){
            StdOut.print("Ingrese el instrumento (Trompeta, Saxofón, Clarinete, Flauta traversa): ");
            String nombreInstrumentoViento = StdIn.readString();

            if (nombreInstrumentoViento.equalsIgnoreCase("Trompeta") || nombreInstrumentoViento.equalsIgnoreCase("Saxofón") || nombreInstrumentoViento.equalsIgnoreCase("Clarinete") || nombreInstrumentoViento.equalsIgnoreCase("Flauta traversa")){

                StdOut.print("Ingrese el material del instrumento (Madera, Metal): ");
                String tipoMaterial = StdIn.readString();

                if (tipoMaterial.equalsIgnoreCase("Madera") || tipoMaterial.equalsIgnoreCase("Metal")) {

                    instrumentoPorAgregar = new Viento(nombreInstrumentoViento,tipoMaterial);
                }else {
                    StdOut.println("El tipo de material ingresado no es valido.");
                    return;
                }

            }else {
                StdOut.println("El instrumento ingresado no es valido.");
                return;
            }



        }else {
            StdOut.println("No fue posible agregar el instrumento.");
            return;
        }

        if (instrumentoPorAgregar.getNombre().equalsIgnoreCase("error")){
            StdOut.println("El instrumento no fue agregado");
            return;
        }

        listaInstrumentosImpl.agregarInstrumento(instrumentoPorAgregar);

        if (this.sobreescribirArchivo(instrumentoAux,instrumentoPorAgregar)){
            StdOut.println("Instrumento agregado con exito.");
            StdOut.println("");
            this.contInstrumentos++;
        }else {
            StdOut.println("No fue posible agregar el instrumento.");
        }

    }

    @Override
    public boolean sobreescribirArchivo(String instrumentoString, Instrumento instrumento) throws IOException {

        ArchivoSalida archSal = new ArchivoSalida("instrumentos.txt");
        Registro regSal;

        if (instrumento instanceof Cuerda && instrumentoString.equalsIgnoreCase("Cuerda")){

            regSal = new Registro(6);
            regSal.agregarCampo(instrumentoString);
            regSal.agregarCampo(instrumento.getNombre());
            regSal.agregarCampo(((Cuerda) instrumento).getTipoCuerda());
            regSal.agregarCampo(((Cuerda) instrumento).getNumCuerdas());
            regSal.agregarCampo(instrumento.getMaterial());
            regSal.agregarCampo(((Cuerda) instrumento).getTipo());
            archSal.writeRegistro(regSal);
            archSal.close();
            return true;

        } else if (instrumento instanceof Percusion && instrumentoString.equalsIgnoreCase("Percusion")) {

            regSal = new Registro(5);
            regSal.agregarCampo(instrumentoString);
            regSal.agregarCampo(instrumento.getNombre());
            regSal.agregarCampo(((Percusion) instrumento).getPercusion());
            regSal.agregarCampo(instrumento.getMaterial());
            regSal.agregarCampo(((Percusion) instrumento).getAltura());
            archSal.writeRegistro(regSal);
            archSal.close();
            return true;

        } else if (instrumento instanceof Viento && instrumentoString.equalsIgnoreCase("Viento")) {

            regSal = new Registro(3);
            regSal.agregarCampo(instrumentoString);
            regSal.agregarCampo(instrumento.getNombre());
            regSal.agregarCampo(instrumento.getMaterial());
            archSal.writeRegistro(regSal);
            archSal.close();
            return true;

        }else {
            return false;
        }
    }

    @Override
    public void venderInstrumento() {

    }

    @Override
    public void consultarInventario() {

    }
    public int getContInstrumentos() {
        return contInstrumentos;
    }
}
