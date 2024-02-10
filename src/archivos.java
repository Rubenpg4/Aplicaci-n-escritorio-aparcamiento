import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class archivos {
    public List<List<String>> matrizDatos;
    public Vector<String> cadenasIdiomas;
    public Vector<String> tipoIdiomas;
    public Vector<String> misPreferencias;
    public Vector<String> matriculasOcupadas;
    public String idiomaSeleccionado;

    public archivos() {
        leerLenguaje();
    }
    
    public Vector<String> leerLenguaje() {
        leerPreferencias();
        cadenasIdiomas = new Vector<>();
        tipoIdiomas = new Vector<>();
        String idiomaActual;
        String cadenaActual;

        try (BufferedReader br = new BufferedReader(new FileReader("bin/data/language"))) {
            int numeroDeIdiomas = Integer.parseInt(br.readLine().trim());

            for (int i = 0; i < numeroDeIdiomas; i++) {
                tipoIdiomas.add(br.readLine());
                idiomaActual = tipoIdiomas.get(i);
                int numeroDeCadenas = Integer.parseInt(br.readLine().trim());

                for (int j = 0; j < numeroDeCadenas; j++) {
                    cadenaActual = br.readLine();
                    if(idiomaActual.equals(idiomaSeleccionado)) {
                        cadenasIdiomas.add(cadenaActual);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cadenasIdiomas;
    }

    public Vector<String> leerPreferencias() {
        misPreferencias = new Vector<>();
        try (BufferedReader br = new BufferedReader(new FileReader("bin/data/preferences"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                for (String parte : partes) {
                    misPreferencias.add(parte);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        idiomaSeleccionado = misPreferencias.get(0);
        if(idiomaSeleccionado == null){
            idiomaSeleccionado = "es";
        }

        return misPreferencias;
    }

    public void escribirPreferencias(Vector<String> nuevosDatos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("bin/data/preferences"))) {
            String linea = String.join(";", nuevosDatos);
            pw.println(linea);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<List<String>> leerMatriz(tipoParking myParking) {
        matrizDatos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(myParking.getMatriz()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                List<String> filaDatos = new ArrayList<>();
                for (String dato : datos) {
                    filaDatos.add(dato);
                }
                matrizDatos.add(filaDatos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        matriculasOcupadas = new Vector<>();
        try (BufferedReader br = new BufferedReader(new FileReader("bin/data/matriculas"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                for (String parte : partes) {
                    if(!matriculasOcupadas.contains(parte.trim())){
                        matriculasOcupadas.add(parte);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matrizDatos;
    }

    public boolean ocuparPlaza(grid myGrid, tipoParking myParking, String matricula) {
        List<List<String>> matrizDatos = leerMatriz(myParking);
        boolean haySeleccionada = false;
    
        for (int i = 0; i < myGrid.getGridCasillas().length; i++) {
            for (int j = 0; j < myGrid.getGridCasillas()[i].length; j++) {
                gridCasilla casilla = myGrid.getGridCasillas()[i][j];
                if (casilla.getTipo() == tipoCasilla.SELECCIONADO) {
                    haySeleccionada = true;
                    String datoActual = matrizDatos.get(i).get(j);
                    String datoModificado = datoActual.substring(0, 1) + "O" + datoActual.substring(2);
                    matrizDatos.get(i).set(j, datoModificado);
                    matriculasOcupadas.add(matricula);
                    break;
                }
            }
            if (haySeleccionada) {
                break;
            }
        }
    
        if (haySeleccionada) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(myParking.getMatriz()))) {
                for (List<String> fila : matrizDatos) {
                    String linea = String.join(";", fila);
                    pw.println(linea);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (PrintWriter pw = new PrintWriter(new FileWriter("bin/data/matriculas"))) {
                String linea = String.join(";", matriculasOcupadas);
                pw.println(linea);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return haySeleccionada;
    }
}
