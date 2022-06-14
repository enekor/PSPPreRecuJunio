package GrepPing;

import GrepPing.ping.PingCallable;

import java.util.List;
import java.util.concurrent.*;

public class ConFuture {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);

        List<Callable<String>> callables = List.of(
                new PingCallable("google.es"),
                new PingCallable("reddit.com"),
                new PingCallable("twitter.com")
        );
        try {
            System.out.println("Enviando ping a google, reddit y twitter");
            List<Future<String>> futures = es.invokeAll(callables);
            futures.forEach(f ->{
                        try {
                            System.out.println(f.get());
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
