package ClienteServidor.Servidor;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Servidor {

    private String key = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"ClienteServidor"+File.separator+"claves"+File.separator+"servidor"+File.separator+"servidor.jks";
    private final int PUERTO = 5555;
    private SSLServerSocket server;
    private SSLSocket cliente;
    private SSLServerSocketFactory factory;

    public void init(){
        if(Files.exists(Path.of(key))){
            setSystemProperties();
            initVariables();
            iniciarServidor();
        }else{
            System.err.println("cuidado con la key, algo anda mal");
        }
    }

    private void setSystemProperties(){
        System.setProperty("javax.net.ssl.keyStore", key);
        System.setProperty("javax.net.ssl.keyStorePassword","123456");
    }

    private void initVariables(){

        try {
            factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            server = (SSLServerSocket) factory.createServerSocket(PUERTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void iniciarServidor(){
        while(true){
            try {
                cliente = (SSLSocket) server.accept();
                new GestionCliente(cliente).start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
