package ClienteServidor.Servidor;

import javax.net.ssl.SSLSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GestionCliente extends Thread{

    private DataInputStream input;
    private DataOutputStream output;
    private SSLSocket cliente;
    private int contador = 0;
    private boolean exit = false;

    public GestionCliente(SSLSocket cliente){
        try {
            this.cliente = cliente;
            this.input = new DataInputStream(cliente.getInputStream());
            this.output = new DataOutputStream(cliente.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        while (!exit){
            try {
                String linea = input.readUTF();
                if(linea.equalsIgnoreCase("fin")){
                    contador++;
                    output.writeBoolean(true);
                    exit = true;
                    System.out.println("sesion terminada");
                    cliente.close();
                }else{
                    System.out.println(linea);
                    output.writeBoolean(false);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
