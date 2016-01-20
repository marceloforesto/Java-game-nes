package LoloModel;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class BichoRosa extends Elemento {
    private int tipoPoder;
    private int iContaIntervalos;
    private Tela tTelaParaLancarFogo;
    
    public BichoRosa(String sNomeImagePNG, Tela t) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
        this.iContaIntervalos = 0;
        this.tTelaParaLancarFogo = t;
        this.tipoPoder = 0;
    }
    
    public void setPoder(int i) {
        tipoPoder = i;
    }

    public void autoDesenho(Graphics g) {
        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());

        this.iContaIntervalos++;
        if(this.iContaIntervalos == Consts.TIMER_FOGO){
            this.iContaIntervalos = 0;
            if (trocar == 1) {
                Fogo f = new Fogo("fire.png", this.tTelaParaLancarFogo);
                f.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()+1);
                f.trocar = trocar;
                this.tTelaParaLancarFogo.addElemento(f);
            }
            if (trocar == 2) {
                Fogo f = new Fogo("fireLado.png", this.tTelaParaLancarFogo);
                f.setPosicao(pPosicao.getLinha()+1, pPosicao.getColuna());
                f.trocar = trocar;
                this.tTelaParaLancarFogo.addElemento(f);
            }
        }
    } 

}