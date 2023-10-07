package sudoku;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import util.FlujoEntradaSalida;

/**
 *
 * @author Pablo Tomás Morinigo <tomasmorinigo098@gmail.com>
 */
public class formSudoku extends javax.swing.JFrame {

    private JPanel panel = new JPanel();
    private JTextField[][] cuadriculas = new JTextField[9][9];
    private JMenuBar mb = new JMenuBar();
    private JMenu mn = new JMenu("Iniciar");
    private JMenu mv = new JMenu("Ver");
    private JMenuItem miA, miB, miC, miD;
    private static Color colorBordes = new Color(36, 41, 41);
    private Color colorSudoku = new Color(255, 252, 163);
    private Sudoku sdk = new Sudoku();
    private int[][] sudokuACompletar;
    private int[][] sudokuResuelto;
    private Cronometro cronometro = new Cronometro();
    private formPosiciones frmPos;
    private String usuario, dificultad;

    public formSudoku() {
        initComponents();
        this.menu();
        this.crearSudoku();
    }

    private void menu() {
        this.setJMenuBar(this.mb);
        this.mb.add(this.mn);
        this.mb.add(this.mv);

        this.miA = new JMenuItem("Fácil");
        this.miA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFacilActionPerformed(evt);
            }
        });
        this.mn.add(this.miA);

        this.miB = new JMenuItem("Medio");
        this.miB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMedioActionPerformed(evt);
            }
        });
        this.mn.add(this.miB);

        this.miC = new JMenuItem("Difícil");
        this.miC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDificilActionPerformed(evt);
            }
        });
        this.mn.add(this.miC);
        
        this.miD = new JMenuItem("Tabla de posiciones");
        this.miD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPosicionesActionPerformed(evt);
            }
        });
        this.mv.add(this.miD);
    }

    private void crearSudoku() {

        this.panel.setLayout(new GridLayout(9, 9));
        this.panel.setBounds(80, 90, 500, 500);
        this.panel.setBorder(BorderFactory.createMatteBorder(8, 8, 6, 6, this.colorBordes));
        this.add(this.panel);

        for (int i = 0; i < this.cuadriculas.length; i++) {
            for (int j = 0; j < this.cuadriculas[i].length; j++) {

                this.cuadriculas[i][j] = new JTextField();
                this.cuadriculas[i][j].setBorder(new LineBorder(this.colorBordes, 1));
                this.cuadriculas[i][j].setHorizontalAlignment(JTextField.CENTER);
                this.cuadriculas[i][j].setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
                this.cuadriculas[i][j].setEnabled(false);
                this.cuadriculas[i][j].setBackground(this.colorSudoku);
                new Propiedades().sudokuKeyAdapter(this.cuadriculas, i, j);
                this.panel.add(this.cuadriculas[i][j]);

                if ((j + 1) % 3 == 0) {
                    this.cuadriculas[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, this.colorBordes));
                }
                if ((i + 1) % 3 == 0) {
                    this.cuadriculas[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, this.colorBordes));
                    if (j == 2 || j == 5 || j == 8) {
                        this.cuadriculas[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, this.colorBordes));
                    }
                }

            }
        }

        this.btnVerificar.setEnabled(false);

    }

    private void rellenarSudoku(int[][] sudoku) {
        this.limpiarCuadriculas();
        for (int i = 0; i < this.cuadriculas.length; i++) {
            for (int j = 0; j < this.cuadriculas[i].length; j++) {
                if (sudoku[i][j] != 0) {
                    this.cuadriculas[i][j].setText(String.valueOf(sudoku[i][j]));
                    this.cuadriculas[i][j].setEnabled(false);
                    this.cuadriculas[i][j].setDisabledTextColor(this.colorBordes);
                    this.cuadriculas[i][j].setBackground(this.colorSudoku);
                } else {
                    this.cuadriculas[i][j].setEnabled(true);
                    this.cuadriculas[i][j].setBackground(new Color(255, 255, 255));
                }
            }
        }
    }

    private int[][] sudokuUsuario() {
        int[][] sudokuUsuario = new int[9][9];
        for (int i = 0; i < this.cuadriculas.length; i++) {
            for (int j = 0; j < this.cuadriculas[i].length; j++) {
                if (!this.cuadriculas[i][j].getText().equals("")) {
                    sudokuUsuario[i][j] = Integer.parseInt((String) this.cuadriculas[i][j].getText());
                }
            }
        }
        return sudokuUsuario;
    }

    private boolean isResuelto(int[][] sudokuResuelto, int[][] sudokuUsuario) {
        if (Arrays.deepEquals(sudokuResuelto, sudokuUsuario)) {
            return true;
        }
        return false;
    }

    private void errores(int[][] sudokuResuelto, int[][] sudokuUsuario) {
        for (int i = 0; i < sudokuResuelto.length; i++) {
            for (int j = 0; j < sudokuResuelto[i].length; j++) {
                if (sudokuResuelto[i][j] != sudokuUsuario[i][j]) {
                    this.cuadriculas[i][j].setBackground(Color.red);
                    this.cuadriculas[i][j].setForeground(Color.white);
                }
            }
        }
    }

    private void habilitarCuadriculas(boolean estado) {
        for (int i = 0; i < this.cuadriculas.length; i++) {
            for (int j = 0; j < this.cuadriculas[i].length; j++) {
                this.cuadriculas[i][j].setEnabled(estado);
                this.cuadriculas[i][j].setDisabledTextColor(this.colorBordes);
                this.cuadriculas[i][j].setBackground(this.colorSudoku);
            }
        }
    }

    private boolean cuadriculasVacias() {
        for (int i = 0; i < this.cuadriculas.length; i++) {
            for (int j = 0; j < this.cuadriculas[i].length; j++) {
                if (this.cuadriculas[i][j].getText().equals("")) {
                    return true;
                }
            }
        }
        return false;
    }

    private void limpiarCuadriculas() {
        for (int i = 0; i < this.cuadriculas.length; i++) {
            for (int j = 0; j < this.cuadriculas[i].length; j++) {
                this.cuadriculas[i][j].setText("");
            }
        }
    }

    private void dificultad(String dificultad, int nivel) {
        frmUser frm = new frmUser(this, true);
        frm.getBtnConfirmar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frm.getBoxUsuario().getText().length() == 0) {
                    JOptionPane.showMessageDialog(frm, "Debes ingresar un nombre como jugador.", 
                                                  "Atención!!!", JOptionPane.WARNING_MESSAGE);
                } else {
                    usuario = frm.getBoxUsuario().getText();
                    if (isUsuarioRepetido(usuario)) {
                        JOptionPane.showMessageDialog(frm, "Ese nombre de usuario ya ha sido utilizado.", 
                                                  "Atención!!!", JOptionPane.WARNING_MESSAGE);
                        frm.getBoxUsuario().setText(null);
                    } else {
                        iniciarSudoku(dificultad, nivel);
                        frm.dispose();
                    }
                }
            }
        });
        frm.setVisible(true);
    }
    
    private void iniciarSudoku(String dificultad, int nivel) {
        this.dificultad = dificultad;
        this.setTitle("Sudoku 1.0 - Nivel: " + dificultad + " - Jugador: " + this.usuario);
        this.sudokuACompletar = this.sdk.generarSudoku(this.sdk.rellenarMatriz(new int[9][9]), nivel);
        this.rellenarSudoku(this.sudokuACompletar);
        this.sudokuResuelto = this.sdk.rellenarMatriz(this.sudokuUsuario());
        this.cronometro.iniciar();
        this.btnVerificar.setEnabled(true);
        this.mn.setEnabled(false);
        this.mv.setEnabled(false);
    }
    
    private boolean isUsuarioRepetido(String nombre) {
        ArrayList<TablaPosiciones> tabla_posiciones = new FlujoEntradaSalida("posiciones.ddr").getDeserializacion();
        for (int i = 0; i < tabla_posiciones.size(); i++) {
            if (tabla_posiciones.get(i).getJugador().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVerificar = new javax.swing.JButton();
        etqCronometro = this.cronometro.etqCronometro;
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        btnVerificar.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnVerificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnVerGris.png"))); // NOI18N
        new Propiedades().disenioBotones("/img/btnVerAma.png", "/img/btnVerAma.png", "/img/btnVerDis.png", btnVerificar);
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnVerificar);
        btnVerificar.setBounds(250, 600, 160, 50);

        etqCronometro.setFont(new java.awt.Font("Trebuchet MS", 0, 36)); // NOI18N
        etqCronometro.setForeground(this.colorSudoku);
        etqCronometro.setText("00:00:00.00");
        getContentPane().add(etqCronometro);
        etqCronometro.setBounds(229, 40, 220, 50);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setForeground(this.colorSudoku);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MiSudoku");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 10, 100, 31);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed

        this.sdk.muestraSudoku(this.sudokuACompletar);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        this.sdk.muestraSudoku(this.sudokuResuelto);

        if (this.isResuelto(this.sudokuResuelto, this.sudokuUsuario())) {
            
            this.cronometro.detener();
            System.out.println("SON IGUALES");
            JOptionPane.showMessageDialog(this, "FELICITACIONES " + this.usuario + "!!!\nCompletaste correctamente todo el sudoku.",
                    "Mensaje", JOptionPane.YES_OPTION, new javax.swing.ImageIcon(getClass().getResource("/img/yes.png")));
            this.habilitarCuadriculas(false);
            this.btnVerificar.setEnabled(false);
            this.mn.setEnabled(true);
            this.mv.setEnabled(true);
                 
            TablaPosiciones tabla = new TablaPosiciones();
            tabla.setJugador(this.usuario);
            tabla.setTiempo(this.etqCronometro.getText());
            tabla.setDificultad(this.dificultad);
            
            FlujoEntradaSalida es = new FlujoEntradaSalida("posiciones.ddr");
            es.abrirOutputStrem();
            es.setSerializarObjeto(tabla);
            es.cerrarOutputStream();
            
            this.frmPos.vaciarTabla();
            
            ArrayList<TablaPosiciones> tabla_posiciones = new FlujoEntradaSalida("posiciones.ddr").getDeserializacion();
            this.frmPos.setTabla(tabla_posiciones);
            
        } else {
            if (this.cuadriculasVacias()) {
                System.out.println("HAY CUADRICULAS VACIAS");
                JOptionPane.showMessageDialog(this, "Hay uno o varias cuadrículas vacías en el sudoku, \ncompletelas por favor.",
                        "Atención", JOptionPane.INFORMATION_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/img/atencion.png")));
            } else {
                System.out.println("NO SON IGUALES");
                JOptionPane.showMessageDialog(this, "Hay uno o varios errores en el sudoku, \ncorrigelos y vuelve a intentarlo",
                        "Error", JOptionPane.NO_OPTION, new javax.swing.ImageIcon(getClass().getResource("/img/no.png")));
                this.errores(this.sudokuResuelto, this.sudokuUsuario());
            }
        }

    }//GEN-LAST:event_btnVerificarActionPerformed

    private void itemFacilActionPerformed(java.awt.event.ActionEvent evt) {
        this.dificultad("Fácil", 1);
    }

    private void itemMedioActionPerformed(java.awt.event.ActionEvent evt) {
        this.dificultad("Medio", 2);
    }

    private void itemDificilActionPerformed(java.awt.event.ActionEvent evt) {
        this.dificultad("Difícil", 3);
    }
    
    private void itemPosicionesActionPerformed(java.awt.event.ActionEvent evt) {
        this.frmPos = new formPosiciones(this, true);
        this.frmPos.setLocationRelativeTo(null);
        this.frmPos.setVisible(true);
    }

    public static void main(String args[]) {
        new Propiedades().lookAndFeel("Windows");
        formSudoku frm = new formSudoku();
        frm.setBounds(0, 0, 655, 700);
        frm.setLocationRelativeTo(null);
        frm.setResizable(false);
        frm.setTitle("MiSudoku 1.0");
        frm.getContentPane().setBackground(colorBordes);
        frm.setVisible(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVerificar;
    protected javax.swing.JLabel etqCronometro;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
