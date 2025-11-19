import java.io.*;
import java.net.Socket;

public class ParaRecibir implements Runnable {
    final DataInputStream entrada;

    
    public ParaRecibir(DataInputStream entrada) throws IOException {
        this.entrada = entrada;
    }


    @Override
    public void run() {
        while (true) {
            try {
                String mensaje = entrada.readUTF();
                
             
                if (mensaje.equals("LOGIN_OK")) {
                    System.out.println("Servidor: " + mensaje);
                    
                   
                    Clientemulti.loginExitoso = true; 
                    
                } else if (mensaje.startsWith("OK: Usuario")) {
                  
                    System.out.println("Servidor: " + mensaje);
                    System.out.println("Registro exitoso. Ahora inicia sesión (escribe 1).");
                } else {
                   
                    System.out.println(mensaje);
                }
            

            } catch (IOException e) {
                System.out.println("Desconectado del servidor.");
             
                if (!Clientemulti.loginExitoso) {
                    System.exit(0); 
                }
                break;
            }
        }
    }
}