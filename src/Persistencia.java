import java.util.List;

public interface Persistencia {

    void guardarDatos(
            List<Doctor> doctores,
            List<Paciente> pacientes,
            List<Cita> citas
    );

    void cargarDatos(
            List<Doctor> doctores,
            List<Paciente> pacientes,
            List<Cita> citas
    );
}