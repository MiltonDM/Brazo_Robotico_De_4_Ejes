# Brazo_Robotico_De_4_Ejes
El propósito de esta práctica es desarrollar paso a paso un proyecto para controlar y programar un Brazo Robot, simulando las funciones básicas de un robot industrial. 

# Proyecto Arduino con una interfaz en Java.
>Creado por: Milton Alexis Durán Moreno.<br />
>Creado por: Luis Eduardo Hernadez Magaña.<br />
>Creado por: Jorge Alderto Sanchez Maldona.<br />

***Correo de los contactos***
>Contacto: duran.milton.92@hotmail.com<br />
>Contacto: jorgealberto_sanchezmaldonado@outlook.com <br />

## El robot debe tener dos funciones básicas:
- **Programar:** Registrar las posiciones de los brazos en tres dimensiones (cada registro es un "paso", un programa consiste en una serie de pasos). 
- **Ejecutar:** Realice en secuencia las posiciones registradas en el "Programa". El robot ejecutará el programa hasta que se use el comando "ABORTAR

**Programas:**
- Java
- Arduino IDE

**Materiales:**
- 2 Arduinos Uno
- Protoboard.
- Driver Uln2003 .
- Motores paso a paso: 28BYJ-48.
- ServoMotor: TowerPro SG90 9G Mini.
- Potenciometro (10k).
- Pantalla LCD.
- Cables.
- Leds.
- Pulsadore (botones).
- resistencias 200 homs
- Base para poner los servos.

## Características principales: 
 - El proyecto se usa para controlar robots con  4 DOF ("Grados de libertad").
 - El robot se debe controlar en modo "REMOTO" (a través de una programa en java por medio del puerto serial).
 - La información para el usuario se podrá proporcionar a través de LEDS de colores, una pantalla LCD de 2 líneas y/ó sonido (un zumbador).
 - Debe de contener un botón de paro de emergencia (Físico).     
 - Si existe un fallo y/o corte de energía, después de restablecerse la corriente el robot debe de continuar el programa (aunque este no se encuentre conectado a la aplicación).
 - Los brazos robóticos se pueden clasificar de acuerdo con el número de "articulaciones" o "Grados de libertad" (DOF) que tienen.             
     - La "Base", o "Cintura", por lo general puede girar el brazo 180o o 360o, dependiendo del   tipo de Servo utilizado (aquí en este proyecto, se debe utilizar un motor a pasos para girar 360o)
     - El "Hombro" es el responsable de "levantar o bajar" el brazo verticalmente 
     - El "codo" hará que el brazo "avance o retroceda".
     - La "Garra" o "Pinza" funciona abriendo o cerrándose para "agarrar cosas". 

Librerias: para la comunicacion Arduino con Java

    >RXTXcomm
    >PanamaHitek_Arduino-3.0.0

## Diseño electrónico
![alt tag](https://github.com/MiltonDM/UNIDAD_3/blob/master/image/1.png)

## Diseño Interfaz Java
![alt tag](https://github.com/MiltonDM/UNIDAD_3/blob/master/image/interfaz%201.png)
## Vista previa de Resultados:

<table>
<tr>
<td><img src=https://github.com/MiltonDM/UNIDAD_3/blob/master/image/IMG_20180411_195835602_BURST001.jpg></td>
<td><img src=https://github.com/MiltonDM/UNIDAD_3/blob/master/image/IMG_20180411_195900047_BURST000_COVER_TOP.jpg></td>
<td><img src=https://github.com/MiltonDM/UNIDAD_3/blob/master/image/IMG_20180411_195905173_BURST000_COVER_TOP.jpg></td>
</tr>
</table>
