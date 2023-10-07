
package util;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import sudoku.TablaPosiciones;

/**
 * En este caso aplicaremos los flujos de entrada y salida que proporciona java para enviar (serializar) y 
 * recibir (deserializar) los objetos binarios entre un fichero y el programa. Para ello debemos utilizar 
 * las clases ObjectOutputStream y ObjectInputStream.
 * 
 * flujo de salida = output stream
 * flujo de entrada = input stream
 * 
 * @author Pablo Tomás Morinigo <tomasmorinigo098@gmail.com>
 */
public class FlujoEntradaSalida<T> {

    private File archivo;
    private ArrayList<T> lista;
    private ObjectOutputStream oos; // convierte en bytes los objetos que vamos a exportar al flujo de salida (serializacion)
    private ObjectInputStream ois; // convierte en objetos los bytes que vamos a importar del flujo de entrada (deserializacion)
    private FileOutputStream fos; // flujo de salida: almacena el proceso de serializacion en el archivo
    private FileInputStream fis; // flujo de entrada: obtiene el proceso de deserializacion
    
    
    public FlujoEntradaSalida(String ruta) {
        this.archivo = new File(ruta);
        this.lista = new ArrayList<>();
        // ni bien se haga una instancia de esta clase el constructor realiza:
        this.abrirInputStream();
        this.deserializarObjeto();
        this.cerrarInputStream();
        
    }
    
    /* retorna el arraylist de objetos deserializado */
    public ArrayList<T> getDeserializacion() {
        return lista;
    }
    
    /**
     * Abre el flujo de salida.
     * Si ya hay una cabecera escrita en el fichero binario escribimos en el archivo con MiObjectOutputStream.
     * Caso contrario si el archivo esta vacio escribimos con el ObjectOutputStream por defecto.
     */
    public void abrirOutputStrem() {
        if (archivo.exists() &&  archivo.length() > 0) { 
            try {
                oos = new MiObjectOutputStream(new FileOutputStream(archivo, true)); //true para que escriba debajo de la 1er cabecera
                System.out.println("Abriendo el flujo de salida para escribir una nueva cabecera...");
            } catch (IOException ex) {}
        } else {
            try {
                fos = new FileOutputStream(archivo);
                oos = new ObjectOutputStream(fos);
                System.out.println("Abriendo el flujo de salida para escribir la 1er cabecera...");
            } catch (IOException ex) {}
        }
    }
    
    /* recibe un objeto y lo serializa */
    public void setSerializarObjeto(T objeto) {
        if (oos != null) {
            try {
                oos.writeObject(objeto);
                System.out.println("El objeto fue serializado correctamente!");
            } catch (IOException ex) {}
        }
        this.lista.add(objeto); //añadimos el objeto al arraylist de objetos
    }
    
    /* cierra el flujo de salida */
    public void cerrarOutputStream() {
        if (oos != null) {
            try {
                oos.close();
                System.out.println("El flujo de salida se cerró correctamente!");
            } catch (IOException ex) {}
        }
    }
    
    /* abre el flujo de entrada */
    private void abrirInputStream() {
        try {
            fis = new FileInputStream(archivo);
            ois = new ObjectInputStream(fis);
            System.out.println("Abriendo el flujo de entrada para recuperar los datos serializados...");
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo todavia no fue creado!");
        } catch (IOException ex) {}
    }
    
    /* lee el archivo binario y realiza el proceso de deserializacion */
    private void deserializarObjeto() {
        if (ois != null) {
            try {
                int n = 0;
                while(true) { //recorremos el archivo de principio a fin
                    T objeto = (T) ois.readObject(); //leemos los objetos serializados y los deserializamos realizando un casting
                    this.lista.add(objeto); //añadimos el objeto al arraylist de objetos
                    this.ordenarJugadores((ArrayList<TablaPosiciones>) this.lista);
                    n++;
                    System.out.println("Recuperando la cabecera n°: " + n);
                }
            } catch (EOFException eof) {
                System.out.println("Ya no hay más datos para leer!");
            } catch (IOException | ClassNotFoundException ex) {}
        }
    }
    
    /* cierra el flujo de entrada */
    private void cerrarInputStream() {
        if (ois != null) {
            try {
                ois.close();
                System.out.println("El flujo de entrada se cerró correctamente!");
            } catch (IOException ex) {}
        }
    }
    
    public void ordenarJugadores(ArrayList<TablaPosiciones> tabla_posiciones) {
        java.util.Collections.sort(tabla_posiciones, new java.util.Comparator<TablaPosiciones>() {
            @Override
            public int compare(TablaPosiciones aI, TablaPosiciones aII) {
                return aI.getTiempo().compareTo(aII.getTiempo());
            }
        });
    }
    
}
