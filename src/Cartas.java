import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Cartas extends JFrame implements ActionListener {

    JLabel carta1, carta2, carta3, carta4;
    JPanel panel;
    JButton bot;

    // Ruta de la carpeta con las fotos
    private static final String RUTA_CARPETA_FOTOS = "src//baraja"; //CAMBIA RUTA

    Cartas() {


        //Crear objetos:
        carta1 = new JLabel();
        carta2 = new JLabel();
        carta3 = new JLabel();
        carta4 = new JLabel();
        panel = new JPanel();
        bot = new JButton("Obtener fotos");

        //Añadir listeners:
        bot.addActionListener(this);

        //Añadir objetos
        add(panel);
        panel.add(carta1);
        panel.add(carta2);
        panel.add(carta3);
        panel.add(carta4);
        panel.add(bot);

        //  Tamaños
        carta1.setSize(100, 100);
        carta2.setSize(100, 100);
        carta3.setSize(100, 100);
        carta4.setSize(100, 100);
        panel.setSize(900, 300);

        //Visualizar Pantalla y demás
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // FUNCIÓN LISTA DE RUTAS  DE FOTOS:
    public ArrayList<String> obtenerRutasFotosEnCarpeta() {

        ArrayList<String> rutasFotos = new ArrayList<String>();
        File carpetaFotos = new File(RUTA_CARPETA_FOTOS);
        File[] archivos = carpetaFotos.listFiles();

        for (File archivo : archivos) {

            if (archivo.isFile()) {

                String rutaArchivo = archivo.getAbsolutePath();
                rutasFotos.add(rutaArchivo);

            }
        }
        return rutasFotos;
    }

    // METODO MOSTRAR LAS FOTOS:
    public void obtenerCuatroFotosAleatorias() {

        ArrayList<String> rutasFotos = obtenerRutasFotosEnCarpeta();
        Random rand = new Random();
        int numFotos = rutasFotos.size();
        int[] indices = new int[4];


        for (int i = 0; i < 4; i++) {

            int index = rand.nextInt(numFotos);

            for (int j = 0; j < i; j++) {

                if (index == indices[j]) {

                    index = rand.nextInt(numFotos);
                    j = -1;

                }
            }

            indices[i] = index;
        }

        //MOSTRAR LAS FOTOS EN LAS LABELS
        carta1.setIcon(new ImageIcon(rutasFotos.get(indices[0])));
        carta2.setIcon(new ImageIcon(rutasFotos.get(indices[1])));
        carta3.setIcon(new ImageIcon(rutasFotos.get(indices[2])));
        carta4.setIcon(new ImageIcon(rutasFotos.get(indices[3])));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bot) {
            obtenerCuatroFotosAleatorias();
        }
    }

    public static void main(String[] args) {
        new Cartas();
    }
}