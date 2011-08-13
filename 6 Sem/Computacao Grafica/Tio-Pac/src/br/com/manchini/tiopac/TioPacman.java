/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.manchini.tiopac;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author manchini
 */
public class TioPacman extends JPanel {

    boolean fechado = true;

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        grphcs.setColor(Color.black);
//        grphcs.clearRect(0, 0, this.getWidth(), this.getHeight());
        if (fechado) {
            desenarAberto(grphcs,Color.black);
            desenarFechado(grphcs,Color.yellow);
        } else {
             desenarFechado(grphcs,Color.black);
            desenarAberto(grphcs,Color.yellow);
        }
        fechado= !fechado;
    }

    private void desenarAberto(Graphics g,Color cor) {
        g.setColor(cor);
        g.fillArc(0, 0, 50, 50, 30, 300);
    }

    private void desenarFechado(Graphics g,Color cor) {
       g.setColor(cor);
        g.fillArc(0, 0, 50, 50, 5, 355);
    }
}
