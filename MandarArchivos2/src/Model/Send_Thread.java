package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Send_Thread extends Thread {

    // Enviar socket
    private DatagramSocket sender = null;
    // La dirección de destino que se enviará, InetSocketAddress se usa para almacenar la dirección del socket IP
    private InetSocketAddress address = null;
    // Recibir entrada de teclado
    Scanner scan = new Scanner(System.in);
    String rutaEnviar = null;
    
    

    // constructor
    public Send_Thread(DatagramSocket sender, InetSocketAddress address) { //AQUI SE PIDE LA RUTA
        this.sender = sender;
        this.address = address;
    }
    public void setRutaEnviar(String rutaEnviar) {
        this.rutaEnviar = rutaEnviar;
    }

    public void run() {
        try {
            File source = new File(rutaEnviar);  // Ruta del archivo a transferir
            //"C:\\Users\\suset\\Documents\\ENVIAR\\Enviado.pdf"
            InputStream inputstream = new FileInputStream(source);
            byte[] data = new byte[1024];
            // Crear datagrama UDP
            while ((inputstream.read(data)) != -1) {
                DatagramPacket pack = new DatagramPacket(data, data.length, address);
                sender.send(pack);
                TimeUnit.MICROSECONDS.sleep(1); // Limite la velocidad de transmisión
            }
            System.out.println("DATOS ENVIADOS");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
