package LoloModel;

import Auxiliar.Desenho;
import java.awt.Graphics;
import java.io.Serializable;


public class Caveira extends Elemento{
    
    public Caveira (String sNomeImagePNG) {
        super(sNomeImagePNG);
        isRight = true;
    }
    
            
    public void autoDesenho(Graphics g){
        if (trocar == 1) {
            if(isLeft)
                this.getPosicao().setPosicao(pPosicao.getLinha(), pPosicao.getColuna()+1);
            
            else 
                this.getPosicao().setPosicao(pPosicao.getLinha(), pPosicao.getColuna()-1);
        }

        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());
    }
}   
