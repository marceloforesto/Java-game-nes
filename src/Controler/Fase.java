package Controler;

import LoloModel.Ponte;
import LoloModel.Agua;
import LoloModel.Bau;
import LoloModel.BichoRosa;
import LoloModel.BichoVerde;
import LoloModel.Caixa;
import LoloModel.Caveira;
import LoloModel.Coracao;
import LoloModel.Dragao;
import LoloModel.Fantasma;
import LoloModel.Grama;
import LoloModel.Parede;
import LoloModel.Pedra;
import LoloModel.Porta;
import java.io.IOException;
//Esta classe utiliza o Desing Patterns SINGLETON, pode ser observado nas linhas 25 e 29 do código
public class Fase {
    private static Fase instance;
    public Tela novaTela= new Tela();
    private int fase;
    private int vida;
    
    private Fase () {
      // Existe apenas para evitar instanciação.
    }
    
    public static Fase instance() {
        if(instance == null)
            instance = new Fase();
        return instance;    
    }
    //SERVE APENAS PARA TROCAR DE FASE
    public void trocarDeFase() {
        instance = null;
    }

    public void setVidas(int a) {
        this.vida = a;
    }
    
    public int getVidas () {
        return this.vida;
    }
    
    public void setFase (int i) {
        this.fase = i;
    }
    
    public int getFase () {
        return this.fase;
    }
    

    private void inicarParede () {
        Parede vidaImg = new Parede("vidas.png");
        vidaImg.setPosicao(0, 13);
        novaTela.addElemento(vidaImg);

        Parede poderOvoImg = new Parede("poderOvo.png");
        poderOvoImg.setPosicao(2, 13);
        novaTela.addElemento(poderOvoImg);
        
        for (int i = 4; i < 13; i++) {
                Parede paredeFrente = new Parede("paredeLateral2.png");
                paredeFrente.setPosicao(i, 13);
                novaTela.addElemento(paredeFrente);
        }
        
        for (int i = 0; i < 13; i++) {
                Parede paredeFrente = new Parede("paredeFrente1.png");
                paredeFrente.setPosicao(0, i);
                novaTela.addElemento(paredeFrente);
        }
        
        for (int i = 0; i < 14; i++) {
                Parede paredeFrente = new Parede("paredeFrente1.png");
                paredeFrente.setPosicao(13, i);
                novaTela.addElemento(paredeFrente);
        }
        
        for (int i = 0; i < 13; i++) {
                if (fase == 1) {
                    if (i != 7){
                        Parede paredeFrente2 = new Parede("paredeFrente2.png");
                        paredeFrente2.setPosicao(1, i);
                        novaTela.addElemento(paredeFrente2);
                    }    
                }
                
                if (fase == 2) {
                    if (i != 10){
                        Parede paredeFrente2 = new Parede("paredeFrente2.png");
                        paredeFrente2.setPosicao(1, i);
                        novaTela.addElemento(paredeFrente2);
                    }    
                }
                
                if (fase == 3) {
                    if (i != 5){
                        Parede paredeFrente2 = new Parede("paredeFrente2.png");
                        paredeFrente2.setPosicao(1, i);
                        novaTela.addElemento(paredeFrente2);
                    }  
                }
                
                if (fase == 4) {
                    if (i != 6){
                        Parede paredeFrente2 = new Parede("paredeFrente2.png");
                        paredeFrente2.setPosicao(1, i);
                        novaTela.addElemento(paredeFrente2);
                    }  
                }
                
                if (fase == 5) {
                    Parede paredeFrente2 = new Parede("paredeFrente2.png");
                    paredeFrente2.setPosicao(1, i);
                    novaTela.addElemento(paredeFrente2);
                }
                
        }
        
        for (int i = 0; i < 14; i++) {
                Parede paredeAtras = new Parede("paredeAtras.png");
                paredeAtras.setPosicao(12, i);
                novaTela.addElemento(paredeAtras);
        }
        
        for (int i = 1; i < 12; i++) {
                Parede paredeLateral = new Parede("paredeLateral.png");
                paredeLateral.setPosicao(i, 0);
                novaTela.addElemento(paredeLateral);
        }
        
        for (int i = 1; i < 12; i++) {
                Parede paredeLateral2 = new Parede("paredeLateral2.png");
                paredeLateral2.setPosicao(i, 12);
                novaTela.addElemento(paredeLateral2);
        }
    }
       
    public void inicarFase (int definir) {
        inicarParede();
        novaTela.auto.setTempo(definir);
        if (fase  == 1) {
            novaTela.setPosicaoLolo(6, 1);
            
            BichoVerde novoVerde = new BichoVerde("bichoVerde.png");
            novoVerde.setPosicao(7, 7);
            novaTela.addElemento(novoVerde);
            
            Porta novaPorta = new Porta("porta.png");
            novaPorta.setPosicao(1, 7);
            novaTela.addElemento(novaPorta);
            
            Coracao novoCoracao = new Coracao("coracao.png");
            novoCoracao.setPosicao(6, 11);
            novaTela.addElemento(novoCoracao);
            
            Coracao novoCoracao2 = new Coracao("coracao.png");
            novoCoracao2.setPosicao(3, 5);
            novaTela.addElemento(novoCoracao2);
            
            Bau novoBau = new Bau("bau.png");
            novoBau.setPosicao(10, 5);
            novaTela.addElemento(novoBau);
            
            
            //INICIA AS PEFRAS
            for (int i = 1; i < 12; i ++) {
                if (i != 7 && i != 10 && i != 11) {
                    Pedra novaPedra = new Pedra("pedra.png");
                    novaPedra.setPosicao(2, i);
                    novaTela.addElemento(novaPedra);
                }
            }
            
            for (int i = 1; i < 12; i ++) {
                if (i != 2 && i != 3 && i != 5 && i != 6 && i != 7 && i != 10 && i != 11) {
                    Pedra novaPedra = new Pedra("pedra.png");
                    novaPedra.setPosicao(3, i);
                    novaTela.addElemento(novaPedra);
                }
            }
            
            for (int i = 1; i < 12; i ++) {
                if (i != 1 && i != 2 && i != 3 && i != 7 && i != 11) {
                    Pedra novaPedra = new Pedra("pedra.png");
                    novaPedra.setPosicao(4, i);
                    novaTela.addElemento(novaPedra);
                }
            }
            
            for (int i = 1; i < 12; i ++) {
                if (i != 1 && i != 2 && i != 3 && i != 4 && i != 7 && i != 11) {
                    Pedra novaPedra = new Pedra("pedra.png");
                    novaPedra.setPosicao(5, i);
                    novaTela.addElemento(novaPedra);
                }
            }
            
            for (int i = 1; i < 12; i ++) {
                if (i != 1 && i != 2 && i != 3 && i != 4 && i != 7 && i != 10 && i != 11) {
                    Pedra novaPedra = new Pedra("pedra.png");
                    novaPedra.setPosicao(6, i);
                    novaTela.addElemento(novaPedra);
                }
            }
            
            Pedra novaPedra = new Pedra("pedra.png");
            novaPedra.setPosicao(7, 9);
            novaTela.addElemento(novaPedra);
            
            Pedra novaPedra2 = new Pedra("pedra.png");
            novaPedra2.setPosicao(10, 1);
            novaTela.addElemento(novaPedra2);
           
            Pedra novaPedra3 = new Pedra("pedra.png");
            novaPedra3.setPosicao(10, 4);
            novaTela.addElemento(novaPedra3);
            
            for (int i = 1; i < 7; i ++) {     
              Pedra novaPedra4 = new Pedra("pedra.png");
              novaPedra4.setPosicao(11, i);
              novaTela.addElemento(novaPedra4);    
            }
   
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j <12; j ++) {
                    if (i == 3 && j == 2 || i == 3 && j == 3 || i == 4 && j == 2 || i == 4 && j == 3 || i == 5 && j == 3 || i == 5 && j == 4 || i == 8 && j == 2 || i == 8 && j == 3 || i == 10 && j == 2 || i == 10 && j == 3 || i == 9 && j == 1 || i == 9 && j == 2 || i == 9 && j == 3 || i == 9 && j == 4 || i == 9 && j == 8 || i == 9 && j == 9 || i == 10 && j == 9 || i == 10 && j == 10 || i == 6 && j == 10 || i == 3 && j == 10 || i == 3 && j == 11 || i == 2 && j == 10 || i == 2 && j == 11 || i == 5 && j == 11 || i == 4 && j == 11)  {
                        Grama novaGrama = new Grama("grama.png");
                        novaGrama.setPosicao(i, j);
                        novaTela.addElemento(novaGrama);
                    }
                }
            }
        }
        
        if (fase == 2) {
            novaTela.setPosicaoLolo(11, 5);
            
            BichoRosa novoRosa = new BichoRosa("bichoRosa.png", novaTela);
            novoRosa.setPosicao(2, 4);
            novaTela.addElemento(novoRosa);
            
            BichoRosa novoRosa2 = new BichoRosa("bichoRosa2.png", novaTela);
            novoRosa2.setPosicao(10, 1);
            novaTela.addElemento(novoRosa2);
            
            for (int i = 5; i < 7; i++) {
                Ponte novaPonte = new Ponte("ponte.png");
                novaPonte.setPosicao(i, 5);
                novaTela.addElemento(novaPonte);
            }    
            
            for (int i = 5; i < 7; i++) {
                Ponte novaPonte = new Ponte("ponte.png");
                novaPonte.setPosicao(i, 10);
                novaTela.addElemento(novaPonte);
            }
         
            Coracao novoCoracao = new Coracao("coracao.png");
            novoCoracao.setPosicao(3, 10);
            novaTela.addElemento(novoCoracao);
            
            Coracao novoCoracao3 = new Coracao("coracao.png");
            novoCoracao3.setPosicao(11, 1);
            novaTela.addElemento(novoCoracao3);
            
            Coracao novoCoracao4 = new Coracao("coracao.png");
            novoCoracao4.setPosicao(9, 8);
            novaTela.addElemento(novoCoracao4);
            
            Coracao novoCoracao2 = new Coracao("coracao.png");
            novoCoracao2.setPosicao(3, 2);
            novaTela.addElemento(novoCoracao2);
            
            Bau novoBau = new Bau("bau.png");
            novoBau.setPosicao(7, 1);
            novaTela.addElemento(novoBau);
            
            Porta novaPorta = new Porta("porta.png");
            novaPorta.setPosicao(1, 10);
            novaTela.addElemento(novaPorta);
            
            for (int i = 6; i < 9; i ++) {
                    Pedra novaPedra = new Pedra("pedra.png");
                    novaPedra.setPosicao(2, i);
                    novaTela.addElemento(novaPedra);
                
            }
            
            for (int i = 6; i < 9; i ++) {
                    Pedra novaPedra = new Pedra("pedra.png");
                    novaPedra.setPosicao(3, i);
                    novaTela.addElemento(novaPedra);
                
            }
            
            Grama novaGrama = new Grama("grama.png");
            novaGrama.setPosicao(4, 7);
            novaTela.addElemento(novaGrama);
            
            Grama novaGrama2 = new Grama("grama.png");
            novaGrama2.setPosicao(4, 8);
            novaTela.addElemento(novaGrama2);
           
            for (int i = 5; i < 7; i ++) {
                    for (int j = 1; j < 12; j++) {
                        if (j != 5 && j != 10) {
                            Agua novaAgua = new Agua("agua.png");
                            novaAgua.setPosicao(i, j);
                            novaTela.addElemento(novaAgua);
                        }
                    }
            }
            
            for (int j = 7; j < 12; j++) {
                    Agua novaAgua = new Agua("agua.png");
                    novaAgua.setPosicao(j, 11);
                    novaTela.addElemento(novaAgua);
            }
            
            for (int j = 7; j < 11; j++) {
                    Agua novaAgua = new Agua("agua.png");
                    novaAgua.setPosicao(11, j);
                    novaTela.addElemento(novaAgua);
            }
            
            
            for (int i = 7; i < 9; i ++) {
                    for (int j = 3; j < 5; j++) {
                            Grama novaGrama3 = new Grama("grama.png");
                            novaGrama3.setPosicao(i, j);
                            novaTela.addElemento(novaGrama3);
                    }
            }
            
            for (int j = 8; j < 10; j++) {
                Grama novaGrama3 = new Grama("grama.png");
                novaGrama3.setPosicao(8, j);
                novaTela.addElemento(novaGrama3);                       
            }
            
            for (int j = 6; j < 8; j++) {
                Pedra novaPedra = new Pedra("pedra.png");
                novaPedra.setPosicao(9, j);
                novaTela.addElemento(novaPedra);                       
            }
            
            Caixa novaCaixa = new Caixa("caixa.png");
            novaCaixa.setPosicao(10, 7);
            novaTela.addElemento(novaCaixa);
        }
        
        if (fase == 3) {
            novaTela.setPosicaoLolo(9, 7);
            
            BichoVerde novoVerde = new BichoVerde("bichoVerde.png");
            novoVerde.setPosicao(5, 6);
            novaTela.addElemento(novoVerde);
            
            Dragao novoDragao = new Dragao("dragao.png");
            novoDragao.setPosicao(9, 1);
            novoDragao.trocar = 1;
            novaTela.addElemento(novoDragao);
            
            for (int i = 3; i < 5; i ++) {
                    for (int j = 8; j < 11; j++) {
                            Agua novaAgua = new Agua("agua.png");
                            novaAgua.setPosicao(i, j);
                            novaTela.addElemento(novaAgua);
                        
                    }
            }
            
            for (int i = 8; i < 10; i ++) {
                    for (int j = 8; j < 11; j++) {
                            Agua novaAgua = new Agua("agua.png");
                            novaAgua.setPosicao(i, j);
                            novaTela.addElemento(novaAgua);
                        
                    }
            }
            
            for (int j = 3; j < 8; j++) {
                            Agua novaAgua = new Agua("agua.png");
                            novaAgua.setPosicao(4, j);
                            novaTela.addElemento(novaAgua);
                        
            }
            
            for (int j = 3; j < 11; j++) {
                if (j != 6) {
                    Agua novaAgua = new Agua("agua.png");
                    novaAgua.setPosicao(j, 2);
                    novaTela.addElemento(novaAgua);
                }                        
            }
            
            for (int j = 4; j < 11; j++) {
                Agua novaAgua = new Agua("agua.png");
                novaAgua.setPosicao(10, j);
                novaTela.addElemento(novaAgua);
            }
            
            for (int i = 3; i < 7; i++) {
                Grama novaGrama = new Grama("grama.png");
                novaGrama.setPosicao(3, i);
                novaTela.addElemento(novaGrama);
            }
            
            Grama novaGrama = new Grama("grama.png");
            novaGrama.setPosicao(5, 4);
            novaTela.addElemento(novaGrama);
            
            Grama novaGrama2 = new Grama("grama.png");
            novaGrama2.setPosicao(5, 8);
            novaTela.addElemento(novaGrama2);
            
            Grama novaGrama5 = new Grama("grama.png");
            novaGrama5.setPosicao(6, 8);
            novaTela.addElemento(novaGrama5);
            
            Grama novaGrama3 = new Grama("grama.png");
            novaGrama3.setPosicao(9, 4);
            novaTela.addElemento(novaGrama3);
            
            Grama novaGrama4 = new Grama("grama.png");
            novaGrama4.setPosicao(8, 7);
            novaTela.addElemento(novaGrama4);
            
            Grama novaGrama6 = new Grama("grama.png");
            novaGrama6.setPosicao(11, 11);
            novaTela.addElemento(novaGrama6);
            
            
            for (int i = 6; i < 9; i ++) {
                    for (int j = 4; j < 7; j++) {
                            if (j != 5) {
                                Pedra novaPedra = new Pedra("pedra.png");
                                novaPedra.setPosicao(i, j);
                                novaTela.addElemento(novaPedra);
                            }    
                    }
            }
            
            Porta novaPorta = new Porta("porta.png");
            novaPorta.setPosicao(1, 5);
            novaTela.addElemento(novaPorta);
            
            Coracao novoCoracao = new Coracao("coracao.png");
            novoCoracao.setPosicao(3, 7);
            novaTela.addElemento(novoCoracao);
            
            Coracao novoCoracao2 = new Coracao("coracao.png");
            novoCoracao2.setPosicao(7, 5);
            novaTela.addElemento(novoCoracao2);
            
            Coracao novoCoracao3 = new Coracao("coracao.png");
            novoCoracao3.setPosicao(7, 7);
            novaTela.addElemento(novoCoracao3);
            
            Coracao novoCoracao4 = new Coracao("coracao.png");
            novoCoracao4.setPosicao(11, 8);
            novaTela.addElemento(novoCoracao4);
            
            Coracao novoCoracao5 = new Coracao("coracao.png");
            novoCoracao5.setPosicao(10, 11);
            novaTela.addElemento(novoCoracao5);
            
            Bau novoBau = new Bau("bau.png");
            novoBau.setPosicao(5, 3);
            novaTela.addElemento(novoBau);
            
            Ponte novaPonte = new Ponte("ponteLado.png");
            novaPonte.setPosicao(6, 2);
            novaTela.addElemento(novaPonte);
            
            Ponte novaPonte2 = new Ponte("ponte.png");
            novaPonte2.setPosicao(10, 3);
            novaTela.addElemento(novaPonte2);
        }
        
        if (fase == 4) {
            novaTela.setPosicaoLolo(11, 6);
            
            Caveira novaCaveira = new Caveira("caveira.png");
            novaCaveira.setPosicao(2, 4);
            novaCaveira.setbTransponivel(false);
            novaTela.addElemento(novaCaveira);
            
            Caveira novaCaveira2 = new Caveira("caveira.png");
            novaCaveira2.setPosicao(11, 4);
            novaCaveira2.setbTransponivel(false);
            novaTela.addElemento(novaCaveira2);
            
            for (int i = 2; i < 8; i ++) {
                Pedra novaPedra = new Pedra("pedra.png");
                novaPedra.setPosicao(3, i);
                novaTela.addElemento(novaPedra);
            }
            
            for (int i = 2; i < 8; i ++) {
                Pedra novaPedra = new Pedra("pedra.png");
                novaPedra.setPosicao(10, i);
                novaTela.addElemento(novaPedra);
            }
            
            for (int i = 7; i < 11; i ++) {
                Pedra novaPedra = new Pedra("pedra.png");
                novaPedra.setPosicao(5, i);
                novaTela.addElemento(novaPedra);
            }
            
            for (int i = 6; i < 8; i ++) {
                Pedra novaPedra = new Pedra("pedra.png");
                novaPedra.setPosicao(i, 7);
                novaTela.addElemento(novaPedra);
            }
            
            for (int i = 7; i < 11; i ++) {
                Pedra novaPedra = new Pedra("pedra.png");
                novaPedra.setPosicao(8, i);
                novaTela.addElemento(novaPedra);
            }
            
            Pedra novaPedra = new Pedra("pedra.png");
            novaPedra.setPosicao(4, 10);
            novaTela.addElemento(novaPedra);
            
            Pedra novaPedra2 = new Pedra("pedra.png");
            novaPedra2.setPosicao(9, 10);
            novaTela.addElemento(novaPedra2);
            
            for (int i = 6; i < 8; i ++) {
                    for (int j = 2; j < 6; j++) {
                            if (i == 7 && j == 5){
                                
                            }
                            
                            else {
                                Agua novaAgua = new Agua("agua.png");
                                novaAgua.setPosicao(i, j);
                                novaTela.addElemento(novaAgua);
                            }
                                
                            
                    }
            }
            
            Ponte novaPonte = new Ponte("ponte.png");
            novaPonte.setPosicao(6, 1);
            novaTela.addElemento(novaPonte);
            
            Ponte novaPonte2 = new Ponte("ponte.png");
            novaPonte2.setPosicao(7, 1);
            novaTela.addElemento(novaPonte2);
            
            Caixa novaCaixa = new Caixa("caixa.png");
            novaCaixa.setPosicao(3, 8);
            novaTela.addElemento(novaCaixa);
            
            Caixa novaCaixa2 = new Caixa("caixa.png");
            novaCaixa2.setPosicao(4, 3);
            novaTela.addElemento(novaCaixa2);
            
            Caixa novaCaixa3 = new Caixa("caixa.png");
            novaCaixa3.setPosicao(10, 8);
            novaTela.addElemento(novaCaixa3);
            
            Caixa novaCaixa4 = new Caixa("caixa.png");
            novaCaixa4.setPosicao(9, 3);
            novaTela.addElemento(novaCaixa4);
            
            Porta novaPorta = new Porta("porta.png");
            novaPorta.setPosicao(1, 6);
            novaTela.addElemento(novaPorta);
            
            Coracao novoCoracao = new Coracao("coracao.png");
            novoCoracao.setPosicao(6, 1);
            novaTela.addElemento(novoCoracao);
            
            Coracao novoCoracao2 = new Coracao("coracao.png");
            novoCoracao2.setPosicao(7, 1);
            novaTela.addElemento(novoCoracao2);
            
            Coracao novoCoracao3 = new Coracao("coracao.png");
            novoCoracao3.setPosicao(6, 9);
            novaTela.addElemento(novoCoracao3);
            
            Coracao novoCoracao4 = new Coracao("coracao.png");
            novoCoracao4.setPosicao(6, 8);
            novaTela.addElemento(novoCoracao4);
            
            Coracao novoCoracao5 = new Coracao("coracao.png");
            novoCoracao5.setPosicao(7, 8);
            novaTela.addElemento(novoCoracao5);
            
            Bau novoBau = new Bau("bau.png");
            novoBau.setPosicao(7, 5);
            novaTela.addElemento(novoBau);
            
        }
        
        if (fase == 5) {
            novaTela.setPosicaoLolo(11, 6);
            
            Fantasma novoFantasma = new Fantasma("fantasma.png");
            novoFantasma.setPosicao(6, 4);
            novaTela.addElemento(novoFantasma);
            
            Fantasma novoFantasma2 = new Fantasma("fantasma.png");
            novoFantasma2.setPosicao(6, 8);
            novaTela.addElemento(novoFantasma2);
            
            for (int i = 1; i < 5; i ++) {
                Pedra novaPedra = new Pedra("pedra.png");
                novaPedra.setPosicao(2, i);
                novaTela.addElemento(novaPedra);
            }
            
            for (int i = 1; i < 5; i ++) {
                if (i != 3) {
                    Pedra novaPedra = new Pedra("pedra.png");
                    novaPedra.setPosicao(4, i);
                    novaTela.addElemento(novaPedra);
                }    
            }
            
            Pedra novaPedra = new Pedra("pedra.png");
            novaPedra.setPosicao(3, 1);
            novaTela.addElemento(novaPedra);
            
            for (int i = 8; i < 12; i ++) {
                Pedra novaPedra2 = new Pedra("pedra.png");
                novaPedra2.setPosicao(4, i);
                novaTela.addElemento(novaPedra2);    
            }
            
            for (int i = 8; i < 12; i ++) {
                if (i != 9) {
                    Pedra novaPedra2 = new Pedra("pedra.png");
                    novaPedra2.setPosicao(3, i);
                    novaTela.addElemento(novaPedra2);    
                }    
            }
            
            Pedra novaPedra3 = new Pedra("pedra.png");
            novaPedra3.setPosicao(2, 11);
            novaTela.addElemento(novaPedra3);
            
            for (int i = 1; i < 4; i ++) {
                if (i != 2) {
                    Pedra novaPedra2 = new Pedra("pedra.png");
                    novaPedra2.setPosicao(8, i);
                    novaTela.addElemento(novaPedra2);    
                }    
            }
            
            for (int i = 1; i < 5; i ++) {
                if (i != 2) {
                    Pedra novaPedra2 = new Pedra("pedra.png");
                    novaPedra2.setPosicao(9, i);
                    novaTela.addElemento(novaPedra2);    
                }    
            }
            
            for (int i = 1; i < 5; i ++) {
                if (i != 2) {
                    Pedra novaPedra2 = new Pedra("pedra.png");
                    novaPedra2.setPosicao(11, i);
                    novaTela.addElemento(novaPedra2);    
                }    
            }
            
            for (int i = 8; i < 12; i ++) {
                if (i != 10) {
                    Pedra novaPedra2 = new Pedra("pedra.png");
                    novaPedra2.setPosicao(9, i);
                    novaTela.addElemento(novaPedra2);    
                }    
            }
            
            for (int i = 8; i < 12; i ++) {
                if (i != 10) {
                    Pedra novaPedra2 = new Pedra("pedra.png");
                    novaPedra2.setPosicao(11, i);
                    novaTela.addElemento(novaPedra2);    
                }    
            }
            
            for (int i = 8; i < 12; i ++) {
                if (i != 10 && i != 9) {
                    Pedra novaPedra2 = new Pedra("pedra.png");
                    novaPedra2.setPosicao(10, i);
                    novaTela.addElemento(novaPedra2);    
                }
            }    

            Caixa novaCaixa = new Caixa("caixa.png");
            novaCaixa.setPosicao(3, 4);
            novaTela.addElemento(novaCaixa);

            Caixa novaCaixa2 = new Caixa("caixa.png");
            novaCaixa2.setPosicao(2, 8);
            novaTela.addElemento(novaCaixa2);

            Caixa novaCaixa3 = new Caixa("caixa.png");
            novaCaixa3.setPosicao(8, 2);
            novaTela.addElemento(novaCaixa3);

            Caixa novaCaixa4 = new Caixa("caixa.png");
            novaCaixa4.setPosicao(10, 4);
            novaTela.addElemento(novaCaixa4);

            Caixa novaCaixa5 = new Caixa("caixa.png");
            novaCaixa5.setPosicao(9, 10);
            novaTela.addElemento(novaCaixa5);

            Coracao novoCoracao = new Coracao("coracao.png");
            novoCoracao.setPosicao(3, 2);
            novaTela.addElemento(novoCoracao);

            Coracao novoCoracao2 = new Coracao("coracao.png");
            novoCoracao2.setPosicao(3, 9);
            novaTela.addElemento(novoCoracao2);

            Coracao novoCoracao3 = new Coracao("coracao.png");
            novoCoracao3.setPosicao(11, 2);
            novaTela.addElemento(novoCoracao3);

            Coracao novoCoracao4 = new Coracao("coracao.png");
            novoCoracao4.setPosicao(10, 9);
            novaTela.addElemento(novoCoracao4);

            Bau novoBau = new Bau("bau.png");
            novoBau.setPosicao(6, 6);
            novaTela.addElemento(novoBau);
        }
    }
    
    public void encerra() {
        novaTela.setVisible(false);
        novaTela.dispose();
    }
    
    public void printarFase () throws IOException {
        novaTela.setVisible(true);
        novaTela.createBufferStrategy(2);
        novaTela.go(this);
    }
}