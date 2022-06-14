package Monitores;

import java.util.LinkedList;
import java.util.Queue;

public class Intercambio {
    private Queue<String> pings = new LinkedList<>();
    private int totalPings = 0;
    private int maxPings;

    public Intercambio(int maxPings){
        this.maxPings = maxPings;
    }

    public synchronized void nuevoPing(String ping){
        System.out.println("nuevo ping en la base de datos");
        pings.add(ping);
        totalPings++;
        System.out.println("pings almacenados -> "+pings.size());
        System.out.println("pings totales -> "+totalPings);
        notifyAll();
    }

    public synchronized String sacarPing() throws InterruptedException {
        while(pings.isEmpty()){
            if(totalPings == maxPings){
                return "FIN";
            }
            System.out.println("esperando ping");
            wait();
        }
        System.out.println("sale un ping");
        return pings.poll();
    }
}
