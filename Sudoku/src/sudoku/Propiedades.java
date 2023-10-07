
package sudoku;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 * 
 * @author Pablo Tomás Morinigo <tomasmorinigo098@gmail.com>
 */
public class Propiedades {
    
    protected void lookAndFeel(String tema) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (tema.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    protected void sudokuKeyAdapter(JTextField[][] box, int i, int j) {
        box[i][j].addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char caracter = evt.getKeyChar();
                if (box[i][j].getText().length() == 1) { //si se intenta escribir mas de una letra, bloqueamos.
                    evt.consume();
                }
                if ((caracter < '1') || (caracter > '9')) {
                    evt.consume();
                }
                box[i][j].setBackground(Color.white);
                box[i][j].setForeground(Color.black);
            }
        });
    }
    
    protected void disenioBotones(String urlA, String urlB, String urlC, javax.swing.JButton btn){
        btn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(urlA))); //lo que se ve al pasar el mouse (hover)
        btn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource(urlB))); //lo que se ve al presionar el boton
        btn.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource(urlC)));
        btn.setContentAreaFilled(false); //boton invisible
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
    }
    
}
