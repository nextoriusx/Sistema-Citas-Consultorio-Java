import java.util.ArrayList;
import java.util.List;

public class SistemaCitas {

    private List<Doctor> doctores;
    private List<Paciente> pacientes;
    private List<Cita> citas;

    private Administrador administrador;
    private Persistencia persistencia;

    public SistemaCitas() {

        doctores = new ArrayList<>();
        pacientes = new ArrayList<>();
        citas = new ArrayList<>();

        administrador = new Administrador("admin", "1234");

        persistencia = new RepositorioCSV("datos_citas.csv");
    }

    public void registrarDoctor(Doctor doctor) {
        doctores.add(doctor);
    }

    public void registrarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void crearCita(Cita cita) {
        citas.add(cita);
    }

    public Doctor buscarDoctor(String id) {

        for (Doctor doctor : doctores) {

            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }

        return null;
    }

    public Paciente buscarPaciente(String id) {

        for (Paciente paciente : pacientes) {

            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }

        return null;
    }

    public void consultarInformacion() {

        System.out.println("\n===== DOCTORES =====");

        for (Doctor doctor : doctores) {

            System.out.println(
                    doctor.getId() + " - "
                            + doctor.getNombreCompleto() + " - "
                            + doctor.getEspecialidad()
            );
        }

        System.out.println("\n===== PACIENTES =====");

        for (Paciente paciente : pacientes) {

            System.out.println(
                    paciente.getId() + " - "
                            + paciente.getNombreCompleto()
            );
        }

        System.out.println("\n===== CITAS =====");

        for (Cita cita : citas) {

            System.out.println("ID Cita: " + cita.getId());
            System.out.println("Fecha: " + cita.getFecha());
            System.out.println("Hora: " + cita.getHora());
            System.out.println("Motivo: " + cita.getMotivo());

            System.out.println(
                    "Doctor: "
                            + cita.getDoctor().getNombreCompleto()
            );

            System.out.println(
                    "Paciente: "
                            + cita.getPaciente().getNombreCompleto()
            );

            System.out.println();
        }
    }

    public boolean iniciarSesion(
            String usuario,
            String contrasena) {

        return administrador.autenticar(usuario, contrasena);
    }
}