
package sudoku;

import javax.swing.JLabel;

/**
 * 
 * @author Pablo Tomás Morinigo <tomasmorinigo098@gmail.com>
 */
public class Cronometro implements Runnable {
    
    private Thread hilo;
    private int horas;
    private int minutos;
    private int segundos;
    private int milisegundos;
    private boolean activarCronometro;
    private String hor, min, seg, ms; 
    protected JLabel etqCronometro = new JLabel();

    @Override
    public void run() {
        this.horas = 0; this.minutos = 0; this.segundos = 0; this.milisegundos = 0;
        while (activarCronometro) {           
            try {
                Thread.sleep(9);
                
                this.milisegundos++;  
                if (this.milisegundos == 100) {
                    this.milisegundos = 0;
                    this.segundos++;
                }
                if (this.segundos == 60) {
                    this.segundos = 0;
                    this.minutos++;
                }
                if (this.minutos == 60) {
                    this.minutos = 0;
                    this.horas++;
                }
                
                if (this.horas < 10) {
                    this.hor = "0" + this.horas;
                } else {
                    this.hor = String.valueOf(this.horas);
                }
                if (this.minutos < 10) {
                    this.min = "0" + this.minutos;
                } else {
                    this.min = String.valueOf(this.minutos);
                }
                if (this.segundos < 10) {
                    this.seg = "0" + this.segundos;
                } else {
                    this.seg = String.valueOf(this.segundos);
                }
                if (this.milisegundos < 10) {
                    this.ms = "0" + this.milisegundos;
                } else {
                    this.ms = String.valueOf(this.milisegundos);
                }
                this.etqCronometro.setText(this.hor + ":" + this.min + ":" + this.seg + "." + this.ms);

                
            } catch (Exception ex) {}
        }
    }
    
    protected void iniciar() {
        this.activarCronometro = true;
        this.hilo = new Thread(this);
        this.hilo.start();
    }
    
    protected void detener() {
        this.activarCronometro = false;
    }

}
