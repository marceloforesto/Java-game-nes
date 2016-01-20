package LoloModel;

import Auxiliar.Desenho;
import java.awt.Graphics;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Dragao extends Elemento {

    public Dragao (String sNomeImagePNG) {
        super(sNomeImagePNG);
        isUp = true;
    }
    public void autoDesenho(Graphics g){
        if (trocar == 0) {
            try {
                this.setImage("dragaoDormindo.png");
                } catch (IOException ex) {
                    Logger.getLogger(Dragao.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        if (trocar == 1) {
            if(isUp) {   
                try {
                    this.getPosicao().setPosicao(pPosicao.getLinha()+1, pPosicao.getColuna());
                    this.setImage("dragaoFrente.png");
                } catch (IOException ex) {
                    Logger.getLogger(Dragao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                try {
                   this.getPosicao().setPosicao(pPosicao.getLinha()-1, pPosicao.getColuna());
                    this.setImage("dragao.png");
                } catch (IOException ex) {
                    Logger.getLogger(Dragao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
        }
        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());
    }
}   