import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Cartas extends JFrame implements ActionListener {

    JLabel carta1, carta2, carta3, carta4;
    JPanel cartasPanel, buttonPanel;
    JButton obtener;

    // Ruta de la carpeta con las fotos
    private static final String RUTA_CARPETA_FOTOS = "src//baraja"; //CAMBIA RUTA

    Cartas() {
        //Creacion de las cartas
        //las cartas son labels
        carta1 = new JLabel();
        carta2 = new JLabel();
        carta3 = new JLabel();
        carta4 = new JLabel();
        cartasPanel = new JPanel();
        buttonPanel = new JPanel();
        obtener = new JButton("Obtener fotos");

        //Añadir listener al button de obtener las fotos
        obtener.addActionListener(this);

        //añadir las cartas y el button al panel, Y el panel al frame
        add(cartasPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        cartasPanel.add(carta1);
        cartasPanel.add(carta2);
        cartasPanel.add(carta3);
        cartasPanel.add(carta4);
        buttonPanel.add(obtener);

        //  Tamaños de las cartas y el panel
        carta1.setSize(100, 100);
        carta2.setSize(100, 100);
        carta3.setSize(100, 100);
        carta4.setSize(100, 100);
        cartasPanel.setSize(900, 300);

        //Visualizar Pantalla
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public ArrayList<String> obtenerRutasFotosEnCarpeta() {
    // este methodo devuelve una lista de Strings ArrayList<String> de las rutas de las fotos en la carpeta
    // la carpeta es una variable que esta definida arriba
        ArrayList<String> rutasFotos = new ArrayList<String>();
        File carpetaFotos = new File(RUTA_CARPETA_FOTOS);
        File[] archivos = carpetaFotos.listFiles();

        for (File archivo : archivos) {
            if (archivo.isFile()) {
                // guardar la ruta del archivo en la lista de rutasFotos
                String rutaArchivo = archivo.getAbsolutePath();
                rutasFotos.add(rutaArchivo);
            }
        }
        return rutasFotos;
    }

    public void obtenerCuatroFotosAleatorias() {
        // este methodo muestra 4 fotos de baraja aleatorio
        ArrayList<String> rutasFotos = obtenerRutasFotosEnCarpeta();
        Random rand = new Random();
        int numFotos = rutasFotos.size();
        int[] indices = new int[4];

        // generar 4 numeros aleatorion entre 0 y rutasFotos.size(), que no se repiten y asignarlos en la lista de indices
        for (int i = 0; i < indices.length; i++) {
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
        if (e.getSource() == obtener) {
            obtenerCuatroFotosAleatorias();
        }
    }

    public static void main(String[] args) {
        new Cartas();
    }
}