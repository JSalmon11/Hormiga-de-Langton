package HormigasDeLangton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Grabador {

    static Scanner Scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //Creo el fichero solo una vez para que sea mas eficiente
        File tablero = new File("tablero.txt");
        FileWriter fwTablero = new FileWriter(tablero, true);
        BufferedWriter bwTablero = new BufferedWriter(fwTablero);

        String tableroRecibido = Scanner.nextLine();
        while (!tableroRecibido.equals("FIN")) {
            escribirTablero(tableroRecibido, bwTablero);
            tableroRecibido = Scanner.nextLine();
        }
        bwTablero.close();
        fwTablero.close();
    }// main()

    private static void escribirTablero(String tableroRecibido, BufferedWriter bwTablero) throws IOException {
        //bucle para formatear el string que llega del proceso padre
        //y que se vea como un tablero
        String[] filas = tableroRecibido.split("o");
        for (int i = 0; i < filas.length; i++) {
            bwTablero.write(filas[i] + "\n");
        }
        bwTablero.write("-\n");
    }// escribirTablero()
}// Grabador