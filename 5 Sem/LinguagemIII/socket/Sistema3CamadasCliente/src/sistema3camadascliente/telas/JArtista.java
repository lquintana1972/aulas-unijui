/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJDialog.java
 *
 * Created on 14/03/2011, 19:15:13
 */
package sistema3camadascliente.telas;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sistema3camadasbase.conexao.Mensagem;
import sistema3camadasbase.musica.artista.Artista;
import sistema3camadascliente.conexao.Cliente;
/**
 *
 * @author manchini
 */
public class JArtista extends javax.swing.JDialog {

    /** Creates new form NewJDialog */
    public JArtista(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jTable1.getColumnModel().removeColumn(jTable1.getColumn("Objeto"));
        atualizarTabela();
    }

    private void atualizarTabela() {
        try {
            Artista alb = new Artista();
            alb.setNome(jTextField_Filtro.getText());
            Mensagem msg = (Mensagem) Cliente.comando(Mensagem.TIPO_LISTAR, alb);
            ArrayList<Artista> lista = (ArrayList<Artista>) msg.getObjeto();
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            tm.setNumRows(0);
            for (Artista object : lista) {

                Object row[] = new Object[3];
                row[0] = object.getId();
                row[1] = object.getNome();
                row[2] = object;
                tm.addRow(row);
            }

        } catch (Exception ex) {
            Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void limparTela() {
        jTextField_Cod.setText("");
        jTextField_Nome.setText("");
    }


    public Artista getArtista(){
        return (Artista)jTable1.getModel().getValueAt(jTable1.getSelectedRow(), jTable1.getColumnCount());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Topo = new javax.swing.JPanel();
        jPanel_Center = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_Cod = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_Nome = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton_Delete = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_Filtro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro Artistas");

        jPanel_Topo.setPreferredSize(new java.awt.Dimension(400, 20));
        getContentPane().add(jPanel_Topo, java.awt.BorderLayout.PAGE_START);

        jPanel_Center.setLayout(new javax.swing.BoxLayout(jPanel_Center, javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText("Codigo:");
        jLabel1.setPreferredSize(new java.awt.Dimension(55, 18));
        jPanel1.add(jLabel1);

        jTextField_Cod.setEditable(false);
        jTextField_Cod.setEnabled(false);
        jTextField_Cod.setFocusable(false);
        jTextField_Cod.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel1.add(jTextField_Cod);

        jPanel_Center.add(jPanel1);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel2.setText("Nome:");
        jLabel2.setPreferredSize(new java.awt.Dimension(55, 18));
        jPanel2.add(jLabel2);

        jTextField_Nome.setPreferredSize(new java.awt.Dimension(300, 25));
        jPanel2.add(jTextField_Nome);

        jPanel_Center.add(jPanel2);

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jButton_Delete.setText("Delete");
        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });
        jPanel4.add(jButton_Delete);

        jButton3.setText("Limpar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);

        jPanel_Center.add(jPanel4);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Artistas"));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel3.setText("Filtro:");
        jLabel3.setPreferredSize(new java.awt.Dimension(55, 18));
        jPanel5.add(jLabel3);

        jTextField_Filtro.setPreferredSize(new java.awt.Dimension(300, 30));
        jTextField_Filtro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_FiltroKeyPressed(evt);
            }
        });
        jPanel5.add(jTextField_Filtro);

        jPanel3.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Objeto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel_Center.add(jPanel3);

        getContentPane().add(jPanel_Center, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-100)/2, (screenSize.height-296)/2, 100, 296);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Artista artista = new Artista();
        if (!jTextField_Cod.getText().equals("")) {
            artista.setId(Integer.valueOf(jTextField_Cod.getText()));
        }
        artista.setNome(jTextField_Nome.getText());
        try {
            Mensagem msg = (Mensagem) Cliente.comando(Mensagem.TIPO_INCLUIR, artista);
            JOptionPane.showMessageDialog(this, msg.getObjeto());

        } catch (Exception ex) {
            Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            atualizarTabela();
            limparTela();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        limparTela();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField_FiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_FiltroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            atualizarTabela();
        }

    }//GEN-LAST:event_jTextField_FiltroKeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            Artista artista = (Artista) jTable1.getModel().getValueAt(jTable1.getSelectedRow(), jTable1.getColumnCount());
            jTextField_Cod.setText(String.valueOf(artista.getId()));
            jTextField_Nome.setText(artista.getNome());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
        Artista artista = new Artista();
        if (!jTextField_Cod.getText().equals("")) {
            artista.setId(Integer.valueOf(jTextField_Cod.getText()));
        }
        artista.setNome(jTextField_Nome.getText());
        try {
            Mensagem msg = (Mensagem) Cliente.comando(Mensagem.TIPO_EXCLUIR, artista);
            JOptionPane.showMessageDialog(this, msg.getObjeto().toString());

        } catch (Exception ex) {
            Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            atualizarTabela();
            limparTela();
        }
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                JArtista dialog = new JArtista(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel_Center;
    private javax.swing.JPanel jPanel_Topo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_Cod;
    private javax.swing.JTextField jTextField_Filtro;
    private javax.swing.JTextField jTextField_Nome;
    // End of variables declaration//GEN-END:variables
}
