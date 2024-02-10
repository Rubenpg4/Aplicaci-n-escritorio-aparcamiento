public enum tipoParking {
    HOSPITAL(3, "bin/data/matriz/matriz-Hospital", "bin/media/002-hospital.png"),
    CENTRO_COMERCIAL(4, "bin/data/matriz/matriz-C_Comercial", "bin/media/001-centro-comercial.png"),
    PARQUE(5, "bin/data/matriz/matriz-Parque", "bin/media/003-parque.png"),
    MUSEO(6, "bin/data/matriz/matriz-Museo", "bin/media/004-museo-britanico.png");

    private final int indiceNombre;
    private final String matriz;
    private final String logo;

    tipoParking(int indiceNombre, String matriz, String logo) {
        this.indiceNombre = indiceNombre;
        this.matriz = matriz;
        this.logo = logo;
    }

    public int getindiceNombre() {
        return indiceNombre;
    }

    public String getMatriz() {
        return matriz;
    }

    public String getLogo() {
        return logo;
    }
}
