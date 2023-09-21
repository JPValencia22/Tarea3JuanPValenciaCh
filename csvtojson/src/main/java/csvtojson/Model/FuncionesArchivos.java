package csvtojson.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FuncionesArchivos {
    
    // Función para abrir un cuadro de diálogo para seleccionar el archivo CSV
    public static String seleccionarArchivoCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione un archivo CSV");
        int seleccion = fileChooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            return archivoSeleccionado.getAbsolutePath();
        }
        return null;
    }

    // Función para leer estudiantes desde un archivo CSV
    public static List<Estudiante> leerEstudiantesDesdeCSV(String archivoCSV) {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                Estudiante estudiante = new Estudiante(Integer.parseInt(datos[0]), datos[1], datos[2]);
                estudiantes.add(estudiante);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }

    // Función para escribir estudiantes en un archivo JSON
    public static void escribirEstudiantesEnJSON(List<Estudiante> estudiantes, String archivoJSON) {
        try (FileWriter fw = new FileWriter(archivoJSON)) {
            fw.write("[");
            for (int i = 0; i < estudiantes.size(); i++) {
                Estudiante estudiante = estudiantes.get(i);
                fw.write(estudiante.toJson());
                if (i < estudiantes.size() - 1) {
                    fw.write(",");
                }
            }
            fw.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Función para mostrar un cuadro de diálogo para seleccionar el archivo
    public static int mostrarDialogoSeleccionArchivo() {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int respuesta = JOptionPane.showOptionDialog(
                null,
                "Por favor, seleccione un archivo CSV",
                "Selección de Archivo",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        return respuesta;
    }

    // Función para verificar si la ruta del archivo es correcta
    public static boolean verificarRutaArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        return archivo.exists() && archivo.isFile();
    }
}
