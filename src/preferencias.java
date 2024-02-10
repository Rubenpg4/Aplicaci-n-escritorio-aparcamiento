import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class preferencias extends JDialog {

    private JDialog subowner = this;
    private int width;
    private int height;

    public preferencias(JFrame owner, archivos myArchivo) {
        super(owner, "Parking&CO - " + myArchivo.cadenasIdiomas.get(8), true);

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize((int)(900*0.3), 400);
        this.getContentPane().setLayout(new BorderLayout());

        width = this.getWidth();
        height = this.getHeight();

        Dimension newSize;

        // Panel principal para el filtro
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(155, 209, 229));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Botones de coche y moto
        JToggleButton btnCoche = new JToggleButton(myArchivo.cadenasIdiomas.get(9));
        JToggleButton btnMoto = new JToggleButton(myArchivo.cadenasIdiomas.get(10));
        ButtonGroup grupoBotones = new ButtonGroup();

        newSize = new Dimension((int)(width*0.4), 30);

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
        panelBotones.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
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

        JCheckBox checkEspecificacion1 = new JCheckBox(myArchivo.cadenasIdiomas.get(11));
        JCheckBox checkEspecificacion2 = new JCheckBox(myArchivo.cadenasIdiomas.get(12));
        JCheckBox checkEspecificacion3 = new JCheckBox(myArchivo.cadenasIdiomas.get(13));
        JCheckBox checkEspecificacion4 = new JCheckBox(myArchivo.cadenasIdiomas.get(14));

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

                JOptionPane.showMessageDialog(subowner, myArchivo.cadenasIdiomas.get(16), myArchivo.cadenasIdiomas.get(36), JOptionPane.INFORMATION_MESSAGE);
                subowner.dispose();
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

        panelPrincipal.add(panelBotones);
        panelPrincipal.add(panelEspecificaciones);
        panelPrincipal.add(panelCampoMatricula);
        panelPrincipal.add(panelAceptarCancelar);

        this.getContentPane().add(panelPrincipal);

        this.pack();
        this.setResizable(false);
        this.setVisible(true);
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


}
