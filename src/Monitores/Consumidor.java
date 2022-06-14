package Monitores;

public class Consumidor extends Thread{
    private Intercambio intercambio;
    private boolean salir = false;

    public Consumidor(Intercambio intercambio){
        this.intercambio = intercambio;
    }

    @Override
    public void run() {
        try {
            while(!salir){
                String ping = intercambio.sacarPing();
                if(ping.equals("FIN")){
                    System.out.println("Consumidor se retira");
                    salir = true;
                }else{
                    System.out.println("ping extraido: "+ping);
                }
            }

            System.exit(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
