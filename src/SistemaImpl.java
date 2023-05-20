import ucn.*;

import java.io.IOException;

public class SistemaImpl implements Sistema{
    Instrumento[] listaInstrumentosImpl;
    int contInstrumentos;

    public SistemaImpl() {
        listaInstrumentosImpl = new Instrumento[100]; //Cantidad maxima de la lista
        this.contInstrumentos = 0;
    }

    @Override
    /**
     * El sistema lee el archivo y lo implementa para añadir o eliminar instrumentos
     */
    public void leerArchivo() throws IOException {

        Instrumento instrumentoPorAgregar;
        ArchivoEntrada archEnt = new ArchivoEntrada("instrumentos.csv");


        while (!archEnt.isEndFile()){

            Registro regEntrada = archEnt.getRegistro();
            int codigo = regEntrada.getInt();
            int precio = regEntrada.getInt();
            int stock = regEntrada.getInt();
            String instrumento = regEntrada.getString();

            // Se verifica que tipo instrumento de Cuerda es
            if (instrumento.equalsIgnoreCase("Guitarra") || instrumento.equalsIgnoreCase("Bajo") || instrumento.equalsIgnoreCase("Violin") || instrumento.equalsIgnoreCase("Arpa")){

                String tipoCuerda = regEntrada.getString();
                int numCuerdas = regEntrada.getInt();
                String materialConstruccion = regEntrada.getString();
                String tipo = regEntrada.getString();

                instrumentoPorAgregar = new Cuerda(instrumento,materialConstruccion,codigo,stock,precio,tipoCuerda,numCuerdas,tipo);
                this.agregarInstrumentoArchivo(instrumentoPorAgregar);
                this.contInstrumentos++;

            // Se verifica que tipo instrumento de Percusion es
            } else if (instrumento.equalsIgnoreCase("Bongo") || instrumento.equalsIgnoreCase("Cajon") || instrumento.equalsIgnoreCase("Campanas Tubulares") || instrumento.equalsIgnoreCase("Bombo")) {

                String tipoPercusion = regEntrada.getString();
                String materialConstruccion = regEntrada.getString();
                String altura = regEntrada.getString();

                instrumentoPorAgregar = new Percusion(instrumento,materialConstruccion,codigo,stock,precio,tipoPercusion,altura);
                this.agregarInstrumentoArchivo(instrumentoPorAgregar);
                this.contInstrumentos++;

            // Se verifica que tipo de instrumento de Viento es
            } else if (instrumento.equalsIgnoreCase("Trompeta") || instrumento.equalsIgnoreCase("Saxofon") || instrumento.equalsIgnoreCase("Clarinete") || instrumento.equalsIgnoreCase("Flauta traversa")) {

                String materialConstruccion = regEntrada.getString();

                instrumentoPorAgregar = new Viento(instrumento,materialConstruccion,codigo,precio,stock);
                this.agregarInstrumentoArchivo(instrumentoPorAgregar);
                this.contInstrumentos++;
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
            StdOut.println("");
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
    /**
     * Se agregan los instrumentos del archivo csv
     * @param instrumento
     */
    public void agregarInstrumentoArchivo(Instrumento instrumento) {

        if (this.contInstrumentos > this.listaInstrumentosImpl.length){
            StdOut.println("No se pueden agregar mas instrumentos.");
            return;
        }

        this.listaInstrumentosImpl[this.contInstrumentos] = instrumento;
        this.contInstrumentos++;
    }

    @Override
    /**
     * Se agrega un instrumento dependiendo de la eleccion del usuario
     */
    public void agregarInstrumentoMenu() throws IOException {

        Instrumento instrumentoPorAgregar;

        StdOut.print("""
                    [*] Seleccione el tipo de instrumento
                    
                    [*] Cuerda
                    [*] Percusion
                    [*] Viento
            
                    """);

        StdOut.print("Escriba el nombre del tipo de instrumento: ");
        String instrumentoAux = StdIn.readString();

        // Se verifica que tipo de instrumento es (Cuerda, Percusion o Viento)
        if (instrumentoAux.equalsIgnoreCase("Cuerda") || instrumentoAux.equalsIgnoreCase("Percusion") || instrumentoAux.equalsIgnoreCase("Viento")){

            // inicializamos la variable codigo
            int codigo;
            // Se pide el codigo del instrumento a agregar
            while (true){
                try {

                    StdOut.print("Escriba el codigo del instrumento: ");
                    String codigoString = StdIn.readString();
                    codigo = Integer.parseInt(codigoString);

                    if (codigo <= 0){
                        StdOut.println("El codigo no puede ser 0. Intentelo denuevo");
                        StdOut.println();
                    }else {
                        break;
                    }

                }catch (Exception e){
                    StdOut.println("Ingrese un codigo valido.");
                    StdOut.println("");
                }
            }

            // inicializamos la variable precio
            int precio;
            // Se pide el precio del instrumento a agregar
            while (true){

                try {
                    StdOut.print("Escriba el precio del instrumento: ");
                    String precioString = StdIn.readString();
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

            // inicializamos la variable stock
            int stock;
            // Se pide la cantidad de stock a agregar
            while (true){

                try {
                    StdOut.print("Ingrese la cantidad de stock que desea agregar de este producto: ");
                    String stockString = StdIn.readString();
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
            // Si el instrumento es tipo Cuerda, se consultan por sus atributos
            if (instrumentoAux.equalsIgnoreCase("Cuerda")){

                StdOut.print("Ingrese el instrumento (Guitarra, Bajo, Violin, Arpa): ");
                String nombreInstrumentoCuerda = StdIn.readString();

                // Se verifica si es Guitarra, Bajo, Violin o Arpa
                if (nombreInstrumentoCuerda.equalsIgnoreCase("Guitarra") || nombreInstrumentoCuerda.equalsIgnoreCase("Bajo") || nombreInstrumentoCuerda.equalsIgnoreCase("Violin") || nombreInstrumentoCuerda.equalsIgnoreCase("Arpa")){

                    StdOut.print("Ingrese el tipo de cuerda (Nylon, Acero, Tripa): ");
                    String tipoCuerda = StdIn.readString();

                    // Se verifica si el material es Nylon, Acero, o Tripa
                    if (tipoCuerda.equalsIgnoreCase("Nylon") || tipoCuerda.equalsIgnoreCase("Acero") || tipoCuerda.equalsIgnoreCase("Tripa")) {

                        int numCuerdas;
                        // Se pide el numero de cuerdas para el instrumento Cuerda
                        while (true) {
                            try {
                                StdOut.print("Ingrese el numero de cuerdas: ");
                                String numCuerdasString = StdIn.readString();
                                numCuerdas = Integer.parseInt(numCuerdasString);

                                if (numCuerdas <= 0) {
                                    StdOut.println("Ingrese un numero valido(que este sea mayor que 0).");
                                    return;
                                }else {
                                    break;
                                }

                            } catch (Exception e) {
                                StdOut.println("Ingrese un numero valido.");
                                StdOut.println("");
                            }
                        }

                        StdOut.print("Ingrese el material del instrumento (Madera, Metal): ");
                        String tipoMaterial = StdIn.readString();

                        // Se verifica si el material es Madera o Metal
                        if (tipoMaterial.equalsIgnoreCase("Madera") || tipoMaterial.equalsIgnoreCase("Metal")) {

                            StdOut.print("Ingrese el tipo del instrumento (Acústico, Eléctrico): ");
                            String tipoInstrumento = StdIn.readString();

                            // Se verifica si el tipo de instrumento Cuerda es Acústico o Eléctrico
                            if (tipoInstrumento.equalsIgnoreCase("Acústico") || tipoInstrumento.equalsIgnoreCase("Eléctrico")) {

                                /*
                                Finalizando la consulta para el instrumento tipo Cuerda:
                                Se crea el instrumento por agregar
                                Se añade a la listaInstrumentosImpl
                                El contador de instrumentos aumenta 1
                                 */
                                instrumentoPorAgregar = new Cuerda(nombreInstrumentoCuerda, tipoMaterial, codigo, precio, stock, tipoCuerda, numCuerdas, tipoInstrumento);
                                this.listaInstrumentosImpl[contInstrumentos] = instrumentoPorAgregar;
                                this.contInstrumentos++;
                                StdOut.println("Agregado con exito");
                            }
                        }
                    // Si no hubo algun tipo de material, se le avisa al usuario
                    } else {
                        StdOut.println("El tipo de material ingresado no es valido. Intente agregar el instrumento denuevo.");
                        StdOut.println("");
                    }
                // Si no hubo algun tipo de Cuerda definido, se le avisa al usuario
                }else {
                    StdOut.println("El tipo de instrumento ingresado no es valido. Intente agregar el instrumento denuevo.");
                    StdOut.println("");
                }
            // Si el instrumento no fue de tipo Cuerda, se verifica si es de Percusion
            } else if (instrumentoAux.equals("Percusion")) {

                StdOut.print("Ingrese el instrumento (Bongo, Cajon, Campanas Tubulares, Bombo): ");
                String nombreInstrumentoPercusion = StdIn.readString();

                // Se verifica si el instrumento tipo Percusion es Bongo, Cajon, Campanas Tubulares, o Bombo
                if (nombreInstrumentoPercusion.equalsIgnoreCase("Bongo") || nombreInstrumentoPercusion.equalsIgnoreCase("Campanas Tubulares") || nombreInstrumentoPercusion.equalsIgnoreCase("Bombo")){

                    StdOut.print("Ingrese el tipo de percusion (Membranofono, idiofono): ");
                    String tipoPercusion = StdIn.readString();

                    // Se verifica si el tipo de percusion es Membrafono, o Iidiofono
                    if (tipoPercusion.equalsIgnoreCase("Membranofono") || tipoPercusion.equalsIgnoreCase("Iidiofono")){

                        StdOut.print("Ingrese el material del instrumento (Madera, Metal, Piel): ");
                        String tipoMaterial = StdIn.readString();

                        // Se verifica si el material es de Madera, Metal, o Piel
                        if (tipoMaterial.equalsIgnoreCase("Madera") || tipoMaterial.equalsIgnoreCase("Metal") || tipoMaterial.equalsIgnoreCase("Piel")) {
                            StdOut.print("Ingrese la altura del instrumento (Definida, Indefinida): ");
                            String alturaPercusion = StdIn.readString();

                            // Se verifica si la altura estaba Definida o Indefinida
                            if (alturaPercusion.equalsIgnoreCase("Definida") || alturaPercusion.equalsIgnoreCase("Indefinida")) {
                                instrumentoPorAgregar = new Percusion(nombreInstrumentoPercusion,tipoMaterial,codigo,precio,stock,nombreInstrumentoPercusion,alturaPercusion);
                                this.listaInstrumentosImpl[contInstrumentos] = instrumentoPorAgregar;
                                this.contInstrumentos++;
                                StdOut.println("Agregado con exito");
                            }
                        }
                    }else {
                        StdOut.println("El tipo de percusion ingresado no es valido.");
                    }

                }else {
                    StdOut.println("El instrumento ingresado no es valido.");
                }

            } else if (instrumentoAux.equals("Viento")) {

                StdOut.print("Ingrese el instrumento (Trompeta, Saxofon, Clarinete, Flauta traversa): ");
                String nombreInstrumentoViento = StdIn.readString();

                // Se verifica si el instrumento Viento es Trompeta, Saxofon, Clarinete o Flauta traversa
                if (nombreInstrumentoViento.equalsIgnoreCase("Trompeta") || nombreInstrumentoViento.equalsIgnoreCase("Saxofon") || nombreInstrumentoViento.equalsIgnoreCase("Clarinete") || nombreInstrumentoViento.equalsIgnoreCase("Flauta traversa")){

                    StdOut.print("Ingrese el tipo de material del instrumento (Madera, metal):");
                    String materialInstrumento = StdIn.readString();

                    // Se verifica si el material es Madera, o Metal
                    if (materialInstrumento.equalsIgnoreCase("Madera") || materialInstrumento.equalsIgnoreCase("Metal")){

                        instrumentoPorAgregar = new Viento(nombreInstrumentoViento,materialInstrumento,codigo,precio,stock);
                        this.listaInstrumentosImpl[contInstrumentos] = instrumentoPorAgregar;
                        this.contInstrumentos++;
                        StdOut.println("Agregado con exito");

                    }else {
                        StdOut.println("El tipo de material ingresado no es valido. Intente agregar el instrumento nuevamente.");
                    }
                }
            }

        // Si el instrumento en general no fue valido, se le avisa al usuario
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
        StdOut.println("Vender instrumento:");
        //Opcional desplegar lista de instrumentos existentes
        StdOut.println("Ingrese el codigo del instrumento a vender");
        String codigo = StdIn.readInt();

        for (int i = 0; i < listaInstrumentosImpl.length; i++) {
            if (listaInstrumentosImpl[i].getCodigo() == codigo) {
                if (listaInstrumentosImpl[i].getStock() > 0) {


                }
                else {
                    StdOut.println("El instrumento solicitado no tiene stock disponible");
                    return;
                }
            }
            else {
                StdOut.println("No se encontro el codigo");
            }
        }

    }

    @Override
    /**
     * Busca un instrumento (Recorrer lista o especificar por codigo de instrumento)
     */
    public void consultarInventario() {

        StdOut.println();
        StdOut.print("""
                
                [*] Seleccione la opcion deseada al consultar
                    
                    [1] Buscar un instrumento ingresando su codigo
                    [2] Despliegue de los instrumentos por tipo
                    [3] Despliegue de todos los instrumentos 
                
                """);
        StdOut.print("Ingrese la opcion deseada");
        String opcion = StdIn.readString();

        switch (opcion){
            case "1" -> this.despliegueInstrumentoCodigo();
            case "2" -> this.despliegueInstrumentosTipo();
            case "3" -> this.despliegueInstrumentos();
            default -> StdOut.println("La opcion ingresada no es valida.");
        }
    }

    /**
     * Despliega el instrumento dado un codigo ingresado desde teclado.
     */
    public void despliegueInstrumentoCodigo(){

        StdOut.println("");
        StdOut.print("Ingrese el codigo del instrumento a buscar: ");
        int codigo = StdIn.readInt();

        for (int i = 0; i <this.listaInstrumentosImpl.length ; i++) {
            if (listaInstrumentosImpl[i].getCodigo() == codigo){
                if (listaInstrumentosImpl[i] instanceof Cuerda){
                    this.despliegueTipoCuerda(i);

                } else if (listaInstrumentosImpl[i] instanceof Percusion) {
                    this.despliegueTipoPercusion(i);

                } else if (listaInstrumentosImpl[i] instanceof Viento) {
                    this.despliegueTipoViento(i);
                }
            }else{
                StdOut.println("No fue encontrado ningun instrumento con el codigo ingresado.");
            }
        }
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
            for (int i = 0; i < this.listaInstrumentosImpl.length ; i++) {
                if (listaInstrumentosImpl[i] instanceof Cuerda){
                    this.despliegueTipoCuerda(i);
                }
            }
            
        } else if (tipoInstrumento.equalsIgnoreCase("Percusion")) {
            for (int i = 0; i < this.listaInstrumentosImpl.length ; i++) {
                if (listaInstrumentosImpl[i] instanceof Percusion){
                    this.despliegueTipoPercusion(i);
                }
            }
            
        } else if (tipoInstrumento.equalsIgnoreCase("Viento")) {
            for (int i = 0; i < this.listaInstrumentosImpl.length ; i++) {
                if (listaInstrumentosImpl[i] instanceof Viento){
                    this.despliegueTipoViento(i);
                }
            }
        } else {
            StdOut.println("El tipo de instrumento ingresado no es valido.");
        }
    }

    /**
     * Despliega el instrumento tipo cuerda dada una posicion.
     *
     * @param posicion
     */
    public void despliegueTipoCuerda(int posicion){

        StdOut.println("Tipo de instrumento:       Cuerda");
        StdOut.println("Nombre del instrumento:   " + listaInstrumentosImpl[posicion].getNombre());
        StdOut.println("Tipo de cuerda:           " + ((Cuerda) listaInstrumentosImpl[posicion]).getTipoCuerda());
        StdOut.println("Numero de cuerdas:        " + ((Cuerda) listaInstrumentosImpl[posicion]).getNumCuerdas());
        StdOut.println("Material de construccion: " + listaInstrumentosImpl[posicion].getMaterial());
        StdOut.println("Tipo de guitarra:         " + ((Cuerda) listaInstrumentosImpl[posicion]).getTipo());
        StdOut.println("");
    }

    /**
     * Despliega el instrumento tipo percusion dada una posicion.
     *
     * @param posicion
     */
    public void despliegueTipoPercusion(int posicion){

        StdOut.println("Tipo de instrumento:       Percusion");
        StdOut.println("Nombre del instrumento:   " + listaInstrumentosImpl[posicion].getNombre());
        StdOut.println("Tipo de percusion:        " + ((Percusion) listaInstrumentosImpl[posicion]).getPercusion());
        StdOut.println("Material de construccion: " + listaInstrumentosImpl[posicion].getMaterial());
        StdOut.println("Tipo de altura:           " + ((Percusion) listaInstrumentosImpl[posicion]).getAltura());
        StdOut.println("");
    }

    /**
     * Despliega el instrumento tipo viento dada una posicion.
     *
     * @param posicion
     */
    public void despliegueTipoViento(int posicion){

        StdOut.println("Tipo de instrumento:       Viento");
        StdOut.println("Nombre del instrumento:   " + listaInstrumentosImpl[posicion].getNombre());
        StdOut.println("Material de construccion: " + listaInstrumentosImpl[posicion].getMaterial());
        StdOut.println("");
        
    }

    /**
     * Despliega todos los instrumentos.
     */
    public void despliegueInstrumentos(){

        for (int i = 0; i < this.listaInstrumentosImpl.length ; i++) {
            if (listaInstrumentosImpl[i] instanceof Cuerda){
                this.despliegueTipoCuerda(i);
            } else if (listaInstrumentosImpl[i] instanceof Percusion) {
                this.despliegueTipoPercusion(i);
            } else if (listaInstrumentosImpl[i] instanceof Viento) {
                this.despliegueTipoViento(i);
            }
        }
    }

    /**
     *
     * @return contInstrumentos
     */
    public int getContInstrumentos() {
        return contInstrumentos;
    }
}
