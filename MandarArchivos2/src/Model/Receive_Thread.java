package Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receive_Thread extends Thread{

    private static final int MAX_RECEIVE_BUFFER = 1024;  // Máximo buffer de recepción
    private DatagramSocket server;    // La clase DatagramSocket se usa para establecer el servidor y el cliente
    private DatagramPacket packet;    // La clase DatagramPacket se usa para almacenar y descomprimir datos UDP
    byte[] buffer = new byte[MAX_RECEIVE_BUFFER];
    private String rutaRecibir;
    // constructor
    public Receive_Thread(DatagramSocket server) {
        this.server = server;
        packet = new DatagramPacket(buffer, buffer.length);
    }
    
    public void setRutaRecibir(String rutaRecibir) {
        this.rutaRecibir = rutaRecibir;
    }

    public void run() {
        try {
            File dest = new File(rutaRecibir);  // Ruta de almacenamiento de archivos para recibir
            //"C:\\Users\\suset\\Documents\\RECIBIR\\Enviado.pdf"
            FileOutputStream output = new FileOutputStream(dest);
            int len = 0;   // longitud de datos
            while (len == 0) {  // Comienza a recibir datos cíclicamente si no hay datos
                // Recibir paquete de datos
                server.receive(packet);
                len = packet.getLength();
                if (len > 0) {
                    // Especifique la longitud de los datos recibidos, para que el programa pueda recibir datos normalmente
                    output.write(buffer,0,len);
                    output.flush();
                    len = 0;// Circular recibe
                }
            }
            System.out.println("DATOS RECIBIDOS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
