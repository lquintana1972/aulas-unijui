/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogovelha.tabuleiro;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jogovelha.interfaces.Jogador;
import jogovelha.marcacao.Mensageiro;
import jogovelha.marcacao.Ponto;
import jogovelha.tela.TelaVelha;

/**
 *
 * @author rudieri
 */
public class Tabuleiro {

    private static final byte LINHAS = 3;
    private static final byte COLUNAS = 3;
    public static final byte JOGADOR_HUMANO = -1;
    public static final byte JOGADOR_COMPUTADOR = 1;
    public static final byte COMPUATADOR_VENCER = 2;
    public static final byte HUMANO_VENCER = -2;
    private byte vezDeJogar;
    private byte[][] tabuleiro;
    private byte casasRestantes;
    private TelaVelha telaVelha;
    private Jogador computador;
    private boolean bloqueio = true;

    public Tabuleiro(TelaVelha telaVelha, Jogador computador) {
        init(telaVelha, computador);
    }

    public Tabuleiro newInstance(){
        return new Tabuleiro(getTelaVelha(), computador);
    }
    private void init(TelaVelha telaVelha, Jogador computador) {
        this.telaVelha = telaVelha;
        this.computador = computador;

    }
    public ArrayList<Ponto> getPosicoesLivres(){
        ArrayList<Ponto> list = new ArrayList<Ponto>();
        for (byte i = 0; i < tabuleiro.length; i++) {
            byte[] bs = tabuleiro[i];
            for (byte j = 0; j < bs.length; j++) {
                if (bs[j]==0) {
                    list.add(new Ponto(i, j));
                }
                
            }
        }
        return  list;
    }
    public byte[][] getcloneMatriz(){
        return tabuleiro.clone();
    }       

    public void setComputador(Jogador _computador) {
        this.computador = _computador;
    }

    public void start(byte quemComeca) {
        bloqueio = false;
        vezDeJogar = quemComeca;
        tabuleiro = new byte[LINHAS][COLUNAS];
        inicializaMatriz();
        computador.novoJogo();
        if (vezDeJogar == JOGADOR_COMPUTADOR) {
            computador.comecar();
        }
    }

    public void setTelaVelha(TelaVelha telaVelha) {
        this.telaVelha = telaVelha;
    }

    private void inicializaMatriz() {
        tabuleiro = new byte[3][3];
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                tabuleiro[i][j] = 0;
            }
        }
        casasRestantes = 9;
    }

    public boolean jogar(byte jogador, final byte linha, final byte coluna) {
        if (bloqueio) {
            JOptionPane.showMessageDialog(getTelaVelha(), "Clique Novo Jogo para começar.");
            return false;
        }
        
        if (!estaLivre(linha, coluna)) {
            return false;
        }
        if (jogador == this.vezDeJogar) {
            setValue(jogador, linha, coluna);
        }
        if (isGameOver()) {
            getTelaVelha().acabou();
            bloqueio = true;
            computador.gameIsOver(vezDeJogar);
            return true;
        }
        if (!existemCasas()) {
            getTelaVelha().acabou();
            bloqueio=true;
        }
        this.vezDeJogar = (byte) -this.vezDeJogar;
        if (vezDeJogar == JOGADOR_COMPUTADOR) {
            new Thread(new Runnable() {

                public void run() {
                    try {
                        Thread.sleep(100);
                        computador.minhaVez(new Ponto((byte) linha, (byte) coluna));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Tabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
        }else{
            //
        }
        return true;
    }

    private void setValue(byte jogador, byte linha, byte coluna) {
        casasRestantes--;
        tabuleiro[linha][coluna] = this.vezDeJogar;
        getTelaVelha().setValor(jogador, linha, coluna);
    }

    public boolean estaLivre(byte linha, byte coluna) {
        if (linha==-1||coluna==-1) {
            return false;
        }
        return getTabuleiro()[linha][coluna] == 0;
    }
    public byte getDonoDoMeio(){
        return tabuleiro[1][1];
    }

    public boolean estaLivre(Ponto p) {
        return estaLivre(p.linha, p.coluna);
    }

    public Ponto verificarPossivelVencedor(int idWinJogador) {

        byte linhas;
        byte colunas;

        byte somaDiagonalPrincipal = 0;
        byte somaDiagonalSecundaria = 0;
        byte somaCols = 0;
        byte somaLinhas = 0;
        for (linhas = 0; linhas < LINHAS; linhas++) {
            somaCols = 0;
            somaLinhas = 0;
            somaDiagonalPrincipal += getTabuleiro()[linhas][linhas];
            somaDiagonalSecundaria += getTabuleiro()[linhas][2 - linhas];
            for (colunas = 0; colunas < COLUNAS; colunas++) {
                somaCols += getTabuleiro()[linhas][colunas];
                somaLinhas += getTabuleiro()[colunas][linhas];
            }
            if (somaCols == idWinJogador) {
                Ponto p = getPontoLivre(new Ponto(linhas, 0), new Ponto(linhas, 1), new Ponto(linhas, 2));
                if (p != null) {
                    return p;
                }

            }
            if (somaLinhas == idWinJogador) {
             
                Ponto p = getPontoLivre(new Ponto(0, linhas), new Ponto(1, linhas), new Ponto(2, linhas));
                if (p != null) {
                    return p;
                }

            }
        }

        if (somaDiagonalPrincipal == idWinJogador) {
            return getPontoLivre(new Ponto(0, 0), new Ponto(1, 1), new Ponto(2, 2));

        }
        if (somaDiagonalSecundaria == idWinJogador) {
            return getPontoLivre(new Ponto(0, 2), new Ponto(1, 1), new Ponto(2, 0));
        }
        return null;
    }

    private Ponto getPontoLivre(Ponto p1, Ponto p2, Ponto p3) {
        if (estaLivre(p1)) {
            return p1;
        } else if (estaLivre(p2)) {
            return p2;
        } else if (estaLivre(p3)) {
            return p3;
        } else {
            return null;
        }
    }

    private boolean isGameOver() {

        byte linhas;
        byte colunas;

        byte somaDiagonalPrincipal = 0;
        byte somaDiagonalSecundaria = 0;
        byte somaCols = 0;
        byte somaLinhas = 0;
        Mensageiro m = null;
        for (linhas = 0; linhas < LINHAS; linhas++) {
            somaCols = 0;
            somaLinhas = 0;
            somaDiagonalPrincipal += getTabuleiro()[linhas][linhas];
            somaDiagonalSecundaria += getTabuleiro()[linhas][2 - linhas];
            for (colunas = 0; colunas < COLUNAS; colunas++) {
                somaCols += getTabuleiro()[linhas][colunas];
                somaLinhas += getTabuleiro()[colunas][linhas];
            }
            if (Math.abs(somaCols) == 3) {
                m = new Mensageiro(vezDeJogar, new Ponto(linhas, 0), new Ponto(linhas, 1), new Ponto(linhas, 2));
                break;
            }
            if (Math.abs(somaLinhas) == 3) {
                m = new Mensageiro(vezDeJogar, new Ponto(0, linhas), new Ponto(1, linhas), new Ponto(2, linhas));
                break;
            }

        }
        if (Math.abs(somaDiagonalPrincipal) == 3) {
            m = new Mensageiro(vezDeJogar, new Ponto(0, 0), new Ponto(1, 1), new Ponto(2, 2));
        }
        if (Math.abs(somaDiagonalSecundaria) == 3) {
            m = new Mensageiro(vezDeJogar, new Ponto(0, 2), new Ponto(1, 1), new Ponto(2, 0));
        }
        if (m != null) {
            getTelaVelha().gameOver(m);
            return true;
        }

        return false;
    }

    public boolean existemCasas() {
        return casasRestantes > 0;
    }

    /**
     * @return the tabuleiro
     */
    public byte[][] getTabuleiro() {
        return tabuleiro;
    }

    public byte getDonoDoPonto(Ponto ponto) {
        return tabuleiro[ponto.linha][ponto.coluna];
    }

    /**
     * @return the telaVelha
     */
    public TelaVelha getTelaVelha() {
        return telaVelha;
    }
}
