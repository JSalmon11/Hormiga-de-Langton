# Hormiga de Langton

### Introducción

Las hormigas de Langton son un caso particular de autómata bidimensional. Su versión más 
sencilla sería la siguiente:

La hormiga se encuentra en una posición (x,y) de un tablero bidimensional, orientada en una 
dirección determinada (Norte, Sur, Este, Oeste). Cada posición puede tener dos colores, 
blanco y negro. Si la hormiga se encuentra en una posición de color blanco, gira a la derecha, 
cambia el color de la posición a negro y avanza una posición. Si se encuentra en una posición de 
color negro, gira a la izquierda, cambia el color de la posición a blanco y avanza una posición. 
La trayectoria que describe la hormiga de Langton básica está muy estudiada. Al cabo de unas 
11.000 iteraciones, entra en una trayectoria oblicua recta infinita.

Se dice que la hormiga anteriormente descrita sigue la regla ‘rl’ (ante el color 0 gira a la derecha, 
ante el color 1 gira a la izquierda). El comportamiento se puede extender a cualquier número de 
colores. Por ejemplo, la regla ‘rrll’ significa que ante los colores 0 y 1 gira a la derecha, y ante los 
colores 2 y 3 gira a la izquierda. En todos los casos, la hormiga convierte el color 0 en el 1, el 1 en 
el 2, el 2 en el 3 y el 3 en el 0 (es cíclico).

Este programa genera una salida del tablero con todos los movimientos de la hormiga en un tablero, en formato .txt.
Y es un programa desarrollado con programación de procesos, para poder optimizarlo lo máximo posible, gracias a ello puede realizar
10000 movimientos en unos 10 segundos, dependiendo del ordenador y de la carga del procesador en ese momento.

## Comenzando 🚀

Puedes decargar el código y ejecutarlo en el IDE Java que quieras.

### Pre-requisitos 📋

-Un IDE de programación Java, el programa está hecho en NetbBeans, por lo que sería la mejor opción para ejecutarlo en tu pc sin problemas.

-Mínimo la versión 8 de Java, el programa usa el Jdk 1.8.

### Instalación 🔧

Puedes decargar el código o el proyecto entero.
En ambos casos si usas un IDE distinto de NetBeans deberás revisar el símbolo que representa a la hormiga, ya que es el símbolo del yen (¥),
por lo que puede dar problemas con la codificación UTF8.


## Ejecución ⚙️

Para ejecutar el programa de manera personalizada hay que editar el fichero FicheroConfiguracionHormiga.txt con los parámetros deseados para la ejecución.

IMPORTANTE leer el fichero FicheroPROTOCOLOconfiguracion.txt para saber cómo editar el fichero FicheroConfiguracionHormiga.txt.

Un ejemplo de ese fichero configurado sería:
```
Filas=51;
Columnas=111;
Regla de colores=r,l;
Orientacion=N;
Fila inicial de la hormiga=26;
Columna inicial de la hormiga=51;
Numero de movimientos de la hormiga=12000;
Ruta de los procesos=F:\\Procesos\\Hormigas de Langton\\build\\classes;
```
Los nombres de las variables editables son bastante autoexplicativas. Pero una breve explicación sería:
```
Filas:                                 Número de filas del tablero.
Columnas:                              Número de columnas del tablero.
Regla de colores;                      Regla de colores a elegir r(right, derecha) l(left,izquierda), máximo de 4 reglas. Diferentes reglas resultan en diferentes tableros,
                                       unos más simétricas, otras más caóticas.
Orientacion.                           Orientación inicial de la hormiga(N,S,E,O).
Fila inicial de la hormiga:            Fila de la posición en la que comienza la hormiga.
Columna inicial de la hormiga:         Columna de la posición en la que comienza la hormiga.
Numero de movimientos de la hormiga:   Máximo de movimientos de la hormiga, a partir de los 11000 empieza a entrar en el bucle de trayectoria oblícua
                                       si la regla es de de 2 colores y r,l o l,r.
Ruta de los procesos:                  Ruta en la que se encuentran las clases dentro del Build, ya que el rpograma está desarrollado con procesos.
```

Último tablero impreso en el .txt con el ejemplo(tiene el bucle de trayectoria oblícua):
```
      # #                                                                                                      
      ##                                                                                                       
                                                                                                               
                                                                                                               
                                           ##  ############  ##                                                
                                          #  ####          #  ##                                               
                                         ###   ##            ## #                                              
                                         # #  #         #  #    #                                              
                                         ## # #         ###       #                                            
                                 #  ### #    #     #     ## ##  ###                                            
                                #  ##  # #   ## #### ##   # #  # ##  ##                                        
                                # ##    #    # ##  ### # #     ###   ###                                       
                                #####   #  ##### # #  ####  #   ### # # #                                      
                                 #  ###########  ## ## ###### # ### #   #                                      
                                   ##  #   # # ## ## ## #   ##### ### ##                                       
                                # # #####   ## ###   #   # #  ####    # ##                                     
                                  # ## #      ## ##   #  ##     ## #     ##                                    
                                #¥    #  ### ###  #  ##     #   ### ##  ## #                                   
                               #  ### # #   ## ##   ###  #    #  ## ####   #                                   
                              ###    #  # #  # # #### ##  # ## ###  #     #                                    
                             #  ###   ###    #  # ###  #      ### ## #  #  ##                                  
                            ###    #    # ## # ##  ##  ##### ####  #### ##   #                                 
                           #  ###   ## #  # ### # # ##      ##   # # #    #   #                                
                          ###    # ## ###  ## #   ##       #### ####   #      #                                
                         #  ###   ##  #   ##  ########### #  ####  #    #    #                                 
                        ###    # ##      # ####  ##  #########  #  ##    #  ##                                 
                       #  ###   ##   ##  # ##   ## ## ### ###   #  # ##  #### #                                
                      ###    # ##   #  # ###### ## # ## # #    ### ###   ##   #                                
                     #  ###   ##   #     ##### # #####     # #  ## #    ##   #                                 
                    ###    # ##    #     # ## ##### ##  # #   #  #  ## #  #  #                                 
                   #  ###   ##     #    #   #### #  ##### ##   ##########   ##                                 
                  ###    # ##      # ##   ##   #  #   ####  #   ## #### ##                                     
                 #  ###   ##        ##### #  ##   ## #   #    # #  #  #  # #                                   
                ###    # ##          ##  ## # # #    ## ## # # ##  #  ##  ##                                   
               #  ###   ##                 #  #    # ######## # # ##  #### #                                   
              ###    # ##                  #  #   #       ## ##   #  #  ## #                                   
             #  ###   ##                    #  #  #      #  ##  ##   ## ####                                   
            ###    # ##                      ##   #       ##  ##    #   # ###                                  
           #  ###   ##                            # ##  ####    #### ### ####                                  
          ###    # ##                              ##  ####    ##  # ## # #  #                                 
         #  ###   ##                                ##    ##    ## ### ## #####                                
        ###    # ##                                                # ## #  ####                                
       #  ###   ##                                                     ## ## ##                                
      ###    # ##                                                      ##                                      
      ## ##   ##                                                     # ##  #### #                              
        #  # ##                                                     #  # ###  ###                              
        ##  ##                                                      # ## #  #  #                               
      # # # #                                                        ##      ##                                
      ## ##                                                           ##                                       
       ##                                                                                                      
        ##                                                                                                     

```
* [Salida de tablero con el ejemplo](https://raw.githubusercontent.com/JSalmon11/Hormiga-de-Langton/main/tablero.txt)

## Construido con 🛠️

* [NetBeans](https://netbeans.apache.org/download/index.html) - El IDE usado
* [Java 8(jdk1.8)](https://www.java.com/es/download/ie_manual.jsp) - Versión de Java utilizada
