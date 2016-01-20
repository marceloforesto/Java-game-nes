package LoloModel;

import Auxiliar.Consts;
import auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

public abstract class Elemento {
    public int trocar;
    public boolean isUp;
    public boolean isDown;
    public boolean isLeft;
    public boolean isRight;
    protected Image iImage;
    public Posicao pPosicao;
    protected boolean bTransponivel; /*Pode passar por cima?*/
    protected String nomeImagem;
    protected Elemento(String sNomeImagePNG) {
        this.trocar = 0;
        this.isUp = false;
        this.isDown = false;
        this.isLeft = false;
        this.isRight = false;
        this.nomeImagem = sNomeImagePNG;
        this.pPosicao = new Posicao(1,1);
        this.bTransponivel = true;
        try {
            iImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath()+Consts.PATH + sNomeImagePNG);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void setImage(String image) throws IOException {
        this.nomeImagem = image;
        iImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath()+Consts.PATH + image);
    }
    
    public String getImage () {
        return nomeImagem;
    }
    
    public Posicao getPosicao(){
        return pPosicao;
    }

    public boolean isbTransponivel() {
        return bTransponivel;
    }

    public void setbTransponivel(boolean bTransponivel) {
        this.bTransponivel = bTransponivel;
    }
    
    abstract public void autoDesenho(Graphics g);
    
    
    public boolean setPosicao(int linha, int coluna){
        return pPosicao.setPosicao(linha, coluna);
    }    
    public boolean moveUp(){
        isUp = true;
        isDown = false;
        isRight = false;
        isLeft = false;
        return this.pPosicao.moveUp();
    }
    public boolean moveDown(){
        isUp = false;
        isDown = true;
        isRight = false;
        isLeft = false;
        return this.pPosicao.moveDown();
    }
    public boolean moveRight(){
        isUp = false;
        isDown = false;
        isRight = true;
        isLeft = false;
        return this.pPosicao.moveRight();
    }
    public boolean moveLeft(){
        isUp = false;
        isDown = false;
        isRight = false;
        isLeft = true;
        return this.pPosicao.moveLeft();
    }
}
