
package sudoku;

import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import util.FlujoEntradaSalida;

/**
 *
 * @author Pablo Tomás Morinigo <tomasmorinigo098@gmail.com>
 */
public class formPosiciones extends javax.swing.JDialog {
    
    private DefaultTableModel model;
    // c) deserializamos los objetos binarios que estan contenidos en el fichero
    private ArrayList<TablaPosiciones> tabla_posiciones = new FlujoEntradaSalida("posiciones.ddr").getDeserializacion();

    public formPosiciones(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setResizable(false);
        this.setTitle("MiSudoku 1.0 - Tabla de Posiciones");
        this.getContentPane().setBackground(new java.awt.Color(36, 41, 41));
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 650));
        setMinimumSize(new java.awt.Dimension(600, 650));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(600, 650));
        setResizable(false);
        getContentPane().setLayout(null);

        //b) establecemos nuestro modelo de tabla
    jTable1.setBackground(new java.awt.Color(36, 41, 41));
    jTable1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
    jTable1.setForeground(new java.awt.Color(255, 252, 163));
    jTable1.setModel(this.getTableModel());
    jTable1.setRowHeight(40);
    jTable1.setShowHorizontalLines(false);
    jTable1.setShowVerticalLines(false);
    //e) llamamos al metodo que vuelca los datos en la tabla y le asignamos como parametro el ArrayList tabla_posiciones
    this.setTabla(this.tabla_posiciones);

    jTable1.setCellSelectionEnabled(false);
    jTable1.setEnabled(false);

    jTable1.getTableHeader().setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
    jTable1.getTableHeader().setBackground(new java.awt.Color(36, 41, 41));
    jTable1.getTableHeader().setForeground(new java.awt.Color(255, 252, 163));
    jTable1.getTableHeader().setOpaque(false);
    jTable1.getTableHeader().setResizingAllowed(false); //deshabilitar la redimencion entre columnas
    jTable1.getTableHeader().setReorderingAllowed(false); //deshabilitar el desplazamiento entre columnas

    jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
    jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
    jScrollPane1.setViewportView(jTable1);

    jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
    jScrollPane1.getViewport().setBackground(new java.awt.Color(36, 41, 41));

    getContentPane().add(jScrollPane1);
    jScrollPane1.setBounds(20, 170, 560, 420);

    jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(255, 252, 163));
    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel2.setText("Tabla de Posiciones");
    getContentPane().add(jLabel2);
    jLabel2.setBounds(130, 70, 350, 31);

    pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * a) Obtenemos nuestro propio modelo de tabla
     * @return model
     */
    protected DefaultTableModel getTableModel() {
        Object[][] datos = new Object[][] {};
        String[] nombreColumnas = new String[]{"POS.", "JUGADOR", "TIEMPO", "DIFICULTAD"};
        this.model =  new DefaultTableModel(datos, nombreColumnas);
        return model;
    }
    
    protected void vaciarTabla() {
        try {
            this.model.setRowCount(0);
        } catch (NullPointerException ex) {
            System.out.println("NULL POINTER EXCEPTIOON");
        }
    }
    
    /**
     * d) Setteamos los datos del ArrayList<TablaPosiciones> a nuestra tabla
     * @param tabla_posiciones 
     */
    protected void setTabla(ArrayList<TablaPosiciones> tabla_posiciones) {
        Object[] fila = new Object[this.model.getColumnCount()]; 
        for (int i = 0; i < tabla_posiciones.size(); i++) {
            fila[0] = i + 1;
            fila[1] = tabla_posiciones.get(i).getJugador();
            fila[2] = tabla_posiciones.get(i).getTiempo();
            fila[3] = tabla_posiciones.get(i).getDificultad();
            model.addRow(fila);
        }
    }
    
    public static void main(String args[]) {
        new Propiedades().lookAndFeel("Windows");
        formPosiciones frm = new formPosiciones(new javax.swing.JFrame(), true);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
