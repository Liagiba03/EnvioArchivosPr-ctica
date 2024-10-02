
package Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateChat{
    
    static JTextArea chat;
    JTextField mensaje;

    public void setChat(JTextArea chat) {
        this.chat = chat;
    }

    public void setMensaje(JTextField mensaje) {
        this.mensaje = mensaje;
    }
   
    
}
