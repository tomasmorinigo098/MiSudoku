
package util;

import java.io.*;

/* debemos crear nuestro propio ObjectOutputStream para evitar conflictos con el método que 
sobreescribe la cabecera ( writeStreamHeader() )
 La cabecera son los flujo de salida que se van insertando en el archivo binario. Si no instanciamos 
 esta clase, el metodo writeStreamHeader() va a sobreescribir siempre el primero de ellos.
*/

public class MiObjectOutputStream extends ObjectOutputStream {
        
    //Constructores
    
    public MiObjectOutputStream(OutputStream out) throws IOException {
        super(out); //llama al constructor de la superclase (OutputStream: flujo de salida)
    }
    
    public MiObjectOutputStream() throws IOException, SecurityException {
        super(); //llama al constructor de la superclase (OutputStream)
    }
    
    //Sobrescribimos el método que crea la cabecera
    @Override
    protected void writeStreamHeader() throws IOException {
        // No hacemos nada.
    }
    
}
