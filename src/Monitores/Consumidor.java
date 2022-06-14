package Monitores;

public class Consumidor extends Thread{
    private Intercambio intercambio;

    public Consumidor(Intercambio intercambio){
        this.intercambio = intercambio;
    }

    @Override
    public void run() {
        try {
            String ping = intercambio.sacarPing();
            if(ping.equals("FIN")){
                System.out.println("Consumidor se retira");
            }else{
                System.out.println("ping extraido: "+ping);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
