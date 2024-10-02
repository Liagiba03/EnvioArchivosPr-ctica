package Model;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Create {   // Crear clases y métodos de servidor y cliente
    private int DEST_PORT = 0;
    private int SEND_PORT = 0;
    private int RECE_PORT = 0;
    private String IP = null;
    private String ruta = null;
    private int opcion = 0;
    // Constructor almacena IP y puerto
    public Create(int DEST_PORT, int SEND_PORT, int RECE_PORT, String IP, String ruta, int opcion) { //PEDIR RUTA
        this.DEST_PORT = DEST_PORT;
        this.SEND_PORT = SEND_PORT;
        this.RECE_PORT = RECE_PORT;
        this.IP = IP;
        this.ruta = ruta;
        this.opcion = opcion;
    }
    public void run(){
        try {
            Send_Thread send_thread = null;
            Receive_Thread receive_thread = null;
            InetSocketAddress address = null;
            
            // Cree el número de puerto y la dirección IP de la máquina de destino para recibir paquetes de datos
            address = new InetSocketAddress(IP, DEST_PORT);
            // Crea el socket para enviar
            DatagramSocket sendsocket = new DatagramSocket(SEND_PORT);
            // Crear un socket aceptado
            DatagramSocket recesocket = new DatagramSocket(RECE_PORT);
            // Enviar establecimiento de hilo
            send_thread = new Send_Thread(sendsocket, address); //SE MANDA RUTA A ENVIAR
            // Aceptar el establecimiento del hilo
            receive_thread = new Receive_Thread(recesocket); //SE MANDA RUTA DONDE SE RECIBE

            if(opcion ==1){ //CLIENTE RECIBIR
                receive_thread.setRutaRecibir(ruta);
            }else if(opcion ==2){ //SERVIDOR ENVIAR
                send_thread.setRutaEnviar(ruta);
            }
            
            send_thread.start();  // Inicia el hilo
            receive_thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
