import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Clientemulti {

   
    static volatile boolean loginExitoso = false;

    public static void main(String[] args) {
        
        String ipServidor = "localhost"; 
        
        try { 
            Socket s = new Socket(ipServidor, 8080);

            DataInputStream entrada = new DataInputStream(s.getInputStream());
            DataOutputStream salida = new DataOutputStream(s.getOutputStream());
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

    
            new Thread(new ParaRecibir(entrada)).start();
          
            while (!loginExitoso) {
                
                String linea = teclado.readLine();
                if (linea == null) {
                    break; 
                }

                if (linea.equals("1")) {
                    System.out.print("Usuario: ");
                    String usuario = teclado.readLine();
                    System.out.print("Contraseña: ");
                    String pass = teclado.readLine();
                    
                    salida.writeUTF("LOGIN:" + usuario + ":" + pass);

                } else if (linea.equals("2")) {
                    System.out.print("Nuevo Usuario: ");
                    String usuario = teclado.readLine();
                    System.out.print("Nueva Contraseña: ");
                    String pass = teclado.readLine();

                    salida.writeUTF("REGISTER:" + usuario + ":" + pass);
                
                } else {
                  
                    salida.writeUTF(linea);
                }
        
            }
            
         
            System.out.println("--- Conectado al chat ---");

        
            new Thread(new ParaMandar(salida, teclado)).start();
    
        } catch (IOException e) {
            System.out.println("No se pudo conectar al servidor (" + ipServidor + "): " + e.getMessage());
        }
    }
}