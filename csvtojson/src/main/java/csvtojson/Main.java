package csvtojson;

import java.util.List;

import javax.swing.JOptionPane;

import csvtojson.Model.Estudiante;
import csvtojson.Model.FuncionesArchivos;

public class Main {
    public static void main(String[] args) {
        int respuesta = FuncionesArchivos.mostrarDialogoSeleccionArchivo();
        if (respuesta == JOptionPane.OK_OPTION) {
            String rutaArchivoCSV = FuncionesArchivos.seleccionarArchivoCSV();
            if (rutaArchivoCSV != null) {
                if (FuncionesArchivos.verificarRutaArchivo(rutaArchivoCSV)) {
                    JOptionPane.showMessageDialog(null, "La ruta del archivo es correcta");
                    List<Estudiante> estudiantes = FuncionesArchivos.leerEstudiantesDesdeCSV(rutaArchivoCSV);
                    FuncionesArchivos.escribirEstudiantesEnJSON(estudiantes, "estudiantes.json");
                    JOptionPane.showMessageDialog(null, "Se ha creado el archivo estudiantes.json");                    
                } else {
                    JOptionPane.showMessageDialog(null, "Error: El archivo no existe o no se puede acceder");
                }
            }
        }
    }
}