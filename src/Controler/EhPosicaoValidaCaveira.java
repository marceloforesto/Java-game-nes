package Controler;

import LoloModel.Elemento;
import auxiliar.Posicao;
import java.util.ArrayList;
//Utiliza o Desgin Patterns bridge
public class EhPosicaoValidaCaveira implements EhPosicaoValida {
    ArrayList<Elemento> e;
    ControleDeJogo cj = new ControleDeJogo();
    public EhPosicaoValidaCaveira (ArrayList<Elemento> a, ControleDeJogo b) {
        this.e = a;
        this.cj =  b;
    }
    
    public boolean posicaoValida(Posicao p){
        Elemento eTemp;
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if (e.get(0).getPosicao().igual(p))
                cj.menosUmaVida = true;
            if(!eTemp.isbTransponivel() || eTemp.getImage() == "caixa.png")
                if(eTemp.getPosicao().igual(p))
                    return false;
        }        
        return true;
    }
}
