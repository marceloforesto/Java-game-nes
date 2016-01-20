package LoloModel;

import Auxiliar.Desenho;
import java.awt.Graphics;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Fantasma extends Elemento {

    public Fantasma (String sNomeImagePNG) {
        super(sNomeImagePNG);
        bTransponivel = false;
        isUp = true;
    }
    public void autoDesenho(Graphics g){
        if(isUp) {   
            try {
                isUp = false;
                this.getPosicao().setPosicao(pPosicao.getLinha()+1, pPosicao.getColuna());
                this.setImage("fantasma.png");
            } catch (IOException ex) {
                Logger.getLogger(Dragao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            try {
                isUp = true;
                this.getPosicao().setPosicao(pPosicao.getLinha()-1, pPosicao.getColuna());
                this.setImage("fantasmaAtras.png");
            } catch (IOException ex) {
                Logger.getLogger(Dragao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());
    }
}   
