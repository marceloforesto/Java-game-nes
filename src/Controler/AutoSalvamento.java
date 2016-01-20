package Controler;

class AutoSalvamento extends Thread implements Runnable{
    private int tempo;
    private int i;
    
    public AutoSalvamento () {
        tempo = 60;
        i = 0;
    }        
    public void setTempo(int x) {
        this.tempo = x;
    }
    
    public int getTempo () {
        return this.tempo;
    }
    
    public int getI() {
        return this.i;
    }
    public void run(){
        while(true) {
            i++;
            try {
                this.sleep(tempo*1000);
            } catch (Exception e) {
                System.out.println(e);
            }    
        }
    }
}
