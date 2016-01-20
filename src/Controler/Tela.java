package Controler;

import Auxiliar.Consts;
import LoloModel.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.swing.JOptionPane;

public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener, Serializable {
    private boolean fim;
    private Lolo lLolo;
    private int faseAtual;
    private int vidaAtual;
    private int num;
    private int contador;
    private ArrayList<Elemento> e;
    AutoSalvamento auto = new AutoSalvamento();
    ControleDeJogo cj = new ControleDeJogo();

    public Tela() {
        initComponents();
        this.addMouseListener(this);
        this.addKeyListener(this);  
        this.setSize(Consts.RES*Consts.CELL_SIDE + getInsets().left + getInsets().right,
                     Consts.RES*Consts.CELL_SIDE + getInsets().top + getInsets().bottom);

        e = new ArrayList<Elemento>(169);
        lLolo = new Lolo("lolo.png");
        this.addElemento(lLolo);
    }
    //UTILIZA A INSTANCIA DA CLASSE FASE, QUE UTILIZA O PADRÃO SINGLETON
    public void iniciarJogo(int i, int x) throws IOException {
        Fase.instance().trocarDeFase();
        Fase.instance().setFase(i);
        Fase.instance().setVidas(x);
        Fase.instance().inicarFase(auto.getTempo());
        Fase.instance().printarFase();
    }
    
    public void salvarJogo() {
        try {
            FileOutputStream fos = new FileOutputStream(new File("user.dat"));
            GZIPOutputStream gos = new GZIPOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(gos);
            
            oos.writeInt(faseAtual);
            oos.writeInt(vidaAtual);
     
            oos.flush();
            oos.close();
 
            gos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void carregarJogo () throws ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(new File("user.dat"));
            GZIPInputStream gis = new GZIPInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(gis);
           
            faseAtual = (int) ois.readInt();
            vidaAtual = (int) ois.readInt();
            cj.trocarDeFase = true;
            try {
                iniciarJogo(faseAtual, vidaAtual);
            } catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ois.close();
            gis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setPosicaoLolo(int a, int b) {
        lLolo.setPosicao(a, b);
    }
   
    public void addElemento(Elemento umElemento){
        e.add(umElemento);
    }
         
    public void removeElemento(Elemento umElemento){
        e.remove(umElemento);
    }
        
    public void paint(Graphics gOld) {   
        Graphics g = getBufferStrategy().getDrawGraphics();
        /*Criamos um contexto gráfico*/
        Graphics g2 = g.create(getInsets().right,getInsets().top,getWidth() - getInsets().left,getHeight() - getInsets().bottom);
        /*Desenha cenário*/
        for (int i = 0; i < Consts.RES; i++) {
            for (int j = 0; j < Consts.RES-1; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "bricks.png");
                    g2.drawImage(newImage,
                            j*Consts.CELL_SIDE, i*Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);

                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        this.cj.desenhaTudo(e, g2);
        this.cj.processaTudo(e);
        
        if (contador != auto.getI()) {
            salvarJogo();
            contador = auto.getI();
        }
        
        if (cj.novaFase) {
            cj.novaFase = false;
            cj.trocarDeFase = true;
            faseAtual += 1;
            if (faseAtual < 6) 
                try {
                    iniciarJogo(faseAtual, vidaAtual);
            } catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Utiliza o Desing Patterns Bridge
        for (int i = 0; i < e.size(); i++) {
            Elemento eTemp = e.get(i);
            if (e.get(i).getImage() == "caveiraAcordada.png") {
                if(!e.get(i).pPosicao.vericaPosicao(new EhPosicaoValidaCaveira(e, cj)))
                    if (eTemp.isRight) {
                        eTemp.isLeft = true;
                        eTemp.isRight = false;
                    }
                    else {
                        eTemp.isRight = true;
                        eTemp.isLeft = false;
                    }
            }
        }
        //Utiliza o Desing Patterns Bridge
        for (int i = 0; i < e.size(); i++) {
            Elemento eTemp = e.get(i);
            if (e.get(i).getImage() == "dragao.png" || e.get(i).getImage() == "dragaoFrente.png" ) {
                if(!e.get(i).pPosicao.vericaPosicao(new EhPosicaoValidaDragao(e, eTemp))) {
                    if (eTemp.isUp)
                        eTemp.isUp = false;
                    else 
                        eTemp.isUp = true;
                }    
            }
        }
        //Utiliza o Desing Patterns Bridge
        for (int i = 0; i < e.size(); i++) {
            Elemento eTemp = e.get(i);
            if (e.get(i).getImage() == "caixa.png") {
                if (!e.get(i).pPosicao.vericaPosicao(new EhPosicaoValidaLolo(e))) {
                        eTemp.pPosicao.volta();
                        lLolo.voltaAUltimaPosicao();
                }        
            }
        }
    
        if (cj.menosUmaVida) {
            vidaAtual += -1;
            cj.menosUmaVida = false;
            cj.trocarDeFase = true;
            try {
                iniciarJogo(faseAtual, vidaAtual);
            } catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (vidaAtual == 0) {
            cj.trocarDeFase = true; 
            JFrame jf = new JFrame();
            jf.setSize(Consts.RES*Consts.CELL_SIDE + getInsets().left + getInsets().right, Consts.RES*Consts.CELL_SIDE + getInsets().top + getInsets().bottom);
            try {
                jf.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("imgs/gameover.png")))));
            } catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
            jf.setVisible(true);
        }
        
        if (cj.numeroDePoder == 0) {
            Parede numeroPoderOvo = new Parede("0.png");
            numeroPoderOvo.setPosicao(3, 13);
            e.add(numeroPoderOvo);
        }
        
        if (cj.numeroDePoder == 1) {
            Parede numeroPoderOvo = new Parede("1.png");
            numeroPoderOvo.setPosicao(3, 13);
            e.add(numeroPoderOvo);
        }
        
        if (cj.numeroDePoder == 2) {
            Parede numeroPoderOvo = new Parede("2.png");
            numeroPoderOvo.setPosicao(3, 13);
            e.add(numeroPoderOvo);
        }
        
        if (vidaAtual == 5) {
            Parede numeroVidas = new Parede("5.png");
            numeroVidas.setPosicao(1, 13);
            e.add(numeroVidas);
        }
        
        if (vidaAtual == 4) {
            Parede numeroVidas = new Parede("4.png");
            numeroVidas.setPosicao(1, 13);
            e.add(numeroVidas);
        }
        
        if (vidaAtual == 3) {
            Parede numeroVidas = new Parede("3.png");
            numeroVidas.setPosicao(1, 13);
            e.add(numeroVidas);
        }
        
        if (vidaAtual == 2) {
            Parede numeroVidas = new Parede("2.png");
            numeroVidas.setPosicao(1, 13);
            e.add(numeroVidas);
        }
        
        if (vidaAtual == 1) {
            Parede numeroVidas = new Parede("1.png");
            numeroVidas.setPosicao(1, 13);
            e.add(numeroVidas);
        }
        
         
        if (cj.coracaosObtidos == 2 && faseAtual == 1) {
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getImage() == "bau.png")
                    e.remove(e.get(i));          
            }
            Bau aberto = new Bau("bauAberto.png");
            aberto.setPosicao(10, 5);
            e.add(aberto);
        }
        
        if (cj.coracaosObtidos == 4 && faseAtual == 2) {
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getImage() == "bau.png")
                    e.remove(e.get(i));          
            }
            Bau aberto = new Bau("bauAberto.png");
            aberto.setPosicao(7, 1);
            e.add(aberto);
            
            for (int i = 0; i < e.size(); i++) {
                Elemento eTemp = e.get(i);
                if (eTemp.getImage() == "bichoRosa.png") {
                    try {
                        eTemp.setImage("bichoRosaAcordado.png");
                    } catch (IOException ex) {
                        Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (eTemp.getImage() == "bichoRosa2.png") {
                    try {
                        eTemp.setImage("bichoRosaAcordado2.png");
                    } catch (IOException ex) {
                        Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        for (int i = 0; i < e.size(); i++) {
                Elemento eTemp = e.get(i);
                Elemento eTemp2 = e.get(0);
                if (eTemp.getImage() == "bichoRosaAcordado.png") {
                    if (eTemp.getPosicao().getColuna() == eTemp2.getPosicao().getColuna())
                        eTemp.trocar = 2;
                    else
                        eTemp.trocar = 0;
                }
                if (eTemp.getImage() == "bichoRosaAcordado2.png") {
                    if (eTemp.getPosicao().getLinha()== eTemp2.getPosicao().getLinha())
                        eTemp.trocar = 1;
                    else
                        eTemp.trocar = 0;
                }
        }
        
        if (cj.coracaosObtidos == 5 && faseAtual == 3) {
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getImage() == "bau.png")
                    e.remove(e.get(i));          
            }

            Bau aberto = new Bau("bauAberto.png");
            aberto.setPosicao(5, 3);
            e.add(aberto);   
        }
        
        if (cj.coracaosObtidos == 5 && faseAtual == 4) {
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getImage() == "bau.png")
                    e.remove(e.get(i));          
            }
            for (int i = 0; i < e.size(); i++) {
                Elemento eTemp = e.get(i);
                if (eTemp.getImage() == "caveira.png") {
                    try {
                        eTemp.setImage("caveiraAcordada.png");
                        eTemp.setbTransponivel(true);
                    } catch (IOException ex) {
                        Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    eTemp.trocar = 1;
                }
            }    
            Bau aberto = new Bau("bauAberto.png");
            aberto.setPosicao(7, 5);
            e.add(aberto);         
        }
        
        if (cj.coracaosObtidos == 4 && faseAtual == 5) {
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getImage() == "bau.png")
                    e.remove(e.get(i));          
            }
            Bau aberto = new Bau("bauAberto.png");
            aberto.setPosicao(6, 6);
            e.add(aberto);       
        }
        
        if (cj.proximaFase && faseAtual == 1) {
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getImage() == "fantasma.png" || e.get(i).getImage() == "bichoRosa2.png" || e.get(i).getImage() == "bichoRosa.png" || e.get(i).getImage() == "caveira.png" || e.get(i).getImage() == "caveiraAcordada.png" || e.get(i).getImage() == "bichoRosaAcordado.png" || e.get(i).getImage() == "bichoRosaAcordado2.png" || e.get(i).getImage() == "bichoVerde.png" || e.get(i).getImage() == "dragaoDormindo.png")
                    e.remove(e.get(i));          
            }
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getImage() == "bauAberto.png")
                    e.remove(e.get(i));          
            }
            Bau vazio = new Bau("bauVazio.png");
            vazio.setPosicao(10, 5);
            e.add(vazio);
            
            for(Elemento contato : e) {  
                if (contato.getImage() == "porta.png"){
                    e.remove(contato);
                    break;
                }
            }
            
            Porta portaAberta = new Porta("portaAberta.png");
            portaAberta.setPosicao(1, 7);
            e.add(portaAberta);
        }
        
        if (cj.proximaFase && faseAtual == 2) {
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getImage() == "fantasma.png" || e.get(i).getImage() == "bichoRosa2.png" || e.get(i).getImage() == "bichoRosa.png" || e.get(i).getImage() == "caveira.png" || e.get(i).getImage() == "caveiraAcordada.png" || e.get(i).getImage() == "bichoRosaAcordado.png" || e.get(i).getImage() == "bichoRosaAcordado2.png" || e.get(i).getImage() == "bichoVerde.png" || e.get(i).getImage() == "dragaoDormindo.png")
                    e.remove(e.get(i));          
            }
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getImage() == "bauAberto.png")
                    e.remove(e.get(i));          
            }
            Bau vazio = new Bau("bauVazio.png");
            vazio.setPosicao(7, 1);
            e.add(vazio);
            
            for(Elemento contato : e) {  
                if (contato.getImage() == "porta.png"){
                    e.remove(contato);
                    break;
                }
            }
            
            Porta portaAberta = new Porta("portaAberta.png");
            portaAberta.setPosicao(1, 10);
            e.add(portaAberta);
        }
        
        if (cj.proximaFase && faseAtual == 3) {
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getImage() == "fantasma.png" || e.get(i).getImage() == "bichoRosa2.png" || e.get(i).getImage() == "bichoRosa.png" || e.get(i).getImage() == "caveira.png" || e.get(i).getImage() == "caveiraAcordada.png" || e.get(i).getImage() == "bichoRosaAcordado.png" || e.get(i).getImage() == "bichoRosaAcordado2.png" || e.get(i).getImage() == "bichoVerde.png" || e.get(i).getImage() == "dragaoDormindo.png")
                    e.remove(e.get(i));          
            }
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getImage() == "bauAberto.png")
                    e.remove(e.get(i));          
            }
            Bau vazio = new Bau("bauVazio.png");
            vazio.setPosicao(5, 3);
            e.add(vazio);
            
            for(Elemento contato : e) {  
                if (contato.getImage() == "porta.png"){
                    e.remove(contato);
                    break;
                }
            }
            
            Porta portaAberta = new Porta("portaAberta.png");
            portaAberta.setPosicao(1, 5);
            e.add(portaAberta);
        }
        
        if (cj.proximaFase && faseAtual == 4) {
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getImage() == "fantasma.png" || e.get(i).getImage() == "bichoRosa2.png" || e.get(i).getImage() == "bichoRosa.png" || e.get(i).getImage() == "caveira.png" || e.get(i).getImage() == "caveiraAcordada.png" || e.get(i).getImage() == "bichoRosaAcordado.png" || e.get(i).getImage() == "bichoRosaAcordado2.png" || e.get(i).getImage() == "bichoVerde.png" || e.get(i).getImage() == "dragaoDormindo.png")
                    e.remove(e.get(i));          
            }
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getImage() == "bauAberto.png")
                    e.remove(e.get(i));          
            }
            Bau vazio = new Bau("bauVazio.png");
            vazio.setPosicao(7, 5);
            e.add(vazio);
            
            for(Elemento contato : e) {  
                if (contato.getImage() == "porta.png"){
                    e.remove(contato);
                    break;
                }
            }
            
            Porta portaAberta = new Porta("portaAberta.png");
            portaAberta.setPosicao(1, 6);
            e.add(portaAberta);
        }
        
        if (cj.proximaFase && faseAtual == 5) {
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getImage() == "fantasma.png" || e.get(i).getImage() == "bichoRosa2.png" || e.get(i).getImage() == "bichoRosa.png" || e.get(i).getImage() == "caveira.png" || e.get(i).getImage() == "caveiraAcordada.png" || e.get(i).getImage() == "bichoRosaAcordado.png" || e.get(i).getImage() == "bichoRosaAcordado2.png" || e.get(i).getImage() == "bichoVerde.png" || e.get(i).getImage() == "dragaoDormindo.png")
                    e.remove(e.get(i));          
            }
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getImage() == "bauAberto.png")
                    e.remove(e.get(i));          
            }
            Bau vazio = new Bau("bauVazio.png");
            vazio.setPosicao(6, 6);
            e.add(vazio);

            Escada novaEscada = new Escada("escada.png");
            novaEscada.setPosicao(10, 6);
            e.add(novaEscada);
        }

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }     
    }

    public void go(final Fase f) throws IOException {
        final ArquivoMusica l = new ArquivoMusica();
        l.ArquivoMusica();
        faseAtual = f.getFase();
        vidaAtual = f.getVidas();
        auto.start();
        TimerTask task = new TimerTask() {
            public void run() {
                if (cj.fimDeJogo) {
                    cj.fimDeJogo = false;
                    JFrame jf = new JFrame();
                    jf.setSize(Consts.RES*Consts.CELL_SIDE + getInsets().left + getInsets().right, Consts.RES*Consts.CELL_SIDE + getInsets().top + getInsets().bottom);
                    try {
                        jf.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("imgs/fim.png")))));
                    } catch (IOException ex) {
                        Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jf.setVisible(true);
                    f.encerra();
                    l.musica.stop();
                    final ArquivoMusica la = new ArquivoMusica();
                    la.setPath("msc/vitoria.mp3");
                    la.ArquivoMusica();
                }
                if (!cj.trocarDeFase)
                    repaint();
                else {
                    auto.stop();
                    f.encerra();
                    l.musica.stop();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.DELAY);
    }

    public void keyPressed(KeyEvent u) {
        if (u.getExtendedKeyCode() == KeyEvent.VK_T) {
            String valor = JOptionPane.showInputDialog("Digite um valor, em segundos, para o auto-salvamento.");
            num = Integer.parseInt(valor.trim());
            auto.setTempo(num);
        }
        
        if (u.getExtendedKeyCode() == KeyEvent.VK_M) {
            salvarJogo();
        }
        
        if (u.getExtendedKeyCode() == KeyEvent.VK_N) {
            try {
                carregarJogo();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (u.getExtendedKeyCode() == KeyEvent.VK_Z) {
            if (cj.numeroDePoder != 0)
                cj.poder(e);
                cj.numeroDePoder--;
        }
            
        if (u.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
            vidaAtual += -1;
            cj.trocarDeFase = true;
            try {
                iniciarJogo(faseAtual, vidaAtual);
            } catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        if (u.getKeyCode() == KeyEvent.VK_UP) {
            try {
                lLolo.setImage("loloU.png");
            } 
            catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
            lLolo.moveUp();          
        } else if (u.getKeyCode() == KeyEvent.VK_DOWN) {
            try {
                lLolo.setImage("lolo.png");
            } 
            catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
            lLolo.moveDown();
        } else if (u.getKeyCode() == KeyEvent.VK_LEFT) {
            try {
                lLolo.setImage("loloL.png");
            } 
            catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
            lLolo.moveLeft();
        } else if (u.getKeyCode() == KeyEvent.VK_RIGHT) {
            try {
                lLolo.setImage("loloR.png");
            } 
            catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
            lLolo.moveRight();
        }
        //Utiliza o Desing Patterns Bridge
        if(!e.get(0).pPosicao.vericaPosicao(new EhPosicaoValidaLolo(this.e)))
            lLolo.voltaAUltimaPosicao();
        
        this.setTitle("-> Cell: " + (lLolo.getPosicao().getColuna()) + ", " 
                                  + (lLolo.getPosicao().getLinha()));
        
        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }    
    
    public void mousePressed(MouseEvent e) {      
        /* Clique do mouse desligado
        int x = e.getX();
        int y = e.getY();
     
        this.setTitle("X: "+ x + ", Y: " + y +
                " -> Cell: " + (y/Consts.CELL_SIDE) + ", " + (x/Consts.CELL_SIDE));
        
        this.lLolo.getPosicao().setPosicao(y/Consts.CELL_SIDE, x/Consts.CELL_SIDE);
        */
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2015-1 - Adventures of lolo");
        setAutoRequestFocus(false);
        setFocusCycleRoot(false);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
