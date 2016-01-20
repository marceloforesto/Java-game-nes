package Controler;

import LoloModel.Elemento;
import auxiliar.Posicao;
import java.util.ArrayList;
//Utiliza o Desgin Patterns bridge
public class EhPosicaoValidaDragao implements EhPosicaoValida{
    ArrayList<Elemento> e;
    Elemento d;
    public EhPosicaoValidaDragao (ArrayList<Elemento> a, Elemento b) {
        this.e = a;
        this.d = b;
    }
    
    public boolean posicaoValida(Posicao p){
        Elemento eTemp;
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if (e.get(0).getPosicao().igual(p)) {
                d.pPosicao.volta();
                d.setbTransponivel(false);
                d.trocar = 0;
            }
            if(!eTemp.isbTransponivel() || eTemp.getImage() == "caixa.png")
                if(eTemp.getPosicao().igual(p))
                    return false;
        }        
        return true;
    }
}
