package LoloModel;

import Auxiliar.Desenho;
import java.awt.Graphics;
import java.io.Serializable;

public class Lolo extends Elemento{

    public Lolo(String sNomeImagePNG) {
        super(sNomeImagePNG);
    }

    public void autoDesenho(Graphics g){
        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());
    }

    public void voltaAUltimaPosicao(){
        this.pPosicao.volta();
    }
}
