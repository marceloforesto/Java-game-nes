package Controler;

import LoloModel.BichoVerde;
import LoloModel.Elemento;
import LoloModel.Lolo;
import auxiliar.Posicao;
import java.awt.Graphics;
import java.util.ArrayList;

public class ControleDeJogo {
    public boolean novaFase;
    public boolean menosUmaVida;
    public int numeroDePoder;
    public int coracaosObtidos;
    public boolean fimDeJogo;
    public boolean proximaFase;
    public boolean trocarDeFase;
    public boolean caixaCima;
    public boolean caixaBaixo;
    public boolean caixaEsquerda;
    public boolean caixaDireita;
    public int a;
    public int b;
    
    public ControleDeJogo () {
        numeroDePoder = 0;
        coracaosObtidos = 0;
        novaFase = false;
        fimDeJogo = false;
        menosUmaVida = false;
        proximaFase = false;
        trocarDeFase = false;
        caixaCima = false;
        caixaBaixo = false;
        caixaEsquerda = false;
        caixaDireita = false;
    }
           
    public void desenhaTudo(ArrayList<Elemento> e, Graphics g){
        for(int i = 1; i < e.size(); i++){
            e.get(i).autoDesenho(g);
        }
        e.get(0).autoDesenho(g);
    }
    public void processaTudo(ArrayList<Elemento> e){
        Lolo lLolo = (Lolo)e.get(0);
        Elemento eTemp;
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if(lLolo.getPosicao().igual(eTemp.getPosicao()))
                if(eTemp.isbTransponivel()) {
                    
                    if (eTemp.getImage() == "fire.png" || eTemp.getImage() == "fireLado.png")
                        menosUmaVida = true;

                    if (eTemp.getImage() == "caixa.png") {
                        if (lLolo.isLeft)
                            eTemp.moveLeft();
                           
                        if (lLolo.isUp)
                            eTemp.moveUp();
                        
                        if (lLolo.isRight)
                            eTemp.moveRight();
                             
                        if (lLolo.isDown)
                            eTemp.moveDown();
                    }
                    
                    if (eTemp.getImage() == "coracao.png") {
                        coracaosObtidos += 1;
                        numeroDePoder = 2;
                    }    
                    
                    else if (eTemp.getImage() == "bauAberto.png")
                        proximaFase = true;                  
                    else if (eTemp.getImage() == "portaAberta.png")
                        novaFase = true;
                    else if (eTemp.getImage() == "escada.png") {
                        fimDeJogo = true;
                        lLolo.setPosicao(11, 6);
                    }    
                    if (eTemp.getImage() != "caveiraAcordada.png" && eTemp.getImage() != "caixa.png" && eTemp.getImage() != "bau.png" && eTemp.getImage() != "ponte.png" && eTemp.getImage() != "ponteLado.png" && eTemp.getImage() != "dragao.png" && eTemp.getImage() != "dragaoFrente.png")
                        e.remove(eTemp);
                }
        }
    }
    public void poder (ArrayList<Elemento> e) {
        Lolo lLolo = (Lolo)e.get(0);
        Elemento eTemp;
        for (int i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if ("ovo.png".equals(eTemp.getImage())) {
                a = eTemp.getPosicao().getLinha();
                b = eTemp.getPosicao().getColuna();
                b += -1;
                Posicao testeP0 = new Posicao(a, b);
                b += 2;
                Posicao testeP1 = new Posicao(a, b);
                b += -1;
                a += -1;
                Posicao testeP2 = new Posicao(a, b);
                a += 2;
                Posicao testeP3 = new Posicao(a, b);
                a += -1;

                if(lLolo.getPosicao().igual(testeP0) || lLolo.getPosicao().igual(testeP1) || lLolo.getPosicao().igual(testeP2) || lLolo.getPosicao().igual(testeP3)) {
                    e.remove(eTemp);
                    break;
                }
            }
            if ("bichoVerde.png".equals(eTemp.getImage()) || "caveira.png".equals(eTemp.getImage()) || "bichoRosa.png".equals(eTemp.getImage()) || "bichoRosa2.png".equals(eTemp.getImage())) {
                a = eTemp.getPosicao().getLinha();
                b = eTemp.getPosicao().getColuna();
                b += -1;
                Posicao testeP0 = new Posicao(a, b);
                b += 2;
                Posicao testeP1 = new Posicao(a, b);
                b += -1;
                a += -1;
                Posicao testeP2 = new Posicao(a, b);
                a += 2;
                Posicao testeP3 = new Posicao(a, b);
                a += -1;

                if(lLolo.getPosicao().igual(testeP0) || lLolo.getPosicao().igual(testeP1) || lLolo.getPosicao().igual(testeP2) || lLolo.getPosicao().igual(testeP3)) {
                    e.remove(eTemp);
                    BichoVerde novoBicho = new BichoVerde("ovo.png");
                    novoBicho.setPosicao(a, b);
                    e.add(novoBicho);
                    break;
                }
            }
        }
    }
}
