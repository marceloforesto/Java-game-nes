package LoloModel;

import Auxiliar.Desenho;
import java.awt.Graphics;
import java.io.Serializable;


public class Porta extends Elemento{
    public Porta (String sNomeImagePNG) {
        super(sNomeImagePNG);
        if (sNomeImagePNG != "portaAberta.png")
            this.bTransponivel = false;
    }
   
    
    public void autoDesenho(Graphics g) {
        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());
    } 
}
