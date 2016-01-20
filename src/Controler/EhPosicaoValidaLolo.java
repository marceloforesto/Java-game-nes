package Controler;

import LoloModel.Elemento;
import auxiliar.Posicao;
import java.util.ArrayList;
//Utiliza o Desgin Patterns bridge
public class EhPosicaoValidaLolo implements EhPosicaoValida{
    ArrayList<Elemento> e;
    
    public EhPosicaoValidaLolo (ArrayList<Elemento> a) {
        this.e = a;
    }

    public boolean posicaoValida(Posicao p) {
        Elemento eTemp;
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if(!eTemp.isbTransponivel())
                if(eTemp.getPosicao().igual(p))
                    return false;
        }        
        return true;
    }
}
