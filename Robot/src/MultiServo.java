
import gnu.io.*;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//interfaz con la que trabajaremos
public class MultiServo {
    //identificar los puertos que vamos a registrar 
Enumeration listaPuertos = CommPortIdentifier.getPortIdentifiers();
CommPortIdentifier puertoId ;
PrintStream arduinoOut ;
SerialPort puerto;
MultiServo(String pto){
    //si encutrea el pueto correcto hace la conexion
    while(listaPuertos.hasMoreElements()){
        puertoId = (CommPortIdentifier)listaPuertos.nextElement();
        if(puertoId.getName().equals(pto)){
            break;
        }
    }
    try {
        puerto = (SerialPort)puertoId.open("Serial", 1000);
        puerto.setSerialPortParams(28800, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        arduinoOut = new PrintStream(puerto.getOutputStream());
    } catch (PortInUseException | IOException | UnsupportedCommOperationException ex) {
        Logger.getLogger(MultiServo.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public void enviar(int num){
    arduinoOut.write(num);
}
    public static void main(String[] args) {
       GUI app = new GUI();
    }
 
}

