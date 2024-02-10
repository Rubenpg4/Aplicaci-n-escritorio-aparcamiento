import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class primeraVista extends JFrame {
    private JFrame owner = this;
    private archivos myArchivo;

    public primeraVista() {
        myArchivo = new archivos();

        this.setTitle(myArchivo.cadenasIdiomas.get(0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setResizable(false);
    
        // Crear la barra de menú usando la clase BarraMenu
        JMenuBar menu = crearBarraMenu();
        this.setJMenuBar(menu);

        // Panel superior para el título y subtitulo
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(106, 142, 174));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS)); 

        JLabel titulo = new JLabel(myArchivo.cadenasIdiomas.get(0));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Arial", Font.BOLD, 32));

        JLabel subtitulo = new JLabel(myArchivo.cadenasIdiomas.get(1));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setFont(new Font("Arial", Font.BOLD, 32));

        panelSuperior.add(titulo);
        panelSuperior.add(Box.createRigidArea(new Dimension(0, 10)));
        panelSuperior.add(subtitulo);

        // Panel central para el nombre de cada parking
        JPanel panelMedio = new JPanel();
        panelMedio.setBackground(new Color(155, 209, 229));
        panelMedio.setLayout(new GridLayout(1, 4, 10, 10));
        panelMedio.setPreferredSize(new Dimension(this.getWidth(), 40));

        JLabel texto1 = crearEtiquetaCentrada(myArchivo.cadenasIdiomas.get(tipoParking.HOSPITAL.getindiceNombre()));
        JLabel texto2 = crearEtiquetaCentrada(myArchivo.cadenasIdiomas.get(tipoParking.CENTRO_COMERCIAL.getindiceNombre()));
        JLabel texto3 = crearEtiquetaCentrada(myArchivo.cadenasIdiomas.get(tipoParking.PARQUE.getindiceNombre()));
        JLabel texto4 = crearEtiquetaCentrada(myArchivo.cadenasIdiomas.get(tipoParking.MUSEO.getindiceNombre()));

        texto1.setFont(new Font("Arial", Font.BOLD, 20));
        texto2.setFont(new Font("Arial", Font.BOLD, 20));
        texto3.setFont(new Font("Arial", Font.BOLD, 20));
        texto4.setFont(new Font("Arial", Font.BOLD, 20));

        panelMedio.add(texto1);
        panelMedio.add(texto2);
        panelMedio.add(texto3);
        panelMedio.add(texto4);

        // Panel inferior para los botones
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(1, 4, 10, 10)); 
        panelInferior.setBackground(new Color(209, 250, 255));
        panelInferior.setPreferredSize(new Dimension(this.getWidth(), 175));

        // Crear botones con imágenes
        JButton btnHospital = crearBotonConImagen(tipoParking.HOSPITAL);
        JButton btnCentroComercial = crearBotonConImagen(tipoParking.CENTRO_COMERCIAL);
        JButton btnParque = crearBotonConImagen(tipoParking.PARQUE);
        JButton btnMuseo = crearBotonConImagen(tipoParking.MUSEO);

        btnHospital.addActionListener(e -> new segundaVista(owner, tipoParking.HOSPITAL, myArchivo));
        btnCentroComercial.addActionListener(e -> new segundaVista(owner, tipoParking.CENTRO_COMERCIAL, myArchivo));
        btnParque.addActionListener(e -> new segundaVista(owner, tipoParking.PARQUE, myArchivo));
        btnMuseo.addActionListener(e -> new segundaVista(owner, tipoParking.MUSEO, myArchivo));

        // Añadir botones al panel de botones
        panelInferior.add(btnHospital);
        panelInferior.add(btnCentroComercial);
        panelInferior.add(btnParque);
        panelInferior.add(btnMuseo);

        // Añadir paneles a la this
        this.add(panelSuperior, BorderLayout.NORTH);
        this.add(panelMedio, BorderLayout.CENTER);
        this.add(panelInferior, BorderLayout.SOUTH);
        
        //Ajustar this
        this.pack();

        // Mostrar la this
        this.setResizable(false);
        this.setVisible(true);

    }

    private static JLabel crearEtiquetaCentrada(String texto) {
        JLabel etiqueta = new JLabel(texto, SwingConstants.CENTER);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 12));
        etiqueta.setHorizontalTextPosition(JLabel.CENTER);
        etiqueta.setVerticalTextPosition(JLabel.CENTER);
        return etiqueta;
    }

    private static JButton crearBotonConImagen(tipoParking myTipo) {
        JButton boton = new JButton();
        Image img = new ImageIcon(myTipo.getLogo()).getImage();
        boton.setHorizontalTextPosition(JLabel.CENTER);
        boton.setVerticalTextPosition(JLabel.CENTER);
        boton.setIcon(new ImageIcon(img));
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);

        return boton;
    }

    public JMenuBar crearBarraMenu() {
        JMenuBar menu = new JMenuBar();

        // Crear los menús
        JMenu menuParking = new JMenu(myArchivo.cadenasIdiomas.get(2));
        JMenu menuConfiguracion = new JMenu(myArchivo.cadenasIdiomas.get(7));
        JMenu menuAyuda = new JMenu(myArchivo.cadenasIdiomas.get(20));

        // Añadir submenús a 'Parking'
        menuParking.add(crearItemParking(tipoParking.HOSPITAL));
        menuParking.add(crearItemParking(tipoParking.CENTRO_COMERCIAL));
        menuParking.add(crearItemParking(tipoParking.PARQUE));
        menuParking.add(crearItemParking(tipoParking.MUSEO));

        // Añadir submenús a 'Configuracion'
        menuConfiguracion.add(crearItemPreferencias());
        menuConfiguracion.add(crearItemIdioma());

        // Añadir submenús a 'Ayuda'
        menuAyuda.add(crearIteMasInformacion());

        // Añadir menús a la barra de menú
        menu.add(menuParking);
        menu.add(menuConfiguracion);
        menu.add(menuAyuda);

        return menu;
    }

    private JMenuItem crearItemParking(tipoParking myTipo) {
        JMenuItem item = new JMenuItem(myArchivo.cadenasIdiomas.get(myTipo.getindiceNombre()));
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new segundaVista(owner, myTipo, myArchivo);
            }
        });
        return item;
    }

    private JMenuItem crearItemPreferencias() {
        JMenuItem item = new JMenuItem(myArchivo.cadenasIdiomas.get(8));
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new preferencias(owner, myArchivo);
            }
        });
        return item;
    }

    private JMenuItem crearItemIdioma() {
        JMenuItem item = new JMenuItem(myArchivo.cadenasIdiomas.get(18));
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new idiomas(owner, myArchivo);
            }
        });
        return item;
    }

    private JMenuItem crearIteMasInformacion() {
        JMenuItem item = new JMenuItem(myArchivo.cadenasIdiomas.get(21));
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(owner, myArchivo.cadenasIdiomas.get(22), myArchivo.cadenasIdiomas.get(36), JOptionPane.INFORMATION_MESSAGE);
            }
        });
        return item;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new primeraVista();
            }
        });
    }

}
