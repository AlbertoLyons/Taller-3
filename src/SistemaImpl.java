import ucn.*;

import java.io.*;
import java.util.Arrays;

public class SistemaImpl implements Sistema{

    ListaInstrumentos instrumentos;

    public SistemaImpl() {
        this.instrumentos = new ListaInstrumentos(100);
    }

    public final String separador = ",";

    /**
     * El sistema lee el archivo y lo implementa para añadir o eliminar instrumentos
     */
    public void leerArchivoCsv() throws IOException {

        BufferedReader bufferLectura = null;
        try {
            // Abrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader("csv_prueba.csv"));

            // Leer una linea del archivo
            String linea = bufferLectura.readLine();

            while (linea != null) {

                Instrumento instrumentoAux;

                // Sepapar la linea leída con el separador definido previamente
                String[] campos = linea.split(separador);

                String codigo = campos[0];
                String precioString = campos[1];
                int precio = Integer.parseInt(precioString);
                String stockString = campos[2];
                int stock = Integer.parseInt(stockString);
                String instrumento = campos[3];

                if (instrumento.equalsIgnoreCase("Guitarra") || instrumento.equalsIgnoreCase("Bajo") || instrumento.equalsIgnoreCase("Violin") || instrumento.equalsIgnoreCase("Arpa")){
                    String tipoCuerda = campos[4];
                    String numCuerdasString = campos[5];
                    int numCuerdas = Integer.parseInt(numCuerdasString);
                    String materialInstrumento = campos[6];
                    String tipoGuitarra = campos[7];
                    instrumentoAux = new Cuerda(instrumento,materialInstrumento,codigo,precio,stock,tipoCuerda,numCuerdas,tipoGuitarra);
                    instrumentos.agregarInstrumento(instrumentoAux);

                    // Se verifica que tipo instrumento de Percusion es
                } else if (instrumento.equalsIgnoreCase("Bongo") || instrumento.equalsIgnoreCase("Cajon") || instrumento.equalsIgnoreCase("Campanas Tubulares") || instrumento.equalsIgnoreCase("Bombo")) {
                    String tipoPercusion = campos[4];
                    String materialInstrumento = campos[5];
                    String altura = campos[6];
                    instrumentoAux = new Percusion(instrumento,materialInstrumento,codigo,precio,stock,tipoPercusion,altura);
                    instrumentos.agregarInstrumento(instrumentoAux);

                    // Se verifica que tipo de instrumento de Viento es
                } else if (instrumento.equalsIgnoreCase("Trompeta") || instrumento.equalsIgnoreCase("Saxofon") || instrumento.equalsIgnoreCase("Clarinete") || instrumento.equalsIgnoreCase("Flauta traversa")) {
                    String materialInstrumento = campos[4];
                    instrumentoAux = new Viento(instrumento,materialInstrumento,codigo,precio,stock);
                    instrumentos.agregarInstrumento(instrumentoAux);
                }



                System.out.println(Arrays.toString(campos));

                // Volver a leer otra línea del fichero
                linea = bufferLectura.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * El menu principal:
     * Si la opcion es 1, se va al subprograma agregarInstrumentoMenu
     * Si la opcion es 2, se va al subprograma venderInstrumento
     * Si la opcion es 3, se va al subprograma consultarInventario
     * Si la opcion es 4, el programa se cierra
     */
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
            opcion =  StdIn.readString();
            switch (opcion){
                case "1" -> this.agregarInstrumentoMenu();
                case "2" -> this.venderInstrumento();
                case "3" -> this.consultarInventario();
                case "4" -> salir();
                default -> StdOut.println("Opcion no valida, intente nuevamente");
            }
        }
    }

    @Override
    /**
     * Se agrega un instrumento dependiendo de la eleccion del usuario
     */
    public void agregarInstrumentoMenu() throws IOException {

        Instrumento instrumentoPorAgregar;
        StdOut.println("");
        StdOut.print("""
                    [*] Seleccione el tipo de instrumento
                    
                    [*] Cuerda
                    [*] Percusion
                    [*] Viento
            
                    """);

        StdOut.print("Escriba el nombre del tipo de instrumento: ");
        String instrumentoAux = StdIn.readString();

        // Se verifica que tipo de instrumento es (Cuerda, Percusion o Viento).
        if (instrumentoAux.equalsIgnoreCase("Cuerda") || instrumentoAux.equalsIgnoreCase("Percusion") || instrumentoAux.equalsIgnoreCase("Viento")){

            // Inicializamos la variable codigo y realizamos su validacion.
            String codigo;
            StdOut.print("Escriba el codigo del instrumento(este debe ser con valores numericos y solo 3 caracteres): ");
            codigo = StdIn.readString();

            // Inicializamos la variable precio.
            String precioString;
            int precio;
            // Se entra a un ciclo de validacion de la variable precio.
            while (true){

                try {
                    StdOut.print("Escriba el precio del instrumento: ");
                    precioString = StdIn.readString();
                    precio = Integer.parseInt(precioString);

                    if (precio <= 0){
                        StdOut.println("El precio debe ser mayor a 0, intentelo denuevo.");
                        StdOut.println();
                    }else {
                        break;
                    }
                }catch (Exception e){
                    StdOut.println("Ingrese un precio valido.");
                    StdOut.println("");
                }
            }

            // Inicializamos la variable stock.
            String stockString;
            int stock;
            // Se entra a un ciclo de validacion de la variable stock.
            while (true){

                try {
                    StdOut.print("Ingrese la cantidad de stock que desea agregar de este producto: ");
                    stockString = StdIn.readString();
                    stock = Integer.parseInt(stockString);

                    if (stock <= 0){
                        StdOut.println("El stock debe ser mayor a 0, intentelo denuevo.");
                        StdOut.println();
                    }else {
                        break;
                    }
                }catch (Exception e){
                    StdOut.println("El stock ingresado no es valido");
                    StdOut.println("");
                }

            }
            // Si el instrumento es tipo Cuerda, se consultan por sus atributos.
            if (instrumentoAux.equalsIgnoreCase("Cuerda")){

                String instrumentoNuevo[] = new String[8];
                instrumentoNuevo[0] = codigo;
                instrumentoNuevo[1] = precioString;
                instrumentoNuevo[2] = stockString;

                StdOut.print("Ingrese el instrumento (Guitarra, Bajo, Violin, Arpa): ");
                String nombreInstrumentoCuerda = StdIn.readString();
                instrumentoNuevo[3] = nombreInstrumentoCuerda;

                // Se verifica si es Guitarra, Bajo, Violin o Arpa.
                if (nombreInstrumentoCuerda.equalsIgnoreCase("Guitarra") || nombreInstrumentoCuerda.equalsIgnoreCase("Bajo") || nombreInstrumentoCuerda.equalsIgnoreCase("Violin") || nombreInstrumentoCuerda.equalsIgnoreCase("Arpa")){

                    StdOut.print("Ingrese el tipo de cuerda (Nylon, Acero, Tripa): ");
                    String tipoCuerda = StdIn.readString();
                    instrumentoNuevo[4] = tipoCuerda;

                    // Se verifica si el material es Nylon, Acero, o Tripa.
                    if (tipoCuerda.equalsIgnoreCase("Nylon") || tipoCuerda.equalsIgnoreCase("Acero") || tipoCuerda.equalsIgnoreCase("Tripa")) {

                        String numCuerdasString;
                        int numCuerdas;
                        // Se pide el numero de cuerdas para el instrumento Cuerda.
                        while (true) {
                            try {
                                StdOut.print("Ingrese el numero de cuerdas: ");
                                numCuerdasString = StdIn.readString();
                                numCuerdas = Integer.parseInt(numCuerdasString);

                                if (numCuerdas <= 0) {
                                    StdOut.println("Ingrese un numero valido(que este sea mayor que 0).");
                                    StdOut.println("");
                                }else {
                                    break;
                                }

                            } catch (Exception e) {
                                StdOut.println("Ingrese un numero valido.");
                                StdOut.println("");
                            }
                        }
                        instrumentoNuevo[5] = numCuerdasString;

                        StdOut.print("Ingrese el material del instrumento (Madera, Metal): ");
                        String tipoMaterial = StdIn.readString();
                        instrumentoNuevo[6] = tipoMaterial;

                        // Se verifica si el material es Madera o Metal.
                        if (tipoMaterial.equalsIgnoreCase("Madera") || tipoMaterial.equalsIgnoreCase("Metal")) {

                            StdOut.print("Ingrese el tipo del instrumento (Acustico, Electrico): ");
                            String tipoInstrumento = StdIn.readString();
                            instrumentoNuevo[7] = tipoInstrumento;

                            // Se verifica si el tipo de instrumento Cuerda es Acústico o Eléctrico.
                            if (tipoInstrumento.equalsIgnoreCase("Acustico") || tipoInstrumento.equalsIgnoreCase("Electrico")) {

                                /*
                                Finalizando la consulta para el instrumento tipo Cuerda:
                                Se crea el instrumento por agregar
                                Se añade a la listaInstrumentosImpl
                                 */
                                instrumentoPorAgregar = new Cuerda(nombreInstrumentoCuerda, tipoMaterial, codigo, precio, stock, tipoCuerda, numCuerdas, tipoInstrumento);
                                this.instrumentos.agregarInstrumento(instrumentoPorAgregar);

                                String archivo = "csv_prueba.csv";
                                int contador = 0;
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo,true))){

                                    writer.newLine();
                                    for (String instrumento: instrumentoNuevo){
                                        if (contador == 7){
                                            writer.write(instrumento);
                                            return;
                                        }
                                        writer.write(instrumento + ",");
                                        contador++;
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                StdOut.println("Instrumento agregado con exito!");
                            }
                        }
                    // Si el tipo de material era distinto de los especificados, se le avisa al usuario.
                    } else {
                        StdOut.println("El tipo de material ingresado no es valido. Intente agregar el instrumento denuevo.");
                        StdOut.println("");
                    }
                // Si el tipo de Cuerda era distinto de los especificados, se le avisa al usuario.
                }else {
                    StdOut.println("El tipo de instrumento ingresado no es valido. Intente agregar el instrumento denuevo.");
                    StdOut.println("");
                }
            // Si el instrumento no fue de tipo Cuerda, se verifica si es de Percusion.
            } else if (instrumentoAux.equals("Percusion")) {

                String instrumentoNuevo[] = new String[7];
                instrumentoNuevo[0] = codigo;
                instrumentoNuevo[1] = precioString;
                instrumentoNuevo[2] = stockString;


                StdOut.print("Ingrese el instrumento (Bongo, Cajon, Campanas Tubulares, Bombo): ");
                String nombreInstrumentoPercusion = StdIn.readString();
                instrumentoNuevo[3] = nombreInstrumentoPercusion;

                // Se verifica si el instrumento tipo Percusion es Bongo, Cajon, Campanas Tubulares, o Bombo.
                if (nombreInstrumentoPercusion.equalsIgnoreCase("Bongo") || nombreInstrumentoPercusion.equalsIgnoreCase("Campanas Tubulares") || nombreInstrumentoPercusion.equalsIgnoreCase("Bombo")){

                    StdOut.print("Ingrese el tipo de percusion (Membranofono, idiofono): ");
                    String tipoPercusion = StdIn.readString();
                    instrumentoNuevo[4] = tipoPercusion;

                    // Se verifica si el tipo de percusion es Membrafono, o Iidiofono.
                    if (tipoPercusion.equalsIgnoreCase("Membranofono") || tipoPercusion.equalsIgnoreCase("Iidiofono")){

                        StdOut.print("Ingrese el material del instrumento (Madera, Metal, Piel): ");
                        String tipoMaterial = StdIn.readString();
                        instrumentoNuevo[5] = tipoMaterial;

                        // Se verifica si el material es de Madera, Metal, o Piel.
                        if (tipoMaterial.equalsIgnoreCase("Madera") || tipoMaterial.equalsIgnoreCase("Metal") || tipoMaterial.equalsIgnoreCase("Piel")) {
                            StdOut.print("Ingrese la altura del instrumento (Definida, Indefinida): ");
                            String alturaPercusion = StdIn.readString();
                            instrumentoNuevo[6] = alturaPercusion;

                            // Se verifica si la altura estaba Definida o Indefinida.
                            if (alturaPercusion.equalsIgnoreCase("Definida") || alturaPercusion.equalsIgnoreCase("Indefinida")) {
                                instrumentoPorAgregar = new Percusion(nombreInstrumentoPercusion,tipoMaterial,codigo,precio,stock,nombreInstrumentoPercusion,alturaPercusion);
                                this.instrumentos.agregarInstrumento(instrumentoPorAgregar);

                                String archivo = "csv_prueba.csv";
                                int contador = 0;
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo,true))){

                                    writer.newLine();
                                    for (String instrumento: instrumentoNuevo){
                                        if (contador == 6){
                                            writer.write(instrumento);
                                            return;
                                        }
                                        writer.write(instrumento + ",");
                                        contador++;
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                StdOut.println("Instrumento agregado con exito!");
                            }
                        }
                    // Si el tipo de percusion es distinto de los especificados, se le avisa al usuario.
                    }else {
                        StdOut.println("El tipo de percusion ingresado no es valido.");
                    }
                // Si el instrumento es distinto de los especificados, se le avisa al usuario.
                }else {
                    StdOut.println("El instrumento ingresado no es valido.");
                }
            // Si el instrumento no es de tipo cuerda, ni tampoco de percusion, verificamos si es de viento.
            } else if (instrumentoAux.equals("Viento")) {

                String instrumentoNuevo[] = new String[5];
                instrumentoNuevo[0] = codigo;
                instrumentoNuevo[1] = precioString;
                instrumentoNuevo[2] = stockString;

                StdOut.print("Ingrese el instrumento (Trompeta, Saxofon, Clarinete, Flauta traversa): ");
                String nombreInstrumentoViento = StdIn.readString();
                instrumentoNuevo[3] = nombreInstrumentoViento;

                // Se verifica si el instrumento Viento es Trompeta, Saxofon, Clarinete o Flauta traversa.
                if (nombreInstrumentoViento.equalsIgnoreCase("Trompeta") || nombreInstrumentoViento.equalsIgnoreCase("Saxofon") || nombreInstrumentoViento.equalsIgnoreCase("Clarinete") || nombreInstrumentoViento.equalsIgnoreCase("Flauta traversa")){

                    StdOut.print("Ingrese el tipo de material del instrumento (Madera, metal):");
                    String materialInstrumento = StdIn.readString();
                    instrumentoNuevo[4] = materialInstrumento;


                    // Se verifica si el material es Madera, o Metal.
                    if (materialInstrumento.equalsIgnoreCase("Madera") || materialInstrumento.equalsIgnoreCase("Metal")){

                        instrumentoPorAgregar = new Viento(nombreInstrumentoViento,materialInstrumento,codigo,precio,stock);
                        this.instrumentos.agregarInstrumento(instrumentoPorAgregar);

                        String archivo = "csv_prueba.csv";
                        int contador = 0;
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo,true))){

                            writer.newLine();
                            for (String instrumento: instrumentoNuevo){
                                if (contador == 6){
                                    writer.write(instrumento);
                                    return;
                                }
                                writer.write(instrumento + ",");
                                contador++;
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        StdOut.println("Instrumento agregado con exito!");
                    // Si el tipo de material es distinto de los especificados, se le avisa al usuario.
                    }else {
                        StdOut.println("El tipo de material ingresado no es valido. Intente agregar el instrumento nuevamente.");
                    }
                }
            }

        // Si el instrumento en general no fue valido, se le avisa al usuario.
        }else {
            StdOut.println("El tipo de instrumento ingresado no es valido. Intente agregar el instrumento denuevo.");
            StdOut.println("");
        }
    }

    @Override
    /**
     * Vende un instrumento y lo elimina de la lista
     * Al vender un instrumento, se genera una boleta con el instrumento vendido y el precio de este
     */
    public void venderInstrumento() {

        StdOut.println("");
        StdOut.println("[*] Vender Instrumento");
        StdOut.println("");
        int codigo;
        while (true){
            try {
                StdOut.print("Ingrese el codigo del instrumento que desea comprar: ");
                String codigoString = StdIn.readString();
                codigo = Integer.parseInt(codigoString);

                if (codigo <= 0){
                    StdOut.println("El codigo debe ser un valor mayor a 0");
                    return;
                }else {
                    StdOut.println("");
                    break;
                }
            }catch (Exception e){
                StdOut.println("Ingrese un codigo valido.");
                StdOut.println("");
            }
        }
        Instrumento instrumentoAux = instrumentos.obtenerInstrumento(codigo);

        if (instrumentoAux == null){
            StdOut.println("No existe un instrumento con el codigo ingresado");
            return;
        }

        if (instrumentoAux.getStock() == 0){
            StdOut.println("No hay stock del instrumento");
            instrumentos.eliminarInstrumento(instrumentoAux);
            return;
        }

        String mensaje = "No hay instrumentos";

        if (instrumentoAux instanceof Cuerda){
            mensaje = ("Instrumento: "+instrumentoAux.getNombre()+" | Codigo: "+instrumentoAux.getCodigo()+" | Tipo de cuerda: "+((Cuerda) instrumentoAux).getTipoCuerda()+" | Cantidad de cuerdas: "+ ((Cuerda) instrumentoAux).getNumCuerdas()+" | Material: "+instrumentoAux.getMaterial()+" | Tipo de guitarra: "+((Cuerda) instrumentoAux).getTipo()+" | Stock: "+instrumentoAux.getStock()+" | Precio: "+instrumentoAux.getPrecio());
        } else if (instrumentoAux instanceof Percusion) {
            mensaje = ("Instrumento: "+instrumentoAux.getNombre()+" | Codigo: "+instrumentoAux.getCodigo()+" | Tipo de percusion: "+((Percusion) instrumentoAux).getPercusion()+" | Material: "+instrumentoAux.getMaterial()+" | Tipo de altura: "+((Percusion) instrumentoAux).getAltura()+" | Stock: "+instrumentoAux.getStock()+" | Precio: "+instrumentoAux.getPrecio());
        } else if (instrumentoAux instanceof Viento) {
            mensaje = ("Instrumento: "+instrumentoAux.getNombre()+" | Codigo: "+instrumentoAux.getCodigo()+" | Material: "+instrumentoAux.getMaterial()+" | Stock: "+instrumentoAux.getStock()+" | Precio: "+instrumentoAux.getPrecio());
        }

        if (mensaje.equals("No hay instrumentos")){
            return;
        }
        StdOut.println("* "+mensaje);
        StdOut.println("");

        StdOut.print("""
                    [*] Desea comprar el instrumento?
                    
                    [1] Si
                    [2] No             
                    
                    """);

        int opcion = 0;
        while (true){
            try {

                StdOut.print("Ingrese el numero correspondiente a su respuesta: ");
                opcion = StdIn.readInt();

                if (opcion <= 0 || opcion > 2){
                    StdOut.println("Debe ser 1 o 2. Intentelo denuevo");
                    return;
                }else {
                    break;
                }

            }catch (Exception e){
                StdOut.println("Ingrese una opcion valida, recuerde ingresar el numero.");
            }
        }

        switch (opcion) {
            case 1 -> {
                instrumentoAux.setStock(instrumentoAux.getStock() - 1);
                StdOut.println("--------- BOLETA DE COMPRA ---------------------------------------------");
                StdOut.println("Instrumento vendido: ");
                StdOut.println(mensaje);
                StdOut.println("------------------------------------------------------------------------");
            }
            case 2 -> StdOut.println("Volviendo atras.");
            default -> StdOut.println("Opcion ingresada no valida.");
        }
    }

    @Override
    /**
     * Busca un instrumento (Recorrer lista o especificar por codigo de instrumento)
     */
    public void consultarInventario() {

        StdOut.print("""
                
                    [*] Seleccione la opcion deseada al consultar
                    
                    [1] Buscar un instrumento ingresando su codigo
                    [2] Despliegue de los instrumentos por tipo
                    [3] Despliegue de todos los instrumentos 
                
                    """);
        StdOut.print("Ingrese la opcion deseada: ");
        String opcion = StdIn.readString();
        StdOut.println("");

        switch (opcion){
            case "1" -> this.despliegueInstrumentoCodigo();
            case "2" -> this.despliegueInstrumentosTipo();
            case "3" -> this.despliegueInstrumentos();
            default -> StdOut.println("La opcion ingresada no es valida.");
        }
    }

    @Override
    /**
     * Se sale del sistema finalizando el programa
     */
    public void salir() {
        StdOut.println("Cerrando sistema...");
        System.exit(0);

    }

    /**
     * Despliega el instrumento dado un codigo ingresado desde teclado.
     */
    public void despliegueInstrumentoCodigo(){

        StdOut.print("Ingrese el codigo del instrumento a buscar: ");
        int codigo = StdIn.readInt();
        StdOut.println("");

        Instrumento instrumentoAux = instrumentos.obtenerInstrumento(codigo);
        String mensaje = "No hay instrumentos";

        if (instrumentoAux instanceof Cuerda){
            mensaje = ("Instrumento: "+instrumentoAux.getNombre()+" | Codigo: "+instrumentoAux.getCodigo()+" | Tipo de cuerda: "+((Cuerda) instrumentoAux).getTipoCuerda()+" | Cantidad de cuerdas: "+ ((Cuerda) instrumentoAux).getNumCuerdas()+" | Material: "+instrumentoAux.getMaterial()+" | Tipo de guitarra: "+((Cuerda) instrumentoAux).getTipo()+" | Stock: "+instrumentoAux.getStock()+" | Precio: "+instrumentoAux.getPrecio());
        } else if (instrumentoAux instanceof Percusion) {
            mensaje = ("Instrumento: "+instrumentoAux.getNombre()+" | Codigo: "+instrumentoAux.getCodigo()+" | Tipo de percusion: "+((Percusion) instrumentoAux).getPercusion()+" | Material: "+instrumentoAux.getMaterial()+" | Tipo de altura: "+((Percusion) instrumentoAux).getAltura()+" | Stock: "+instrumentoAux.getStock()+" | Precio: "+instrumentoAux.getPrecio());
        } else if (instrumentoAux instanceof Viento) {
            mensaje = ("Instrumento: "+instrumentoAux.getNombre()+" | Codigo: "+instrumentoAux.getCodigo()+" | Material: "+instrumentoAux.getMaterial()+" | Stock: "+instrumentoAux.getStock()+" | Precio: "+instrumentoAux.getPrecio());
        }

        if (mensaje.equals("No hay instrumentos")){
            return;
        }
        StdOut.println("* " + mensaje);
        StdOut.println("");
    }

    /**
     * Despliega los instrumentos dado un tipo de instrumento ingresado desde teclado.
     */
    public void despliegueInstrumentosTipo(){

        StdOut.println("");
        StdOut.print("""
                
                [*] Seleccione el tipo de instrumentos a consultar
                    
                    [*] Cuerda
                    [*] Percusion
                    [*] Viento 
                
                """);
        StdOut.print("Escriba el nombre del tipo de instrumento: ");
        String tipoInstrumento = StdIn.readString();
        
        if (tipoInstrumento.equalsIgnoreCase("Cuerda")){
            for (int i = 0; i < this.instrumentos.getTamanio() ; i++) {
                if (instrumentos.obtenerInstrumento(i) instanceof Cuerda){
                    Instrumento instrumentoAux = instrumentos.obtenerInstrumento(i);
                    String mensaje = ("Instrumento: "+instrumentoAux.getNombre()+" | Codigo: "+instrumentoAux.getCodigo()+" | Tipo de cuerda: "+((Cuerda) instrumentoAux).getTipoCuerda()+" | Cantidad de cuerdas: "+ ((Cuerda) instrumentoAux).getNumCuerdas()+" | Material: "+instrumentoAux.getMaterial()+" | Tipo de guitarra: "+((Cuerda) instrumentoAux).getTipo()+" | Stock: "+instrumentoAux.getStock()+" | Precio: "+instrumentoAux.getPrecio());
                    StdOut.println("* " + mensaje);
                }
            }
            
        } else if (tipoInstrumento.equalsIgnoreCase("Percusion")) {
            for (int i = 0; i < this.instrumentos.getTamanio() ; i++) {
                if (instrumentos.obtenerInstrumento(i) instanceof Percusion){
                    Instrumento instrumentoAux = instrumentos.obtenerInstrumento(i);
                    String mensaje = ("Instrumento: "+instrumentoAux.getNombre()+" | Codigo: "+instrumentoAux.getCodigo()+" | Tipo de percusion: "+((Percusion) instrumentoAux).getPercusion()+" | Material: "+instrumentoAux.getMaterial()+" | Tipo de altura: "+((Percusion) instrumentoAux).getAltura()+" | Stock: "+instrumentoAux.getStock()+" | Precio: "+instrumentoAux.getPrecio());
                    StdOut.println("* " + mensaje);
                }
            }
            
        } else if (tipoInstrumento.equalsIgnoreCase("Viento")) {
            for (int i = 0; i < this.instrumentos.getTamanio() ; i++) {
                if (instrumentos.obtenerInstrumento(i) instanceof Viento){
                    Instrumento instrumentoAux = instrumentos.obtenerInstrumento(i);
                    String mensaje = ("Instrumento: "+instrumentoAux.getNombre()+" | Codigo: "+instrumentoAux.getCodigo()+" | Material: "+instrumentoAux.getMaterial()+" | Stock: "+instrumentoAux.getStock()+" | Precio: "+instrumentoAux.getPrecio());
                    StdOut.println("* " + mensaje);
                }
            }
            StdOut.println("");
        } else {
            StdOut.println("El tipo de instrumento ingresado no es valido.");
        }
    }

    /**
     * Despliega todos los instrumentos.
     */
    public void despliegueInstrumentos(){

        String[] instrumentos = this.obtenerInstrumentos();
        if (instrumentos == null || instrumentos.length == 0){
            StdOut.println("No hay instrumentos en el sistema.");
            return;
        }
        for (int i = 0; i < instrumentos.length ; i++) {

            StdOut.println("* " + instrumentos[i]);
        }
        StdOut.println("");
    }

    /**
     * Transforma a string la informacion del vendedor.
     *
     * @return instrumentos[].
     */
    public String[] obtenerInstrumentos(){

        String[] instrumentos = new String[this.instrumentos.getTamanio()];
        for (int i = 0; i < this.instrumentos.getTamanio() ; i++) {

            Instrumento instrumento = this.instrumentos.obtenerInstrumento(i);

            if (instrumento instanceof Cuerda){
                instrumentos[i] = "Instrumento: "+instrumento.getNombre()+" | Codigo: "+instrumento.getCodigo()+" | Tipo de cuerda: "+((Cuerda) instrumento).getTipoCuerda()+" | Cantidad de cuerdas: "+ ((Cuerda) instrumento).getNumCuerdas()+" | Material: "+instrumento.getMaterial()+" | Tipo de guitarra: "+((Cuerda) instrumento).getTipo()+" | Stock: "+instrumento.getStock()+" | Precio: "+instrumento.getPrecio();

            } else if (instrumento instanceof Percusion) {
                instrumentos[i] = "Instrumento: "+instrumento.getNombre()+" | Codigo: "+instrumento.getCodigo()+" | Tipo de percusion: "+((Percusion) instrumento).getPercusion()+" | Material: "+instrumento.getMaterial()+" | Tipo de altura: "+((Percusion) instrumento).getAltura()+" | Stock: "+instrumento.getStock()+" | Precio: "+instrumento.getPrecio();

            } else if (instrumento instanceof Viento) {
                instrumentos[i] = "Instrumento: "+instrumento.getNombre()+" | Codigo: "+instrumento.getCodigo()+" | Material: "+instrumento.getMaterial()+" | Stock: "+instrumento.getStock()+" | Precio: "+instrumento.getPrecio();
            }
        }
        return instrumentos;
    }


}
