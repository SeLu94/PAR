package practica13;

import java.io.*;
import java.net.*;

/**
 *
 * @author SeLu
 */
public class Cliente {

    private static String IP = "127.0.0.1";
    private static int Puerto = 2000;
    /*Esta es la cadena que se usará como ping, cada caracter son 16 bits*/
    private static String envio ="ping";

    public static void main(String args[]) throws IOException {
        ping();
    }

    private static int bitString(String str) {
        /*La lectura de 1 caracter en Java con str.length() es 16 bits por caracter*/
        int bits = str.length() * 16;
        return bits;
    }

    private static void ping() throws IOException {
        long t1, t2;
        while (true) {
            try {
                /* Creamos una conexión a una ip y un puerto*/
                Socket soc = new Socket(IP, Puerto);

                System.out.println("Haciendo ping a: " + IP + ":" + Puerto + " con " + bitString(envio) + " bits");
                /*Creamos un flujo de salida, en el cual mandaremos la cadena de caracteres "envio" */
                OutputStream os = soc.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                InputStream in = soc.getInputStream();
                InputStreamReader inr = new InputStreamReader(in);
                BufferedReader br1 = new BufferedReader(inr);
                /*Recogemos la hora en milisegundos, antes del envío*/
                t1 = System.currentTimeMillis();
                /*Enviamos la cadena*/
                pw.println(envio);
                /*
                Como usamos flujos, hasta que no se ejecute esta linea de lectura,
                la cual será la respuesta del servidor, esta no pasará a ejecutar la siguiente
                */
                String respuesta = br1.readLine();
                /*Recogemos la hora en milisegundos, tras la respuesta*/
                t2 = System.currentTimeMillis();
                System.out.println("Tiempo de respuesta aproximado: " + (t2 - t1) + " milisegundos. Pérdida: " + (bitString(envio) - bitString(respuesta)) + " bits.");
                /*Hacemos una espera para que el ping sea legible*/
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
        }

    }
}
