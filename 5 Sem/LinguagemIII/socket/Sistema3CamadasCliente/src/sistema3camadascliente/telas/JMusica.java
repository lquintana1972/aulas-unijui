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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sistema3camadasbase.conexao.Mensagem;
import sistema3camadasbase.musica.Musica;
import sistema3camadasbase.musica.album.Album;
import sistema3camadasbase.musica.artista.Artista;
import sistema3camadasbase.musica.genero.Genero;
import sistema3camadascliente.conexao.Cliente;
import sistema3camadascliente.telas.JArtista;
import sistema3camadascliente.telas.Preferencias;

/**
 *
 * @author manchini
 */
public class JMusica extends javax.swing.JDialog {

    private Artista artista;
    private Album album;
    private Genero genero;

    /** Creates new form NewJDialog */
    public JMusica(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        Preferencias p = new Preferencias(parent, true);
        HashMap mostrar = p.mostrar();
        Cliente.setIp((String) mostrar.get("ip"));
        Cliente.setPorta(Integer.parseInt(mostrar.get("porta").toString()));
        
        initComponents();
        jTable1.getColumnModel().removeColumn(jTable1.getColumn("Objeto"));
        atualizarTabela();
    }

    private void atualizarTabela() {
        try {
            Musica alb = new Musica();
            alb.setNome(jTextField_Filtro.getText());
            Mensagem msg = (Mensagem) Cliente.comando(Mensagem.TIPO_LISTAR, alb);
            ArrayList<Musica> lista = (ArrayList<Musica>) msg.getObjeto();
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            tm.setNumRows(0);
            for (Musica object : lista) {

                Object row[] = new Object[3];
                row[0] = object.getId();
                row[1] = object.getNome();
                row[2] = object;
                tm.addRow(row);
            }

        } catch (Exception ex) {
            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void limparTela() {
        jTextField_Cod.setText("");
        jTextField_Nome.setText("");

        jTextField_CodArtista.setText("");
        jTextField_NomeArtista.setText("");
        artista = null;

        jTextField_CodAlbum.setText("");
        jTextField_NomeAlbum.setText("");
        album = null;

        jTextField_CodGenero.setText("");
        jTextField_NomeGenero.setText("");
        genero = null;
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
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_CodArtista = new javax.swing.JTextField();
        jTextField_NomeArtista = new javax.swing.JTextField();
        jButton_Artista = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField_CodAlbum = new javax.swing.JTextField();
        jTextField_NomeAlbum = new javax.swing.JTextField();
        jButton_Album = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_CodGenero = new javax.swing.JTextField();
        jTextField_NomeGenero = new javax.swing.JTextField();
        jButton_Genero = new javax.swing.JButton();
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
        setTitle("Cadastro Musica");

        jPanel_Topo.setPreferredSize(new java.awt.Dimension(400, 20));
        getContentPane().add(jPanel_Topo, java.awt.BorderLayout.PAGE_START);

        jPanel_Center.setLayout(new javax.swing.BoxLayout(jPanel_Center, javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText("Codigo:");
        jLabel1.setPreferredSize(new java.awt.Dimension(60, 18));
        jPanel1.add(jLabel1);

        jTextField_Cod.setEditable(false);
        jTextField_Cod.setEnabled(false);
        jTextField_Cod.setFocusable(false);
        jTextField_Cod.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel1.add(jTextField_Cod);

        jPanel_Center.add(jPanel1);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel2.setText("Nome:");
        jLabel2.setPreferredSize(new java.awt.Dimension(60, 18));
        jPanel2.add(jLabel2);

        jTextField_Nome.setPreferredSize(new java.awt.Dimension(300, 25));
        jPanel2.add(jTextField_Nome);

        jPanel_Center.add(jPanel2);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel4.setText("Artista:");
        jLabel4.setPreferredSize(new java.awt.Dimension(60, 18));
        jPanel6.add(jLabel4);

        jTextField_CodArtista.setEditable(false);
        jTextField_CodArtista.setFocusable(false);
        jTextField_CodArtista.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel6.add(jTextField_CodArtista);

        jTextField_NomeArtista.setEditable(false);
        jTextField_NomeArtista.setFocusable(false);
        jTextField_NomeArtista.setPreferredSize(new java.awt.Dimension(245, 25));
        jPanel6.add(jTextField_NomeArtista);

        jButton_Artista.setText("...");
        jButton_Artista.setPreferredSize(new java.awt.Dimension(28, 28));
        jButton_Artista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ArtistaActionPerformed(evt);
            }
        });
        jPanel6.add(jButton_Artista);

        jPanel_Center.add(jPanel6);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel5.setText("Album:");
        jLabel5.setPreferredSize(new java.awt.Dimension(60, 18));
        jPanel7.add(jLabel5);

        jTextField_CodAlbum.setEditable(false);
        jTextField_CodAlbum.setFocusable(false);
        jTextField_CodAlbum.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel7.add(jTextField_CodAlbum);

        jTextField_NomeAlbum.setEditable(false);
        jTextField_NomeAlbum.setFocusable(false);
        jTextField_NomeAlbum.setPreferredSize(new java.awt.Dimension(245, 25));
        jPanel7.add(jTextField_NomeAlbum);

        jButton_Album.setText("...");
        jButton_Album.setPreferredSize(new java.awt.Dimension(28, 28));
        jButton_Album.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AlbumActionPerformed(evt);
            }
        });
        jPanel7.add(jButton_Album);

        jPanel_Center.add(jPanel7);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel6.setText("Genero:");
        jLabel6.setPreferredSize(new java.awt.Dimension(60, 18));
        jPanel8.add(jLabel6);

        jTextField_CodGenero.setEditable(false);
        jTextField_CodGenero.setFocusable(false);
        jTextField_CodGenero.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel8.add(jTextField_CodGenero);

        jTextField_NomeGenero.setEditable(false);
        jTextField_NomeGenero.setFocusable(false);
        jTextField_NomeGenero.setPreferredSize(new java.awt.Dimension(245, 25));
        jPanel8.add(jTextField_NomeGenero);

        jButton_Genero.setText("...");
        jButton_Genero.setPreferredSize(new java.awt.Dimension(28, 28));
        jButton_Genero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GeneroActionPerformed(evt);
            }
        });
        jPanel8.add(jButton_Genero);

        jPanel_Center.add(jPanel8);

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
        setBounds((screenSize.width-542)/2, (screenSize.height-540)/2, 542, 540);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Musica musica = new Musica();
        if (!jTextField_Cod.getText().equals("")) {
            musica.setId(Integer.valueOf(jTextField_Cod.getText()));
        }
        musica.setNome(jTextField_Nome.getText());
        musica.setAutor(artista);
        musica.setAlbum(album);
        musica.setGenero(genero);
        try {
            Mensagem msg = (Mensagem) Cliente.comando(Mensagem.TIPO_INCLUIR, musica);
            JOptionPane.showMessageDialog(this, msg.getObjeto());

        } catch (Exception ex) {
            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
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
            limparTela();
            Musica musica = (Musica) jTable1.getModel().getValueAt(jTable1.getSelectedRow(), jTable1.getColumnCount());
            if (musica != null) {
                try {
                    jTextField_Cod.setText(String.valueOf(musica.getId()));
                    jTextField_Nome.setText(musica.getNome());
                    if (musica.getAutor() != null) {
                        artista = musica.getAutor();
                        jTextField_CodArtista.setText(String.valueOf(artista.getId()));
                        jTextField_NomeArtista.setText(artista.getNome());
                    }
                    if (musica.getAlbum() != null) {
                        album = musica.getAlbum();
                        jTextField_CodAlbum.setText(String.valueOf(album.getId()));
                        jTextField_NomeAlbum.setText(album.getNome());
                    }
                    if (musica.getGenero() != null) {
                        genero =musica.getGenero();
                        jTextField_CodGenero.setText(String.valueOf(genero.getId()));
                        jTextField_NomeGenero.setText(genero.getNome());
                    }
                } catch (Exception ex) {
                    Logger.getLogger(JMusica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
        Musica musica = new Musica();
        if (!jTextField_Cod.getText().equals("")) {
            musica.setId(Integer.valueOf(jTextField_Cod.getText()));
        }
        musica.setNome(jTextField_Nome.getText());
        try {
            Mensagem msg = (Mensagem) Cliente.comando(Mensagem.TIPO_EXCLUIR, musica);
            JOptionPane.showMessageDialog(this, msg.getObjeto().toString());
        } catch (Exception ex) {
            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            atualizarTabela();
            limparTela();
        }
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jButton_ArtistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ArtistaActionPerformed
        JArtista jArtista = new JArtista(null, true);
        jArtista.setVisible(true);
        artista = jArtista.getArtista();
        if (artista != null) {
            jTextField_CodArtista.setText(String.valueOf(artista.getId()));
            jTextField_NomeArtista.setText(artista.getNome());
        }
    }//GEN-LAST:event_jButton_ArtistaActionPerformed

    private void jButton_AlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AlbumActionPerformed

        JAlbum jAlbum = new JAlbum(null, true);
        jAlbum.setVisible(true);
        album = jAlbum.getAlbum();
        if (album != null) {
            jTextField_CodAlbum.setText(String.valueOf(album.getId()));
            jTextField_NomeAlbum.setText(album.getNome());
        }
    }//GEN-LAST:event_jButton_AlbumActionPerformed

    private void jButton_GeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GeneroActionPerformed
        JGenero jGenero = new JGenero(null, true);
        jGenero.setVisible(true);
        genero = jGenero.getGenero();
        if (genero != null) {
            jTextField_CodGenero.setText(String.valueOf(genero.getId()));
            jTextField_NomeGenero.setText(genero.getNome());
        }
    }//GEN-LAST:event_jButton_GeneroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                JMusica dialog = new JMusica(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_Album;
    private javax.swing.JButton jButton_Artista;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JButton jButton_Genero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel_Center;
    private javax.swing.JPanel jPanel_Topo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_Cod;
    private javax.swing.JTextField jTextField_CodAlbum;
    private javax.swing.JTextField jTextField_CodArtista;
    private javax.swing.JTextField jTextField_CodGenero;
    private javax.swing.JTextField jTextField_Filtro;
    private javax.swing.JTextField jTextField_Nome;
    private javax.swing.JTextField jTextField_NomeAlbum;
    private javax.swing.JTextField jTextField_NomeArtista;
    private javax.swing.JTextField jTextField_NomeGenero;
    // End of variables declaration//GEN-END:variables
}
