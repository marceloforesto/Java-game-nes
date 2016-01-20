package LoloModel;

import Auxiliar.Desenho;
import java.awt.Graphics;
import java.io.Serializable;

public class Caixa extends Elemento {
    public Caixa (String sNomeImagePNG) {
        super(sNomeImagePNG);
    }
    
    public void autoDesenho(Graphics g) {
        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());
    } 
}
