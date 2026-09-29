public class RepositorioCSV implements Persistencia {

    private String rutaArchivo;

    public RepositorioCSV(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void guardarDatos() {
        System.out.println("Guardando datos en: " + rutaArchivo);
    }

    @Override
    public void cargarDatos() {
        System.out.println("Cargando datos desde: " + rutaArchivo);
    }
}