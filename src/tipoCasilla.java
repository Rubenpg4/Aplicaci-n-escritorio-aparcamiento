import java.awt.*;

public enum tipoCasilla {
    OCUPADO(Color.BLACK),
    LIBRE_SR(Color.GRAY),
    LIBRE_CR(Color.WHITE),
    SELECCIONADO(Color.GREEN);

    private final Color color;

    tipoCasilla(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}