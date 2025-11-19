import java.io.*;
import java.net.Socket;

public class ParaMandar implements Runnable {
  
    final BufferedReader teclado;
    final DataOutputStream salida;

    public ParaMandar(DataOutputStream salida, BufferedReader teclado) throws IOException {
        this.salida = salida;
        this.teclado = teclado;
    }
  

    @Override
    public void run() {
        while (true) {
            try {
                String mensaje = teclado.readLine();
                if (mensaje == null) break; // Salió
                salida.writeUTF(mensaje);
            } catch (IOException e) {
                System.out.println("Error al enviar mensaje (probablemente desconectado).");
                break;
            }
        }
    }
}