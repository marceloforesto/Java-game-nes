package LoloModel;

import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

/**
 *
 * @author junio
 */
public class Fogo extends Elemento {
    private Tela tTelaOndeOFogoAnda;
    public Fogo(String sNomeImagePNG, Tela t) {
        super(sNomeImagePNG);
        tTelaOndeOFogoAnda = t;
    }

    @Override
    public void autoDesenho(Graphics g) {
        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());
        
        if (trocar == 1) {
            if(!this.moveRight())
                tTelaOndeOFogoAnda.removeElemento(this);        
        }
        if (trocar == 2) {
            if(!this.moveDown())
                tTelaOndeOFogoAnda.removeElemento(this);
        }
    }
    
}
