import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Clientemulti {

    public static void main(String[] args) {
        
       
        try { 
            Socket s = new Socket("localhost", 8080);

            
            DataInputStream entrada = new DataInputStream(s.getInputStream());
            DataOutputStream salida = new DataOutputStream(s.getOutputStream());
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            System.out.println(entrada.readUTF()); // "Bienvenido. [1] Iniciar Sesión [2] Registrarse"
            String opcion = teclado.readLine();
            
            String usuario = "";
            String pass = "";

            if (opcion.equals("1")) { // Iniciar Sesión
                System.out.print("Usuario: ");
                usuario = teclado.readLine();
                System.out.print("Contraseña: ");
                pass = teclado.readLine();
                
                salida.writeUTF("LOGIN:" + usuario + ":" + pass);

            } else if (opcion.equals("2")) { // Registrarse
                System.out.print("Nuevo Usuario: ");
                usuario = teclado.readLine();
                System.out.print("Nueva Contraseña: ");
                pass = teclado.readLine();

                salida.writeUTF("REGISTER:" + usuario + ":" + pass);
            } else {
                System.out.println("Opción no válida.");
                s.close();
                return;
            }



            //  Esperamos la respuesta del Servidor ---
            String respuestaServidor = entrada.readUTF();
            System.out.println("Servidor: " + respuestaServidor);

            if (!respuestaServidor.equals("LOGIN_OK")) {
                if (respuestaServidor.startsWith("OK: Usuario")) {
                     System.out.println("Registro exitoso. Por favor, reinicia el cliente para iniciar sesión.");
                }
                s.close();
                return;
            }
            
            System.out.println("--- Conectado al chat ---");

            new Thread(new ParaMandar(s)).start();
            new Thread(new ParaRecibir(s)).start();

    
        } catch (IOException e) {
            System.out.println("No se pudo conectar al servidor: " + e.getMessage());
        }
    }
}