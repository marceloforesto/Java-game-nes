package Auxiliar;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

public class Desenho {
    public static void desenhar(Graphics g, Image iImage, int iColuna, int iLinha){
        g.drawImage(iImage, iColuna * Consts.CELL_SIDE, iLinha * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);        
    }
}
