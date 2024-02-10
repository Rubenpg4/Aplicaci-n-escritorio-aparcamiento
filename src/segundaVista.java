import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Vector;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class segundaVista extends JDialog {

    // Dimensiones para el grid
    private static final int GRID_SIZE = 10;
    private gridCasilla[][] myGridCasilla = new gridCasilla[GRID_SIZE][GRID_SIZE];
    private JDialog subowner = this;
    private archivos myArchivo;
    private tipoParking myParking;
    private grid myGrid;

    private JFrame owner;
    private JPanel panelGrid;
    private JToggleButton btnCoche;
    private JToggleButton btnMoto;
    private JCheckBox checkEspecificacion1;
    private JCheckBox checkEspecificacion2;
    private JCheckBox checkEspecificacion3;
    private JCheckBox checkEspecificacion4;

    public segundaVista(JFrame owner, tipoParking mytipo, archivos myArchivo) {
        super(owner, true);

        this.myArchivo = myArchivo;
        myParking = mytipo;

        // Configuración inicial de la ventana
        this.setTitle("Parking&CO - " + myArchivo.cadenasIdiomas.get(mytipo.getindiceNombre()));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(900, 450);
        this.getContentPane().setLayout(new BorderLayout());

        // Crear panel de filtro
        JPanel panelFiltro = crearPanelFiltro((int)(this.getWidth()*0.25), this.getHeight());
        panelFiltro.setPreferredSize(new Dimension((int)(this.getWidth()*0.3), this.getHeight())); 

        // Crear panel de grid
        JPanel panelGrid = crearPanelGrid((int)(this.getWidth()*0.5), this.getHeight(), myParking);
        panelGrid.setPreferredSize(new Dimension((int)(this.getWidth()*0.425), this.getHeight()));

        // Crear leyenda
        JPanel panelLeyenda = crearPanelLeyenda((int)(this.getWidth()*0.25), this.getHeight());
        panelLeyenda.setPreferredSize(new Dimension((int)(this.getWidth()*0.275), this.getHeight()));

        // Añadir los paneles a la ventana
        this.getContentPane().add(panelFiltro, BorderLayout.WEST);
        this.getContentPane().add(panelGrid, BorderLayout.CENTER);
        this.getContentPane().add(panelLeyenda, BorderLayout.EAST);

        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    private JPanel crearPanelFiltro(int width, int height) {
        Dimension newSize;

        // Panel principal para el filtro
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(155, 209, 229));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Texto filtro e icono refrescar
        JPanel panelTextoEIcono = new JPanel();
        panelTextoEIcono.setBackground(new Color(155, 209, 229));
        panelTextoEIcono.setLayout(new GridLayout(1, 2, 1, 1)); 
        panelTextoEIcono.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTextoEIcono.setAlignmentY(Component.CENTER_ALIGNMENT);

        newSize = new Dimension(new Dimension(width-1, (int)(height*0.25)));
        panelTextoEIcono.setMaximumSize(newSize);
        panelTextoEIcono.setPreferredSize(newSize);
        
        JLabel textFiltro = new JLabel(myArchivo.cadenasIdiomas.get(23), SwingConstants.RIGHT);
        textFiltro.setFont(new Font("Arial", Font.BOLD, 33));
        textFiltro.setAlignmentX(Component.CENTER_ALIGNMENT);
        textFiltro.setAlignmentY(Component.CENTER_ALIGNMENT);

        newSize = new Dimension(new Dimension(96, 5));
        textFiltro.setMaximumSize(newSize);
        textFiltro.setPreferredSize(newSize);
 
        JButton btnRefrescar = crearBotonConImagen("bin/media/002-actualizar-flecha.png");
        panelTextoEIcono.add(textFiltro);
        panelTextoEIcono.add(btnRefrescar);

        // Botones de coche y moto
        btnCoche = new JToggleButton(myArchivo.cadenasIdiomas.get(9));
        btnMoto = new JToggleButton(myArchivo.cadenasIdiomas.get(10));
        ButtonGroup grupoBotones = new ButtonGroup();

        newSize = new Dimension((int)(width*0.45), 30);

        btnCoche.setMaximumSize(newSize);
        btnCoche.setPreferredSize(newSize);
        btnMoto.setMaximumSize(newSize);
        btnMoto.setPreferredSize(newSize);

        grupoBotones.add(btnCoche);
        grupoBotones.add(btnMoto);

        String selecionCocheMoto = myArchivo.misPreferencias.get(1);
        if (!selecionCocheMoto.equals("default")) {
            if (selecionCocheMoto.equals("coche")) { btnCoche.setSelected(true); }
            if (selecionCocheMoto.equals("moto")) { btnMoto.setSelected(true); }
        }
        
        // Panel para contener los botones toggle
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(155, 209, 229));
        panelBotones.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBotones.setAlignmentY(Component.CENTER_ALIGNMENT);

        newSize = new Dimension(width-1, (int)(height*0.2));
        panelBotones.setMaximumSize(newSize);
        panelBotones.setPreferredSize(newSize);
        
        panelBotones.add(btnCoche);
        panelBotones.add(btnMoto);

        // Checkbox de especificaciones
        JPanel panelEspecificaciones = new JPanel();
        panelEspecificaciones.setBackground(new Color(155, 209, 229));
        panelEspecificaciones.setLayout(new BoxLayout(panelEspecificaciones, BoxLayout.Y_AXIS));
        panelEspecificaciones.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        panelEspecificaciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEspecificaciones.setAlignmentY(Component.CENTER_ALIGNMENT);

        newSize = new Dimension(new Dimension(width-1, (int)(height*0.33)));
        panelEspecificaciones.setMaximumSize(newSize);
        panelEspecificaciones.setPreferredSize(newSize);
         
        checkEspecificacion1 = new JCheckBox(myArchivo.cadenasIdiomas.get(11));
        checkEspecificacion2 = new JCheckBox(myArchivo.cadenasIdiomas.get(12));
        checkEspecificacion3 = new JCheckBox(myArchivo.cadenasIdiomas.get(13));
        checkEspecificacion4 = new JCheckBox(myArchivo.cadenasIdiomas.get(14));

        checkEspecificacion1.setFont(new Font("Arial", Font.BOLD, 13));
        checkEspecificacion2.setFont(new Font("Arial", Font.BOLD, 13));
        checkEspecificacion3.setFont(new Font("Arial", Font.BOLD, 13));
        checkEspecificacion4.setFont(new Font("Arial", Font.BOLD, 13));

        checkEspecificacion1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        checkEspecificacion2.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        checkEspecificacion3.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        checkEspecificacion4.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        checkEspecificacion1.setBackground(new Color(155, 209, 229));
        checkEspecificacion2.setBackground(new Color(155, 209, 229));
        checkEspecificacion3.setBackground(new Color(155, 209, 229));
        checkEspecificacion4.setBackground(new Color(155, 209, 229));

        checkEspecificacion1.setAlignmentX(Component.LEFT_ALIGNMENT);
        checkEspecificacion2.setAlignmentX(Component.LEFT_ALIGNMENT);
        checkEspecificacion3.setAlignmentX(Component.LEFT_ALIGNMENT);
        checkEspecificacion4.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelEspecificaciones.add(checkEspecificacion1);
        panelEspecificaciones.add(checkEspecificacion2);
        panelEspecificaciones.add(checkEspecificacion3);
        panelEspecificaciones.add(checkEspecificacion4);

        if(myArchivo.misPreferencias.get(2).equals("S")){ checkEspecificacion1.setSelected(true); }
        if(myArchivo.misPreferencias.get(3).equals("S")){ checkEspecificacion2.setSelected(true); }
        if(myArchivo.misPreferencias.get(4).equals("S")){ checkEspecificacion3.setSelected(true); }
        if(myArchivo.misPreferencias.get(5).equals("S")){ checkEspecificacion4.setSelected(true); }

        // Panel para la entrada de la matrícula y el botón
        JPanel panelCampoMatricula = new JPanel();
        panelCampoMatricula.setBackground(new Color(155, 209, 229));
        panelCampoMatricula.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCampoMatricula.setAlignmentY(Component.CENTER_ALIGNMENT);
        panelCampoMatricula.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        newSize = new Dimension(new Dimension(width-1, (int)(height*0.25)));
        panelCampoMatricula.setMaximumSize(newSize);
        panelCampoMatricula.setPreferredSize(newSize);

        JTextField campoMatricula = new JTextField();

        newSize = new Dimension(new Dimension(105, 24));
        campoMatricula.setMaximumSize(newSize);
        campoMatricula.setPreferredSize(newSize);
        campoMatricula.setText(myArchivo.misPreferencias.get(6));

        JButton btnMatricula = crearBotonConImagen("bin/media/001-matricula.png");

        panelCampoMatricula.add(campoMatricula);
        panelCampoMatricula.add(btnMatricula);

        JPanel panelAceptarCancelar = new JPanel();
        panelAceptarCancelar.setBackground(new Color(155, 209, 229));
        panelAceptarCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAceptarCancelar.setAlignmentY(Component.CENTER_ALIGNMENT);

        newSize = new Dimension(new Dimension(width-1, (int)(height*0.2)));
        panelAceptarCancelar.setMaximumSize(newSize);
        panelAceptarCancelar.setPreferredSize(newSize);

        newSize = new Dimension((int)(width*0.4), 30);
        JButton btnAceptar = new JButton(myArchivo.cadenasIdiomas.get(15));
        btnAceptar.setFont(new Font("Arial", Font.BOLD, 10));
        btnAceptar.setMaximumSize(newSize);
        btnAceptar.setPreferredSize(newSize);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!campoMatricula.getText().isBlank()) {
                    if (!myArchivo.matriculasOcupadas.contains(campoMatricula.getText().trim())) {
                        if(myArchivo.ocuparPlaza(myGrid, myParking, campoMatricula.getText().trim())){ 
                            if (campoMatricula.getText() == "") { myArchivo.misPreferencias.set(6, " "); } 
                            else { myArchivo.misPreferencias.set(6, campoMatricula.getText()); }
                            myArchivo.escribirPreferencias(myArchivo.misPreferencias);

                            JOptionPane.showMessageDialog(subowner, myArchivo.cadenasIdiomas.get(31), myArchivo.cadenasIdiomas.get(36), JOptionPane.INFORMATION_MESSAGE);
                            subowner.dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(subowner,myArchivo.cadenasIdiomas.get(32), myArchivo.cadenasIdiomas.get(35), JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(subowner,myArchivo.cadenasIdiomas.get(34), myArchivo.cadenasIdiomas.get(35), JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(subowner,myArchivo.cadenasIdiomas.get(33), myArchivo.cadenasIdiomas.get(35), JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Crear botón de Cancelar
        JButton btnCancelar = new JButton(myArchivo.cadenasIdiomas.get(17));
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 10));
        btnCancelar.setMaximumSize(newSize);
        btnCancelar.setPreferredSize(newSize);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subowner.dispose();
            }
        });

        panelAceptarCancelar.add(btnAceptar);
        panelAceptarCancelar.add(btnCancelar);

        btnRefrescar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnCoche.isSelected()) { myArchivo.misPreferencias.set(1, "coche"); }
                else if(btnMoto.isSelected()) { myArchivo.misPreferencias.set(1, "moto"); }
                else { myArchivo.misPreferencias.set(1, "default"); }

                if(checkEspecificacion1.isSelected()){ myArchivo.misPreferencias.set(2, "S"); }
                else { myArchivo.misPreferencias.set(2, "N"); }

                if(checkEspecificacion2.isSelected()){ myArchivo.misPreferencias.set(3, "S"); }
                else { myArchivo.misPreferencias.set(3, "N"); }

                if(checkEspecificacion3.isSelected()){ myArchivo.misPreferencias.set(4, "S"); }
                else { myArchivo.misPreferencias.set(4, "N"); }

                if(checkEspecificacion4.isSelected()){ myArchivo.misPreferencias.set(5, "S"); }
                else { myArchivo.misPreferencias.set(5, "N"); }
                
                if (campoMatricula.getText() == "") { myArchivo.misPreferencias.set(6, " "); } 
                else { myArchivo.misPreferencias.set(6, campoMatricula.getText()); }

                myArchivo.escribirPreferencias(myArchivo.misPreferencias);
                subowner.dispose();
                new segundaVista(owner, myParking, myArchivo);
            }
        });

        panelPrincipal.add(panelTextoEIcono);
        panelPrincipal.add(panelBotones);
        panelPrincipal.add(panelEspecificaciones);
        panelPrincipal.add(panelCampoMatricula);
        panelPrincipal.add(panelAceptarCancelar);

        return panelPrincipal;
    }

    private JPanel crearPanelGrid(int width, int height, tipoParking myTipo) {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(209, 250, 255));
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Etiqueta del título del parking
        JLabel textoParking = new JLabel(myArchivo.cadenasIdiomas.get(myTipo.getindiceNombre()), SwingConstants.CENTER);
        textoParking.setFont(new Font("Arial", Font.BOLD, 32));
        textoParking.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel que contiene el grid
        panelGrid = new JPanel();
        panelGrid.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        panelGrid.setBackground(new Color(209, 250, 255));
        panelGrid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear el grid de botones
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                myGridCasilla[i][j] = elegirTipoCasilla(i, j);
                panelGrid.add(myGridCasilla[i][j]);
            }
        }
        myGrid = new grid(myGridCasilla);
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                myGridCasilla[i][j].setGrid(myGrid);
            }
        }

        panelPrincipal.add(textoParking, BorderLayout.NORTH);
        panelPrincipal.add(panelGrid, BorderLayout.CENTER);

        return panelPrincipal;
    }

    private JPanel crearPanelLeyenda(int width, int height) {
        JPanel panelLeyenda = new JPanel();
        panelLeyenda.setLayout(new BoxLayout(panelLeyenda, BoxLayout.Y_AXIS));
        panelLeyenda.setBackground(new Color(209, 250, 255));
        panelLeyenda.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 4));
        panelLeyenda.setAlignmentY(Component.CENTER_ALIGNMENT);
        panelLeyenda.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelLeyenda.add(Box.createVerticalGlue());

        // Añadir título de la leyenda
        JLabel textoLeyeda = new JLabel(myArchivo.cadenasIdiomas.get(24), SwingConstants.CENTER);
        textoLeyeda.setFont(new Font("Arial", Font.BOLD, 40));
        textoLeyeda.setAlignmentX(Component.LEFT_ALIGNMENT);
        textoLeyeda.setHorizontalTextPosition(JLabel.CENTER);
        textoLeyeda.setVerticalTextPosition(JLabel.CENTER);
        panelLeyenda.add(textoLeyeda);

        // Añadir etiquetas con íconos de colores
        panelLeyenda.add(Box.createVerticalStrut(10));
        panelLeyenda.add(crearEtiquetaLeyenda(myArchivo.cadenasIdiomas.get(25), Color.BLACK));
        panelLeyenda.add(Box.createVerticalStrut(10));
        panelLeyenda.add(crearEtiquetaLeyenda(myArchivo.cadenasIdiomas.get(26), Color.GRAY));
        panelLeyenda.add(Box.createVerticalStrut(10));
        panelLeyenda.add(crearEtiquetaLeyenda(myArchivo.cadenasIdiomas.get(27), Color.WHITE));
        panelLeyenda.add(Box.createVerticalStrut(10));
        panelLeyenda.add(crearEtiquetaLeyenda(myArchivo.cadenasIdiomas.get(28), Color.GREEN)); // Reemplaza el color según corresponda

        panelLeyenda.add(Box.createVerticalGlue());

        return panelLeyenda;
    }

    private JPanel crearEtiquetaLeyenda(String texto, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(new Color(209, 250, 255));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    
        // Crear el ícono de color
        Icon icono = crearIconoColor(color);
    
        // Crear la etiqueta con el ícono
        JLabel labelIcono = new JLabel(icono);
        labelIcono.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5)); 
    
        // Crear la etiqueta con el texto
        JLabel labelTexto = new JLabel(texto);
        labelTexto.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelTexto.setFont(new Font("Arial", Font.BOLD, 16));
    
        // Añadir ícono y texto al panel
        panel.add(labelIcono);
        panel.add(labelTexto);
    
        return panel;
    }
    
    private Icon crearIconoColor(Color color) {
        // Crear una imagen de color sólido
        BufferedImage imagen = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagen.createGraphics();
        g2d.setColor(color);
        g2d.fillRect(0, 0, 10, 10);
        g2d.dispose();
    
        // Crear y retornar el ícono a partir de la imagen
        return new ImageIcon(imagen);
    }

    private static JButton crearBotonConImagen(String miIcono) {
        JButton boton = new JButton();
        Image img = new ImageIcon(miIcono).getImage();
        boton.setHorizontalTextPosition(JLabel.CENTER);
        boton.setVerticalTextPosition(JLabel.CENTER);
        boton.setIcon(new ImageIcon(img));
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);

        return boton;
    }

    private gridCasilla elegirTipoCasilla(int i, int j) {
        List<List<String>> myMatriz = myArchivo.leerMatriz(myParking);
        String myDato = myMatriz.get(i).get(j);
        tipoCasilla myTipoCasilla;

        char CocheOMoto = myDato.charAt(0);
        char LibreOOCupado = myDato.charAt(1);
        Set<Character> Especificacion = new HashSet<>();

        for (int k = 2; k < myDato.length(); k++) {
            Especificacion.add(myDato.charAt(k));
        }

        if (LibreOOCupado == 'L') {
            if (cumpleRequisitos(CocheOMoto, Especificacion)) {
                myTipoCasilla = tipoCasilla.LIBRE_CR;
            } else {
                myTipoCasilla = tipoCasilla.LIBRE_SR;
            }
        } else {
            myTipoCasilla = tipoCasilla.OCUPADO;
        }
        
        
        return new gridCasilla(null, myTipoCasilla, myArchivo);
    }

    private boolean cumpleRequisitos(char CocheOMoto, Set<Character> Especificacion) {
        boolean flag = true;
        if((btnCoche.isSelected() && CocheOMoto == 'C') || (btnMoto.isSelected() && CocheOMoto == 'M')) {
            if(checkEspecificacion1.isSelected()) {
                if(!Especificacion.contains('1')) {
                    flag = false;
                }
            }
            if(checkEspecificacion2.isSelected()) {
                if(!Especificacion.contains('2')) {
                    flag = false;
                }
            }
            if(checkEspecificacion3.isSelected()) {
                if(!Especificacion.contains('3')) {
                    flag = false;
                }
            }
            if(checkEspecificacion4.isSelected()) {
                if(!Especificacion.contains('4')) {
                    flag = false;
                }
            }
        }
        else{
            flag = false;
        }
        return flag;
    }

}

