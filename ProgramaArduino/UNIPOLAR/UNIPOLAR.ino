
#include <Stepper.h>//libreria para el motor a pasos
int dado;//variable 
const int Motor = 500;
Stepper MeuMotor(Motor, 8, 10, 9, 11);//posicion de los pines para el motor a pasos

void setup() {
  MeuMotor.setSpeed(60);
  Serial.begin(9600);
  }

void loop() {
  if (Serial.available() > 0) {//inicializamos el serial en 0
    dado = Serial.read();//le el dato
    if (dado == '1') {//si el dato es igual a 1 guira a la derecha
      MeuMotor.step(100);//movimineto de el motor
    } else if (dado == '0') {//si el dato es igual a 0 guira a izquierda
      MeuMotor.step(-100);//movimineto de el motor
    }
  }
}

