
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
//instalamos la libreria PanamaHitek_Arduino-3.0.0
public class JavaRX {
    //Se crea una instancia de la librería PanamaHitek_Arduino
    PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    //esta libreria ya nos hace toda la conexion solo falta asignarle el puerto 
    //al que nos vamos a conectar
    public JavaRX() {//en el contructor
        try {//
            ino.arduinoTX("COM3", 9600);//asignamos el pueto que vamos a usar
        } catch (ArduinoException ex) {//por si el puerto no se encuetra manda error
            Logger.getLogger(JavaRX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
