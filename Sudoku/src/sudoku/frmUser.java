
package sudoku;

import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author Pablo Tomás Morinigo <tomasmorinigo098@gmail.com>
 */
public class frmUser extends javax.swing.JDialog {

    public frmUser(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setTitle("Ingresar usuario");
        this.setBounds(0, 0, 400, 250);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(36, 41, 41));
        this.setResizable(false);
        initComponents();
    }

    public JTextField getBoxUsuario() {
        return boxUsuario;
    }

    public JButton getBtnConfirmar() {
        return btnConfirmar;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boxUsuario = new javax.swing.JTextField();
        btnConfirmar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 263));
        setMinimumSize(new java.awt.Dimension(400, 263));
        setPreferredSize(new java.awt.Dimension(400, 263));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        boxUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                boxUsuarioKeyTyped(evt);
            }
        });
        getContentPane().add(boxUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 340, 50));

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnComGris.png"))); // NOI18N
        new Propiedades().disenioBotones("/img/btnComAma.png", "/img/btnComAma.png", "/img/btnComDis.png", btnConfirmar);
        getContentPane().add(btnConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 180, 40));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("* Ingrese nombre de usuario:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 170, -1));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 252, 163));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MiSudoku 1.0");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 220, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boxUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_boxUsuarioKeyTyped
        char ch = evt.getKeyChar();
        if (ch == ' ') {
            evt.consume();
        }
        if ((ch < 'a' || ch > 'z') && (ch < 'A' || ch > 'Z') && (ch < '1' || ch > '9')) {
            evt.consume();
        }
        if (this.boxUsuario.getText().length() == 20) {
            evt.consume();
        }
    }//GEN-LAST:event_boxUsuarioKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        new Propiedades().lookAndFeel("Windows");

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmUser dialog = new frmUser(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField boxUsuario;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
