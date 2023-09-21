package csvtojson.Model;

public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;

    public Estudiante(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public String toJson() {
        return "{ \"id\": " + id + ", \"nombre\": \"" + nombre + "\", \"apellido\": \"" + apellido + "\" }";
    }
}