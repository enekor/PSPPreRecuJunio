package Monitores;

import GrepPing.ping.PingCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Productor extends Thread{
    private Intercambio intercambio;
    private List<Future<String>> pings = new ArrayList<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    public Productor(Intercambio intercambio,String web1,String web2){
        this.intercambio = intercambio;

        System.out.println("haciendo ping a "+web1+" y "+web2);
        pings.add(executorService.submit(new PingCallable(web1)));
        pings.add(executorService.submit(new PingCallable(web2)));
    }

    @Override
    public void run() {
        for (Future<String> ping: pings) {
            try {
                intercambio.nuevoPing(ping.get());
                Thread.sleep(300);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
