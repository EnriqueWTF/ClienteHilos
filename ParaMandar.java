

import java.io.*;
import java.net.Socket;

public class ParaMandar implements Runnable {
    final BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
    final DataOutputStream salida;

    public ParaMandar(Socket s) throws IOException {
        this.salida = new DataOutputStream(s.getOutputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                String mensaje = teclado.readLine();
                salida.writeUTF(mensaje);
            } catch (IOException e) {
                System.out.println("Error al enviar mensaje.");
                break;
            }
        }
    }
}

 