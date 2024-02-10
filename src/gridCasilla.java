import javax.swing.*;

public class gridCasilla extends JButton {
    archivos myArchivo;
    private grid grid;
    private tipoCasilla tipo;
    private tipoCasilla tipoPrevio;

    public gridCasilla(grid grid, tipoCasilla tipoInicial, archivos myArchivo) {
        super();
        this.grid = grid;
        this.tipo = tipoInicial;
        this.tipoPrevio = tipoInicial;
        this.myArchivo = myArchivo;
        configurarBoton();
    }

    private void configurarBoton() {
        this.setBackground(tipo.getColor());
        this.addActionListener(e -> manejarClic());
    }

    private void manejarClic() {
        switch (tipo) {
            case OCUPADO:
                JOptionPane.showMessageDialog(this, myArchivo.cadenasIdiomas.get(29), myArchivo.cadenasIdiomas.get(35), JOptionPane.ERROR_MESSAGE);
                break;
            case LIBRE_SR:
                int confirm = JOptionPane.showConfirmDialog(this, myArchivo.cadenasIdiomas.get(30), myArchivo.cadenasIdiomas.get(37), JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    cambiarTipo(tipoCasilla.SELECCIONADO);
                }
                break;
            case LIBRE_CR:
                cambiarTipo(tipoCasilla.SELECCIONADO);
                break;
            case SELECCIONADO:
                cambiarTipo(tipoPrevio);
                break;
        }
    }

    public void cambiarTipo(tipoCasilla nuevoTipo) {
        if (nuevoTipo == tipoCasilla.SELECCIONADO && this.tipo != tipoCasilla.SELECCIONADO) {
            grid.cambiarSeleccion(this);
        }
        if (this.tipo != tipoCasilla.SELECCIONADO && nuevoTipo == tipoCasilla.SELECCIONADO) {
            tipoPrevio = this.tipo;
        }
        this.tipo = nuevoTipo;
        configurarBoton();
    }

    public tipoCasilla getTipoPrevio() {
        return tipoPrevio;
    }

    public tipoCasilla getTipo() {
        return tipo;
    }

    public void setGrid(grid nuevoGrid) {
        this.grid = nuevoGrid;
    }
}

