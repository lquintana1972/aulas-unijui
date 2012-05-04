package com.aula.carrinho;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.SurfaceHolder;
import com.geomanalitica.utils.Vetorizador;
import com.geomanalitica.utils._2d.Ponto2D;
import com.geomanalitica.utils._2d.Vetor2D;
import java.util.ArrayList;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

class Sample4View extends SampleViewBase {

    public static final int ALTURA = 800;
    private Mat mYuv;
    private Mat mRgba;
    private Mat mGraySubmat;
    private Mat mIntermediateMat;
    private boolean pintar;
    private final TelaActivity tela;

    public Sample4View(Context context) {
        super(context);
        this.tela = (TelaActivity) context;
    }

    @Override
    public void surfaceChanged(SurfaceHolder _holder, int format, int width, int height) {
        super.surfaceChanged(_holder, format, width, height);

        synchronized (this) {
            // initialize Mats before usage
            mYuv = new Mat(getFrameHeight() + getFrameHeight() / 2, getFrameWidth(), CvType.CV_8UC1);
            mGraySubmat = mYuv.submat(0, getFrameHeight(), 0, getFrameWidth());

            mRgba = new Mat();
            mIntermediateMat = new Mat();
        }
    }
    byte contaPrint = 0;

    @Override
    protected Bitmap processFrame(byte[] data) {
        mYuv.put(0, 0, data);


        //Modo Canny
        Imgproc.Canny(mGraySubmat, mIntermediateMat, 80, 100);
        Imgproc.cvtColor(mIntermediateMat, mRgba, Imgproc.COLOR_GRAY2BGR, 4);


        Bitmap bmp = Bitmap.createBitmap(getFrameWidth(), getFrameHeight(), Bitmap.Config.ARGB_8888);

//        if (contaPrint++ == 10) {
        System.out.println("Print");
        contaPrint = 0;
        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                bmp.setPixel(i, j, Color.BLACK);
            }
        }
//        } else {
//            bmp.recycle();
//            return null;
//        }
        ArrayList<Ponto2D> pontos;
//            boolean[][] pretoBranco = converterParaPretoBranco(mRgba);
        pontos = Vetorizador.vetorizar(mRgba);
//        Utils.matToBitmap(mRgba, bmp);
//        if (Utils.matToBitmap(mRgba, bmp)) {

//        if (pintar) {

        if (pontos != null && pontos.size() > 1) {
//            System.out.println("Pontos: " + pontos.size());
        } else {
            return bmp;
        }
//        if (pontos == null || pontos.isEmpty()) {
//            bmp.recycle();
//            
//            return null;
//        }
//        bmp.prepareToDraw();

        ArrayList<Ponto2D> otm = new ArrayList<Ponto2D>(2);
        otm.add(pontos.get(0));
        otm.add(pontos.get(pontos.size() - 1));
        Vetor2D centro = new Vetor2D(new Ponto2D(mRgba.rows() / 2, 0), new Ponto2D(mRgba.rows() / 2, mRgba.cols()));
        Vetor2D vetor2D = new Vetor2D(pontos.get(0), pontos.get(pontos.size() - 1));
        final float anguloInterno = Vetor2D.anguloInterno(centro, vetor2D);
        System.out.println("Angulo: " + anguloInterno);

        int potenciaEsquerda = 99;
        int potenciaDireita = 99;
        float dif = anguloInterno - 90;
        if (dif > 0) {
            potenciaEsquerda -= dif * 3;
        } else {
            potenciaDireita += dif * 3;
        }
        tela.enviarPotencia(potenciaEsquerda, potenciaDireita);

        for (int i = 0; otm != null && i < otm.size() - 1; i++) {
            Ponto2D ponto = otm.get(i);
            Ponto2D proxPonto = otm.get(i + 1);
            linhaToBmp((int) ponto.getX(), (int) ponto.getY(), (int) proxPonto.getX(), (int) proxPonto.getY(), bmp);
        }
//            pintar = false;
        return bmp;
//        }
//        }
//
//        pintar = true;
//        bmp.recycle();
//        return null;
    }

    private byte sinal(int x) {
        return (byte) ((x < 0) ? -1 : ((x > 0) ? 1 : 0));
    }//macro que retorna o sinal de um número

    private void linhaToBmp(int x1, int y1, int x2, int y2, Bitmap bmp) {
        int dx, dy, sdx, sdy, px, py, dxabs, dyabs, i;
        float inclinacao;

        dx = x2 - x1;      /*
         * distância horizontal da linha
         */
        dy = y2 - y1;      /*
         * distância vertical da linha
         */
        dxabs = Math.abs(dx);
        dyabs = Math.abs(dy);
        sdx = sinal(dx);
        sdy = sinal(dy);

        if (dxabs >= dyabs) /*
         * a linha é mais horizontal que vertical
         */ {
            inclinacao = (float) dy / (float) dx;
            for (i = 0; i != dx; i += sdx) {
                px = i + x1;
                py = (int) (inclinacao * i + y1);    // dy=slope*delta_x
                bmp.setPixel(px, py, Color.RED);
            }
        } else /*
         * a linha é mais vertical que horizontal
         */ {
            inclinacao = (float) dx / (float) dy;
            for (i = 0; i != dy; i += sdy) {
                px = (int) (inclinacao * i + x1);
                py = i + y1;
                bmp.setPixel(px, py, Color.RED);
            }
        }
    }

    private boolean[][] converterParaPretoBranco(Mat mat) {
        boolean[][] pretoBranco = new boolean[mat.rows()][mat.cols()];
        final int colunas = mRgba.cols() / 2;
        for (int r = 0; r < mRgba.rows(); r++) {
            for (int c = 0; c < colunas; c++) {
                pretoBranco[r][c] = mRgba.get(r, c)[0] > 0;
            }
        }
        return pretoBranco;
    }

    @Override
    public void run() {
        super.run();

        synchronized (this) {
            // Explicitly deallocate Mats
            if (mYuv != null) {
                mYuv.release();
            }
            if (mRgba != null) {
                mRgba.release();
            }
            if (mGraySubmat != null) {
                mGraySubmat.release();
            }
            if (mIntermediateMat != null) {
                mIntermediateMat.release();
            }

            mYuv = null;
            mRgba = null;
            mGraySubmat = null;
            mIntermediateMat = null;
        }
    }

    public native void FindFeatures(long matAddrGr, long matAddrRgba);

    static {
        System.loadLibrary("mixed_sample");
    }
}
