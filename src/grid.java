public class grid {
    private gridCasilla[][] gridCasillas;
    private gridCasilla botonSeleccionadoActual;

    public grid(gridCasilla[][] copiaGridCasillas) {
        this.gridCasillas = copiaGridCasillas; // Usar la matriz de casillas proporcionada
        botonSeleccionadoActual = null;
        configurarCasillas(); // Configurar casillas con la referencia a este grid
    }

    private void configurarCasillas() {
        for (int i = 0; i < gridCasillas.length; i++) {
            for (int j = 0; j < gridCasillas[i].length; j++) {
                gridCasillas[i][j].setGrid(this); // Establecer la referencia a este grid
            }
        }
    }

    public void cambiarSeleccion(gridCasilla nuevaSeleccion) {
        if (botonSeleccionadoActual != null && botonSeleccionadoActual != nuevaSeleccion) {
            botonSeleccionadoActual.cambiarTipo(botonSeleccionadoActual.getTipoPrevio());
        }
        botonSeleccionadoActual = nuevaSeleccion;
    }

    public gridCasilla[][] getGridCasillas() {
        return gridCasillas;
    }
}




