#include <Servo.h>
#include <EEPROM.h>
#include  <LiquidCrystal.h>  //libreria para trabajar con la pantalla LCD

Servo motor1;  //se declara motor a utilizar
Servo motor2;  //se declara motor a utilizar
Servo motor3;  //se declara motor a utilizar

int num;    //variable que obtendra el valor enviado de java
int mov;    //variable que regulara el movimiento de lso servomotres 
int estado = 0; //Guarda el estado del boton
int salida = 0; //0= led esta apagado 1 encendido
int estadoAnterior = 0; //estado anterior del boton
//int pinzumbador = 13;    // pin del zumbador
int frecuencia = 220;      // frecuencia correspondiente a la nota La

int pin_Button = 12;        //pin que se utiliza para el boton de reproducir
int posiciones[300];        //el maximo de posiciones que se pueden guardar
int pin_Button_State = 0;   //boton que ejecutara movimientos
int pin_Button_State_Last = 0;  //boton a activar mediante pin_Button cuando este este en HIGH
//int contador = 0;
int datos = 0;            //contador que reproducira los valoras guardados


void setup() {
  Serial.begin(28800);
  motor1.attach(9);       //declaramos que pin utiizara el motor 1
  motor1.write(0);        //en que posicion iniciara el motor 1

  motor2.attach(10);     //declaramos que pin utiizara el motor 2
  motor2.write(52);      //en que posicion iniciara el motor 2

  motor3.attach(11);     //declaramos que pin utiizara el motor 3
  motor3.write(171);     //en que posicion iniciara el motor 3

  pinMode(pin_Button, INPUT);  //declaramos el boton de entrada
  pinMode(6,INPUT);  //declaramos el boton como entrada
  pinMode(8, OUTPUT);//declaramos el led como salida.
  pinMode(7, OUTPUT);//declaramos el led como salida

}

void loop() {
estado = digitalRead(6); //leer el estado del boton

  if(estado == HIGH && (estadoAnterior == LOW)){ //comprueba el estadom del boton para guardar el boton de paro
    salida = 1 - salida;
  }
  estadoAnterior = estado; //guarda el valor actual
  
  if(salida == 0){//si es estado es cero osea el boton sin preddsionar enciende el led
    digitalWrite(7, LOW);
    digitalWrite(8,HIGH);
    
  
  if (Serial.available() > 0) {    //comprobamos si es mayor a cero, si lo es ejecutara los movimientos y guardara
    num = Serial.read();          //lee el numero enviado desde java
    if (num >= 0 && num < 85) {   //si esta entre estas posiciones se movera y guardara
      mov = map(num, 0, 84, 0, 180);  //los movemos a una variable, donde le pasamos num con vaor maxiomo de 180
      motor1.write(mov);              //se mueve el motor con los parametros de mov
      posiciones[datos] = mov;        //guardamos los datos para despues reproducirlos
      datos++;                        //aumentamos las posiciones para guardar
    }
    if (num >= 85 && num < 170) {    //lee el numero enviado desde java
      mov = map(num, 85, 169, 0, 180);  //si esta entre estas posiciones se movera y guardara
      motor2.write(mov);                //se mueve el motor con los parametros de mov
      posiciones[datos] = mov;          //guardamos los datos para despues reproducirlos
      datos++;                          //aumentamos las posiciones para guardar
    }
    if (num >=170 && num <= 255) {    //lee el numero enviado desde java
      mov = map(num, 170, 255, 0, 180);   //si esta entre estas posiciones se movera y guardara
      motor3.write(mov);                  //se mueve el motor con los parametros de mov
      posiciones[datos] = mov;            //guardamos los datos para despues reproducirlos
      datos++;                            //aumentamos las posiciones para guardar
    }
  }
  //
pin_Button_State = digitalRead(pin_Button_State);  //lee el estado
if(pin_Button_State != pin_Button_State_Last){ //si es diferente pin_Button_State que se obtuvo pin_Button_State con pin_Button_State_Last se ejecutara el codigo
if(pin_Button_State == HIGH) {        //si esta en HIGH se ejecuta
  int i;                              //se incializa una variable para recorrer el arreglo
  for(i = 0; i < 300; i = i +1){    //se declara un for donde para recorrer el arreglo de posiciones guardadas
    Serial.println(posiciones[i]);         //se lle por serial las posiciones 
    delay(100);                            //le damos un retraso de 100 para que no haga movimientos brusos
    motor1.write(posiciones[i]);          //activa los pasos de motor 1 que se hicieron desde java
    delay(100);                           //le damos un retraso de 100 para que no haga movimientos brusos
    motor2.write(posiciones[i]);          //activa los pasos de motor 2 que se hicieron desde java
    delay(100);                           //le damos un retraso de 100 para que no haga movimientos brusos
    motor3.write(posiciones[i]);          //activa los pasos de motor 3 que se hicieron desde java
     } 
    }
  }
  }if(salida == 1){ //si es presionado con el valor 1 
      digitalWrite(7,HIGH);
    digitalWrite(8, LOW);


   // tone(pinzumbador,frecuencia);    // inicia el zumbido
    //delay(1500);                    
    //noTone(pinzumbador);               // lo detiene a los dos segundos
    //delay(1000);
  }
}
