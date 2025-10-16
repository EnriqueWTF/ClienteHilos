
import java.io.*;
import java.net.Socket;

public class ParaRecibir implements Runnable {
    final DataInputStream entrada;

    public ParaRecibir(Socket s) throws IOException {
        this.entrada = new DataInputStream(s.getInputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                String mensaje = entrada.readUTF();
                System.out.println(mensaje);
            } catch (IOException e) {
                System.out.println("Desconectado del servidor.");
                break;
            }
        }
    }
}
