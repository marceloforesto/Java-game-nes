package LoloModel;

import Auxiliar.Desenho;
import java.awt.Graphics;
import java.io.Serializable;

public class Agua extends Elemento{
    public Agua (String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
    }
    
    public void autoDesenho(Graphics g) {
        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());
    } 
}
