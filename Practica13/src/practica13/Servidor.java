package practica13;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author SeLu
 */
public class Servidor {

    public static void main(String[] argv) throws Exception {
        /*
        Creamos el socket para recibir peticiones en el puerto 2000
         Para una conexión TCP usamos ServerSocket la cual es la que utilizamos en este caso,
         si la conexión fuera UDP usaríamos DatagramSocket, 
         y deberíamos de trabajar con los datagramas
         */
        ServerSocket s = new ServerSocket(2000);
        while (true) {
            Socket c = s.accept();
            /*Abrimos el flujo de entrada*/
            InputStream in = c.getInputStream();
            InputStreamReader inr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(inr);
            /*Leemos lo que nos llega*/
            String str = br.readLine();
            PrintStream ps = new PrintStream(c.getOutputStream());
            /*
            Al hacer el PrinStream sobre c.gotOutputStream, estamos escribiendo a la salida de la conexión,
            enviandole así la respuesta al cliente
            */
            ps.println(str);
        }
    }
}
