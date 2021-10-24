package HormigasDeLangton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Grabador {
    static Scanner Scanner=new Scanner(System.in);
    
    public static void main(String[] args) {
        String tableroRecibido=Scanner.nextLine();
        escribirTablero(tableroRecibido);
    }// main()
    
    private static void escribirTablero(String tableroRecibido){
        File tablero=new File("tablero.txt");
        
        try {
            FileWriter      fwTablero   =  new FileWriter(tablero, true);
            BufferedWriter  bwTablero   =  new BufferedWriter(fwTablero);
            
            //bucle para formatear el string que llega del proceso padre
            //y que se vea como un tablero
            String[] filas = tableroRecibido.split("o");
            for (int i = 0; i < filas.length; i++) {
                bwTablero.write(filas[i]+"\n");
            }
            bwTablero.write("-\n");
                       
            bwTablero.close();
            fwTablero.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("IOException error");
        }
    }// escribirTablero()
}// Grabador