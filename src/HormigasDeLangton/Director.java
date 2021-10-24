package HormigasDeLangton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 *
 * @author Jorge Salmon Yanguas
 */
public class Director {
    //Configuracion-------------------------------------------------------
        private static final int filas = 60;
        private static final int columnas = 100;
        private static final String reglaColores="r,l";
        //Definir orientacion como Norte(N), Sur(S), Oeste(O) y Este(E)
        private static String orientacion="N";
        //Posicion maxima de la hormiga filas-1 columnas-1
        //porque el array es de 0 a filas y de 0 a columnas
        private static int posHormigaFila=30;
        private static int posHormigaColumna=50;
        private static final int maxMoves=10;
        private static final String rutaProcesos="F:\\JORGE\\Google Drive\\Mis cosa de clase\\2DAM\\Procesos\\Hormigas de Langton\\build\\classes";
    //Configuracion-------------------------------------------------------
        
        private static int colorCasilla=1;
        private static char tablero[][] = new char[filas][columnas];
        private static char color;
        private static String[] numReglas = reglaColores.split(",");
        
    public static void main(String[] args) {
        //El si es para saber si es el primer tablero que se crea
        //y asi crear el tablero vacio
        tablero("si");
        hormigas();
    }// main()
    
    private static void tablero(String primerTablero) {        
        ProcessBuilder pbGrabador=new ProcessBuilder("java","-cp",
            rutaProcesos,
            "HormigasDeLangton.Grabador");
        try {
            Process p=pbGrabador.start();      
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            
            if("si".equals(primerTablero)){
                //recorro las filas y las columnas del tablero rellenandolas con espacios
                color=' ';
                for (int i = 0; i < filas; ++i) {
                    for (int j = 0; j < columnas; ++j) {
                        tablero[i][j]=color;
                    }
                }
                String tableroEnviar = "";
                //bucle for para mandar el tablero formateado al subproceso,
                //separando las filas con "o", para luego en el proceso grabador
                //poder formatearlo del todo
                for (int x=0; x < tablero.length; x++) {
                    for (int y=0; y < tablero[x].length; y++) {
                        tableroEnviar += (tablero[x][y]);
                    }
                    tableroEnviar += "o";
                }
            bw.write(tableroEnviar+"\n");bw.flush();
            }else if ("no".equals(primerTablero)){
                actualizarTablero(bw);
            }                
        }catch (IOException ex) {
        System.out.println(ex);
        }
    }// tablero()
    
    private static void actualizarTablero(BufferedWriter bw){
        color=tablero[posHormigaFila][posHormigaColumna];
        switch(color){
            case ' ' : colorCasilla=1; break;
            case '#' : colorCasilla=2; break;
            case '/' : colorCasilla=3; break;
            case '&' : colorCasilla=4; break;
        }
        //If para controlar los cambios de colores en
        //funcion de las reglas de colores dadas
        if (colorCasilla==numReglas.length){
            colorCasilla=1;
        }else{
            ++colorCasilla;
        } 
        switch(colorCasilla){
            case 1: color=' '; break;
            case 2: color='#'; break;
            case 3: color='/'; break;
            case 4: color='&'; break;
        }
        //Pinta la posicion actual de la hormiga con el simbolo del yen
        tablero[posHormigaFila][posHormigaColumna]='¥';
        String tableroEnviar = "";
            for (int x=0; x < tablero.length; x++) {
                for (int y=0; y < tablero[x].length; y++) {
                    tableroEnviar += (tablero[x][y]);
                }
                tableroEnviar += "o";
            }
        //Pinta el color de la posicion de la hormiga que corresponde
        //para que en la proxima vuelta se quite el ¥
        //y se ponga el color correspondiente
        tablero[posHormigaFila][posHormigaColumna]=color;
        try {
            bw.write(tableroEnviar+"\n");
            bw.flush();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }// actualizarTablero()
    
    private static void hormigas(){
        ProcessBuilder pbHormiga=new ProcessBuilder("java","-cp",
            rutaProcesos,
            "HormigasDeLangton.Hormiga");
        try {
            Process p=pbHormiga.start();
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
                        
            //Envio la configuracion inicial al proceso hormiga
            bw.write(Integer.toString(filas)+" "+Integer.toString(columnas)+
                    "o"+Integer.toString(posHormigaFila)+" "+
                    Integer.toString(posHormigaColumna)+"o"+orientacion+"o"+
                    reglaColores+"\n"); bw.flush();
            
            //Mientras la hormiga no sobrepase el numero maximo de movimientos
            //sus movimientos continuan
            for (int i = 0; i < maxMoves; ++i) {
                //Recibo la posicion de la hormiga para enviarle
                //el color de la casilla
                bw.write("si"+"\n");
                bw.flush();
                String posHormiga = br.readLine();    
                String[] posHor = posHormiga.split(" ");
                posHormigaFila=Integer.parseInt(posHor[0]);
                posHormigaColumna=Integer.parseInt(posHor[1]);
                char respuestaColor=tablero[posHormigaFila][posHormigaColumna];
                bw.write(respuestaColor+"\n");               
                bw.flush();
                //El no indica que ya no es el primer tablero que se crea
                tablero("no");
            }
            bw.write("no"+"\n");
            bw.flush();
            bw.close();
            br.close();
            
            }catch (IOException ex) {
            System.out.println(ex);
            }
    }// hormigas()

}// Director