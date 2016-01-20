package LoloModel;

import Auxiliar.Desenho;
import java.awt.Graphics;
import java.io.Serializable;

public class BichoVerde extends Elemento {
    public BichoVerde (String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
    }
    
    public void autoDesenho(Graphics g) {
        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());
    } 
}