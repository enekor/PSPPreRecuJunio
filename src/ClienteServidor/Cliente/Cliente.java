package ClienteServidor.Cliente;

import Monitores.App;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Cliente {

    private String key = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"ClienteServidor"+File.separator+"claves"+File.separator+"cliente"+File.separator+"cliente.jks";
    private final int PUERTO = 5555;
    private SSLSocket servidor;
    private SSLSocketFactory factory;
    private DataInputStream input;
    private DataOutputStream output;

    public void init(){
        if(Files.exists(Path.of(key))){
            setSystemProperies();
            initVariables();
            new App().init(this);
        }else{
            System.err.println("Cuidado con la ruta del key, algo no anda bien");
        }
    }

    private void setSystemProperies(){
        System.setProperty("javax.net.ssl.trustStore", key);
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");
    }

    private void initVariables(){
        try {
            factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            servidor = (SSLSocket) factory.createSocket("localhost",PUERTO);
            input = new DataInputStream(servidor.getInputStream());
            output = new DataOutputStream(servidor.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void intercambio(String linea){
        try {
            System.out.println("Enviando resultado a servidor");
            output.writeUTF(linea);
            if(input.readBoolean()){
                System.out.println("sesion terminada");
                System.exit(0);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        notifyAll();
    }
}
