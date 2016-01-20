package LoloModel;

import Auxiliar.Desenho;
import java.awt.Graphics;

public class Bau extends Elemento {
    public Bau (String sNomeImagePNG) {
        super(sNomeImagePNG);
    }
    
    public void autoDesenho(Graphics g) {
        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());
    } 
}
