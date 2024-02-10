import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class idiomas extends JDialog {

    private JDialog subowner = this;
    private String idiomaSeleccionado;

    public idiomas(JFrame owner, archivos myArchivo) {
        super(owner, "Parking&CO - " + myArchivo.cadenasIdiomas.get(18), true);

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize((int)(900*0.25), 250);
        this.getContentPane().setLayout(new BorderLayout());

        int width = this.getWidth();
        int height = this.getHeight();

        Dimension newSize;
        
        idiomaSeleccionado = myArchivo.idiomaSeleccionado;

        myArchivo.leerLenguaje();

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(155, 209, 229));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.setAlignmentY(Component.CENTER_ALIGNMENT);

        JPanel panelBotonesIdiomas = new JPanel();
        panelBotonesIdiomas.setBackground(new Color(155, 209, 229));
        ButtonGroup grupoIdiomas = new ButtonGroup();

        for (String idioma : myArchivo.tipoIdiomas) {
            JRadioButton botonIdioma = new JRadioButton(idioma);
            botonIdioma.setBackground(new Color(155, 209, 229));
            if(idioma.equals(myArchivo.idiomaSeleccionado)){
                botonIdioma.setSelected(true);
            }
            botonIdioma.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    idiomaSeleccionado = idioma;
                }
            });

            grupoIdiomas.add(botonIdioma);
            panelBotonesIdiomas.add(botonIdioma);
        }

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
                JOptionPane.showMessageDialog(subowner,myArchivo.cadenasIdiomas.get(19), myArchivo.cadenasIdiomas.get(36), JOptionPane.INFORMATION_MESSAGE);
                myArchivo.misPreferencias.set(0, idiomaSeleccionado);
                myArchivo.escribirPreferencias(myArchivo.misPreferencias);
                myArchivo.leerLenguaje();
                subowner.dispose();
                owner.dispose();
                new primeraVista();
            }
        });

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

        panelPrincipal.add(panelBotonesIdiomas);
        panelPrincipal.add(panelAceptarCancelar);

        this.getContentPane().add(panelPrincipal);

        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }
}