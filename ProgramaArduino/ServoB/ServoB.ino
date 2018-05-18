#include <Servo.h>  //libreria para manejar los servomotores del brazo
#include  <LiquidCrystal.h>  //libreria para trabajar con la pantalla LCD


Servo motor1;   //se declara el servomotor numero 1
Servo motor2;   //se declara el servomotor numero 2
Servo motor3;   //se declara el servomotor numero 1
int num;        //varibale que recibe los parametros que se mandan desde java y el cual contribuira
int mov;
int estado = 0; //Guarda el estado del boton
int salida = 0; //0= led esta apagado 1 encendido
int estadoAnterior = 0; //estado anterior del boton
//int pinzumbador = 13;    // pin del zumbador
int frecuencia = 220;      // frecuencia correspondiente a la nota La
LiquidCrystal lcd (7, 6, 5, 4, 3, 2);

void setup(){
  Serial.begin(28800);
  //inicializamos la posicion inicial del servo de cada uno
  //cada servo en sus respectivos pin
  motor1.attach(11);
  motor1.write(171);

  motor2.attach(12);
  motor2.write(52);

  motor3.attach(13);
  motor3.write(0);
  
  pinMode(8,INPUT);  //declaramos el boton como entrada
  pinMode(9, OUTPUT);
  pinMode(10, OUTPUT);
  pinMode(0,INPUT);
  pinMode(1,INPUT);

     
}

void loop(){
  estado = digitalRead(8); //leer el estado del boton

  if(estado == HIGH && (estadoAnterior == LOW)){
    salida = 1 - salida;
  }
  estadoAnterior = estado; //guarda el valor actual
  
  if(salida == 0){
    digitalWrite(10, LOW);
    digitalWrite(9,HIGH);
    lcd.begin(16, 2); //Nos indica donde empezara a excribir el LCD.    
  lcd.print("Brazo Trabajando");
    
  if(Serial.available()>0){//incializamos el serial
    num = Serial.read();//leemos el serial para la comunicacion
    if(num >=0 && num <85){//valores de los servos dependiendo del programa que mande
      mov = map(num, 0, 84, 0, 180);
      motor1.write(mov);
    }
    if(num >=85 && num <170){//valores de los servos dependiendo del programa que mande
      mov = map(num, 85, 169, 0, 180);
      motor2.write(mov);
    }
    if(num >=170 && num <= 255){//valores de los servos dependiendo del programa que mande
      mov = map(num, 170, 255, 0, 180);
      motor3.write(mov);
    }
  }
  }if(salida == 1){
      digitalWrite(10,HIGH);
    digitalWrite(9, LOW);

    lcd.begin(16, 2); //Nos indica donde empezara a excribir el LCD.    
  lcd.print("Brazo Detenido");

   // tone(pinzumbador,frecuencia);    // inicia el zumbido
    //delay(1500);                    
    //noTone(pinzumbador);               // lo detiene a los dos segundos
    //delay(1000);
  }
  
} 
