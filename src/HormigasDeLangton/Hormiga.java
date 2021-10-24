package HormigasDeLangton;
import java.util.Scanner;

public class Hormiga {
    static Scanner Scanner=new Scanner(System.in);
    private static int filas;
    private static int columnas;
    private static int posHormigaFila;
    private static int posHormigaColumna;
    private static String orientacion;
    private static String reglaColores;
    
    public static void main(String[] args) {
        String datos = Scanner.nextLine();
        transformarDatos(datos);
        movimientos();
    }// main()
    
    private static void movimientos(){
        String continuar="si";
        while ("si".equals(continuar)) {           
            continuar=Scanner.nextLine();
            //Enviar mensaje para saber el color y continuar segun la regla de colores
            System.out.println(posHormigaFila+" "+posHormigaColumna);
            String colorCasilla=Scanner.nextLine();
            determinarOrientacion(colorCasilla);

            //Cambio de orientación de la hormiga,
            //los if son para que el tablero sea toroidal
            switch (orientacion) {
                case "N": if (posHormigaFila-1<=0){
                              posHormigaFila=filas-1;
                          }else{
                                --posHormigaFila;
                          }; break;
                          
                case "S": if (posHormigaFila+1>=filas){
                              posHormigaFila=0;
                          }else{
                               ++posHormigaFila;
                          }; break;
                          
                case "O": if (posHormigaColumna-1<=0){
                              posHormigaColumna=columnas-1;
                          }else{
                              --posHormigaColumna;
                          }; break;
                          
                case "E": if (posHormigaColumna+1>=columnas){
                              posHormigaColumna=0;
                          }else{
                              ++posHormigaColumna;
                          }; break;
            }
        }
    }// movimientos()   
    
    private static String determinarOrientacion(String colorCasilla) {
        //switches para determinar la orientcion dependiendo de la regla de movimientos
        String[] giro = reglaColores.split(",");
        String nuevaOrientacion = "";
        switch (colorCasilla){
            case " " : nuevaOrientacion=giro[0]; break;
            case "#" : nuevaOrientacion=giro[1]; break;
            case "/" : nuevaOrientacion=giro[2]; break;
            case "&" : nuevaOrientacion=giro[3]; break;
        }
            if ("r".equals(nuevaOrientacion)) {
                switch (orientacion){
                    case "N" : orientacion="E"; break;
                    case "E" : orientacion="S"; break;
                    case "S" : orientacion="O"; break;
                    case "O" : orientacion="N"; break;
                }
            }else if("l".equals(nuevaOrientacion)) {
                switch (orientacion){
                    case "N" : orientacion="O"; break;
                    case "O" : orientacion="S"; break;
                    case "S" : orientacion="E"; break;
                    case "E" : orientacion="N"; break;
                    }
            }
        return orientacion;
    }// determinarOrientacion()
    
    private static void transformarDatos(String datos){
        //Metodo que transforma el string recibido del
        //proceso Director en datos manejables para el metodo movimientos
        String[] datosSeparados = datos.split("o");
        
        String tamanioTableroString = datosSeparados[0];
        String[]datosTamanio=tamanioTableroString.split(" ");
        filas=Integer.parseInt(datosTamanio[0]);
        columnas=Integer.parseInt(datosTamanio[1]);
        
        String posHormigaString = datosSeparados[1];
        String []datosPosicion=posHormigaString.split(" ");
        posHormigaFila=Integer.parseInt(datosPosicion[0]);
        posHormigaColumna=Integer.parseInt(datosPosicion[1]);
        
        orientacion = datosSeparados[2];
        reglaColores = datosSeparados[3];      
    }// transformarDatos()
    
}// Hormiga