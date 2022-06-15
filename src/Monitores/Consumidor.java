package Monitores;

import ClienteServidor.Cliente.Cliente;

public class Consumidor extends Thread{
    private Intercambio intercambio;
    private boolean salir = false;
    private boolean envioAServidor = false;
    private Cliente cliente;

    public Consumidor(Intercambio intercambio){
        this.intercambio = intercambio;
    }

    public Consumidor(Intercambio intercambio,boolean envioAServidor, Cliente c){
        this.intercambio = intercambio;
        this.envioAServidor = envioAServidor;
        this.cliente = c;
    }

    @Override
    public void run() {
        try {
            while(!salir){
                String ping = intercambio.sacarPing();
                if(ping.equals("FIN")){
                    if(envioAServidor) {
                        cliente.intercambio("Fin del consumidor");
                    }else{
                        System.out.println("fin");
                    }
                    salir = true;
                }else{
                    if(envioAServidor){
                        cliente.intercambio(ping);
                    }
                    else {
                        System.out.println("ping extraido: " + ping);
                    }
                }
            }

            System.exit(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
