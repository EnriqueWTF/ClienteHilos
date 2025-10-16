

import java.io.IOException;
import java.net.Socket;

public class Clientemulti {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 8080);

        new Thread(new ParaMandar(s)).start();
        new Thread(new ParaRecibir(s)).start();
    }
}
