package Monitores;

import ClienteServidor.Cliente.Cliente;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Intercambio intercambio = new Intercambio(6);
        List<Productor> productores = List.of(
                new Productor(intercambio,"google.es","pornhub.com"),
                new Productor(intercambio,"twitter.com","reddit.com"),
                new Productor(intercambio,"github.com","facebook.com")
        );
        List<Consumidor> consumidores = List.of(
                new Consumidor(intercambio),
                new Consumidor(intercambio)
        );

        productores.forEach(Thread::start);
        consumidores.forEach(Thread::start);
    }

    public void init(Cliente c){
        Intercambio intercambio = new Intercambio(6);
        List<Productor> productores = List.of(
                new Productor(intercambio,"google.es","pornhub.com"),
                new Productor(intercambio,"twitter.com","reddit.com"),
                new Productor(intercambio,"github.com","facebook.com")
        );
        List<Consumidor> consumidores = List.of(
                new Consumidor(intercambio,true,c),
                new Consumidor(intercambio,true,c)
        );

        productores.forEach(Thread::start);
        consumidores.forEach(Thread::start);
    }
}
