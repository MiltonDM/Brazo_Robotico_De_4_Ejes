
import com.panamahitek.ArduinoException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import jssc.SerialPortException;

//interfaz con la que trabajaremos
public class GUI extends KeyAdapter implements ActionListener, ChangeListener {

    JavaRX conn = new JavaRX();

    JFrame vt = new JFrame("Servos");
    JLabel txt1 = new JLabel("Selecciona el puerto de tu Arduino");
    //selecionamos el puerto donde estamos en el arduino para trabajar
    String[] puertosOp = {"COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9",};
    JComboBox puertosSelec = new JComboBox(puertosOp);
    JPanel panel1 = new JPanel();
    //Un slider para cada servo que vamos a usar 
    JSlider motor1 = new JSlider(JSlider.VERTICAL, 0, 180, 0);
    JTextField manual1 = new JTextField(3);
    JPanel m1 = new JPanel();
    JSlider motor2 = new JSlider(JSlider.VERTICAL, 0, 180, 0);
    JTextField manual2 = new JTextField(3);
    JPanel m2 = new JPanel();
    JSlider motor3 = new JSlider(JSlider.VERTICAL, 0, 180, 0);
    JTextField manual3 = new JTextField(3);
    JPanel m3 = new JPanel();

    JPanel panel2 = new JPanel();
    //botones para enviar y guirar el motor a paso 
    JButton enviar = new JButton("Enviar");
    JPanel panel3 = new JPanel();

    JButton btnIzq = new JButton("Izquierda");
    JPanel panel4 = new JPanel();

    JButton btnDer = new JButton("Derecha");
    JPanel panel5 = new JPanel();
    //llamamos a la clase Multiservo
    MultiServo conexion;

    GUI() {//acomodamos todo en la interfaz como que ramos que aparesca
        vt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vt.setResizable(false);
        vt.setLayout(new BorderLayout());
        vt.add(panel1, BorderLayout.NORTH);
        vt.add(panel2, BorderLayout.CENTER);

        panel1.add(txt1);
        panel1.add(puertosSelec);
        puertosSelec.addActionListener(this);

        panel2.add(m1);
        panel2.add(new JLabel("    "));
        panel2.add(m2);
        panel2.add(new JLabel("    "));
        panel2.add(m3);

        m1.setLayout(new BorderLayout());
        m1.add(motor1, BorderLayout.CENTER);
        m1.add(manual1, BorderLayout.SOUTH);
        motor1.addChangeListener(this);
        manual1.addKeyListener(this);

        m2.setLayout(new BorderLayout());
        m2.add(motor2, BorderLayout.CENTER);
        m2.add(manual2, BorderLayout.SOUTH);
        motor2.addChangeListener(this);
        manual2.addKeyListener(this);

        m3.setLayout(new BorderLayout());
        m3.add(motor3, BorderLayout.CENTER);
        m3.add(manual3, BorderLayout.SOUTH);
        motor3.addChangeListener(this);
        manual3.addKeyListener(this);

        vt.add(panel3, BorderLayout.SOUTH);
        panel3.add(enviar);

        vt.add(panel4, BorderLayout.EAST);
        panel4.add(btnIzq);

        vt.add(panel5, BorderLayout.WEST);
        panel5.add(btnDer);

        btnDer.addActionListener(this);
        btnIzq.addActionListener(this);
        enviar.addActionListener(this);
        motor1.setEnabled(false);
        motor2.setEnabled(false);
        motor3.setEnabled(false);
        manual1.setEditable(false);
        manual2.setEditable(false);
        manual3.setEditable(false);
        vt.pack();
        vt.setVisible(true);//para que se muestre

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //le damos funcionalidad a los botones y al combo box
        Object f = e.getSource();
        if (f == puertosSelec) {//si el pueto es el correcto activa los slider
            motor1.setEnabled(true);
            motor2.setEnabled(true);
            motor3.setEnabled(true);
            String op = (String) puertosSelec.getSelectedItem();
            conexion = new MultiServo(op);

            manual1.setEditable(true);
            manual2.setEditable(true);
            manual3.setEditable(true);

            puertosSelec.setEnabled(false);
        }
        if (f == enviar) {//envia los datos que se regitran
            String val;
            val = manual1.getText();
            motor1.setValue(Integer.parseInt(val));

            val = manual2.getText();
            motor2.setValue(Integer.parseInt(val));

            val = manual3.getText();
            motor3.setValue(Integer.parseInt(val));
        }
        if (f == btnIzq) {//manda a llamar a la clase JavaRX para mover el motor
            //si se encutra un 1 o un 0
            try {
                conn.ino.sendData("1");
            } catch (ArduinoException | SerialPortException ex) {
                Logger.getLogger(JavaRX.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//boton derecha
        if (f == btnDer) {
            try {
                conn.ino.sendData("0");
            } catch (ArduinoException | SerialPortException ex) {
                Logger.getLogger(JavaRX.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        //los moviminetos que van hacer los servos con la conexion del serial
        Object f = e.getSource();
        String val;
        if (f == motor1) {
            val = String.valueOf(motor1.getValue());
            conexion.enviar(Map.rTres(motor1.getValue()));
            manual1.setText(val);
        }
        if (f == motor2) {
            val = String.valueOf(motor2.getValue());
            conexion.enviar(Map.rTres(motor2.getValue()) + 85);
            manual2.setText(val);
        }
        if (f == motor3) {
            val = String.valueOf(motor3.getValue());
            conexion.enviar(Map.rTres(motor3.getValue()) + 170);
            manual3.setText(val);
        }
    }

    @Override//por si queremos mover los slider con los teclado felcas
    public void keyReleased(KeyEvent e) {
        Object f = e.getSource();
        int tecla = e.getKeyCode();
        String val;
        if (f == manual1 && tecla == KeyEvent.VK_ENTER) {
            val = manual1.getText();
            motor1.setValue(Integer.parseInt(val));
        }
        if (f == manual2 && tecla == KeyEvent.VK_ENTER) {
            val = manual2.getText();
            motor2.setValue(Integer.parseInt(val));
        }
        if (f == manual3 && tecla == KeyEvent.VK_ENTER) {
            val = manual3.getText();
            motor3.setValue(Integer.parseInt(val));
        }
    }

    private static class Map {

        static int rTres(int n) {
            //85 x n/180
            int resultado = (85 * n) / 180;
            return resultado;
        }
    }
}
