
package sudoku;

/**
 * 
 * @author Pablo Tomás Morinigo <tomasmorinigo098@gmail.com>
 */
public class TablaPosiciones implements java.io.Serializable {
    
    private String jugador, tiempo, dificultad;
    
    public TablaPosiciones() {}

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
    
}
