package HormigasDeLangton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author Jorge Salmon Yanguas
 */
public class Director {

    private static int filas;
    private static int columnas;
    private static String reglaColores;  
    private static String orientacion;  
    private static int posHormigaFila;
    private static int posHormigaColumna;
    private static int maxMoves;
    private static String rutaProcesos;  
    private static int colorCasilla = 1;
    private static char tablero[][];
    private static char color;
    private static String[] numReglas;

    public static void main(String[] args) throws IOException {
        //Metodo para leer configuracion del fichero
        aplicarConfiguracion();
        //Guardo el bw del proceso grabador en una variable para poder
        //pasarselo al metodo que graba el tablero
        BufferedWriter bw = tableroYprocesoGrabador();
        
        ProcessBuilder pbHormiga = new ProcessBuilder("java", "-cp",
                rutaProcesos,
                "HormigasDeLangton.Hormiga");
        Process p = pbHormiga.start();
        
        //Guardo el bw y br de la hormiga, psasndole el proceso iniciado como
        //argumento para iniciar el mismo una sola vez, para pasarselo al
        //metodo hormigas que se comunica con el subproceso
        BufferedWriter bwH = bwHormiga(p);
        BufferedReader brH = brHormiga(p);
        //Mientras la hormiga no sobrepase el numero maximo de movimientos
        //sus movimientos continuan
        for (int i = 0; i <= maxMoves; ++i) {
            hormigas(bwH, brH);
            actualizarTablero();
            grabador(bw);
            //if para cerrar las cominucaciones y dejar morir a los procesos
            if (i == maxMoves) {
                bwH.write("no" + "\n");
                bwH.flush();
                bwH.close();
                brH.close();
                bw.write("FIN\n");
                bw.flush();
                bw.close();
            }
        }
    }// main()
    
    private static void aplicarConfiguracion() {
        try {
            File f = new File("FicheroConfiguracionHormiga.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            String leerConfiguracion=br.readLine();
            String[] configuracionPorLineas;
            String [] configuracionPorVariables;
            String[] datos=new String[8];
            for (int i = 0; i < 8; ++i) {
                configuracionPorLineas = leerConfiguracion.split(";");
                configuracionPorVariables=configuracionPorLineas[0].split("=");
                datos[i]=configuracionPorVariables[1];
                leerConfiguracion=br.readLine();
            }         
            filas=Integer.parseInt(datos[0]);
            columnas=Integer.parseInt(datos[1]);
            reglaColores=datos[2];
            orientacion=datos[3];
            posHormigaFila=Integer.parseInt(datos[4]);
            posHormigaColumna=Integer.parseInt(datos[5]);
            maxMoves=Integer.parseInt(datos[6]);
            rutaProcesos=datos[7];
            numReglas=reglaColores.split(",");
            tablero = new char[filas][columnas];
            br.close();
            fr.close();
            
        } // aplicarConfiguracion()
        catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private static BufferedWriter tableroYprocesoGrabador() throws IOException {
        //Inicializo el tablero recorriendo las filas
        //y las columnas rellenandolas con espacios
        color = ' ';
        for (int i = 0; i < filas; ++i) {
            for (int j = 0; j < columnas; ++j) {
                tablero[i][j] = color;
            }
        }
        //Creo una unica vez el proceso grabador
        ProcessBuilder pbGrabador = new ProcessBuilder("java", "-cp",
                rutaProcesos,
                "HormigasDeLangton.Grabador");
        Process p = pbGrabador.start();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
        return bw;
    }// tableroYprocesoGrabador()

    private static BufferedWriter bwHormiga(Process p) throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

        //Envio la configuracion inicial al proceso hormiga
        bw.write(Integer.toString(filas) + " " + Integer.toString(columnas)
                + "o" + Integer.toString(posHormigaFila) + " "
                + Integer.toString(posHormigaColumna) + "o" + orientacion + "o"
                + reglaColores + "\n");
        bw.flush();
        return bw;
    }// bwHormiga()

    private static BufferedReader brHormiga(Process p) {
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        return br;
    }// brHormiga()

    private static void hormigas(BufferedWriter bw, BufferedReader br) throws IOException {

        //Recibo la posicion de la hormiga para enviarle
        //el color de la casilla
        bw.write("si" + "\n");
        bw.flush();
        String posHormiga = br.readLine();
        String[] posHor = posHormiga.split(" ");
        posHormigaFila = Integer.parseInt(posHor[0]);
        posHormigaColumna = Integer.parseInt(posHor[1]);
        char respuestaColor = tablero[posHormigaFila][posHormigaColumna];
        bw.write(respuestaColor + "\n");
        bw.flush();

    }// hormigas()

    private static void actualizarTablero() {
        color = tablero[posHormigaFila][posHormigaColumna];
        switch (color) {
            case ' ': colorCasilla = 1; break;
            case '#': colorCasilla = 2; break;
            case '/': colorCasilla = 3; break;
            case '&': colorCasilla = 4; break;
        }
        //If para controlar los cambios de colores en
        //funcion de las reglas de colores dadas
        if (colorCasilla == numReglas.length) {
            colorCasilla = 1;
        } else {
            ++colorCasilla;
        }
        switch (colorCasilla) {
            case 1: color = ' '; break;
            case 2: color = '#'; break;
            case 3: color = '/'; break;
            case 4: color = '&'; break;
        }
    }// actualizarTablero()

    private static void grabador(BufferedWriter bw) {
        //Pinta la posicion actual de la hormiga con el simbolo del yen
        tablero[posHormigaFila][posHormigaColumna] = '¥';
        String tableroEnviar = "";
        for (int x = 0; x < tablero.length; x++) {
            for (int y = 0; y < tablero[x].length; y++) {
                tableroEnviar += (tablero[x][y]);
            }
            tableroEnviar += "o";
        }
        //Pinta el color de la posicion de la hormiga que corresponde
        //para que en la proxima llamada se quite el yen
        //y se ponga el color correspondiente
        tablero[posHormigaFila][posHormigaColumna] = color;
        try {
            bw.write(tableroEnviar + "\n");
            bw.flush();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }// grabador()

}// Director
