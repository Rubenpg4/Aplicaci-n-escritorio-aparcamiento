Para la compilacion del programa y empaquetamiento en un jar debemos:
1. Utilizar VSCode para la compilacion
2. Una vez compilado ejecutamos el comando para empaqeutar en jar:
"jar cvfm 'Parking&CO.jar' MANIFEST.MF -C bin/ ."

Para la ejecucion del programa simplemete hay que usar dentro del directorio el comando: 
"java -jar 'Parking&CO.jar'"

Durante la ejecucion del programa se puede modificar ./bin/data/language
con el objetivo de aumentar o reducir los idiomas disponibles y podremos
observar el cambio cuando en el menu pulsamos configuracion->idiomas