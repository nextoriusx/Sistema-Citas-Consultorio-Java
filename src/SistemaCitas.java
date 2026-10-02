import java.util.ArrayList;
import java.util.List;

public class SistemaCitas {

    private final List<Doctor> doctores;
    private final List<Paciente> pacientes;
    private final List<Cita> citas;

    private final Administrador administrador;
    private final Persistencia persistencia;

    public SistemaCitas() {

        doctores = new ArrayList<>();
        pacientes = new ArrayList<>();
        citas = new ArrayList<>();

        administrador =
                new Administrador(
                        "admin",
                        "1234"
                );

        persistencia =
                new RepositorioCSV("db");

        // Cargar información existente al iniciar
        persistencia.cargarDatos(
                doctores,
                pacientes,
                citas
        );
    }

    // Registrar doctor
    public void registrarDoctor(Doctor doctor) {

        if (doctor == null) {

            System.out.println(
                    "Error: el doctor no puede ser nulo."
            );

            return;
        }

        if (buscarDoctor(doctor.getId()) != null) {

            System.out.println(
                    "El doctor con ID "
                            + doctor.getId()
                            + " ya está registrado."
            );

            return;
        }

        doctores.add(doctor);

        guardarDatos();

        System.out.println(
                "Doctor registrado correctamente."
        );
    }

    // Registrar paciente
    public void registrarPaciente(Paciente paciente) {

        if (paciente == null) {

            System.out.println(
                    "Error: el paciente no puede ser nulo."
            );

            return;
        }

        if (buscarPaciente(paciente.getId()) != null) {

            System.out.println(
                    "El paciente con ID "
                            + paciente.getId()
                            + " ya está registrado."
            );

            return;
        }

        pacientes.add(paciente);

        guardarDatos();

        System.out.println(
                "Paciente registrado correctamente."
        );
    }

    // Crear cita
    public void crearCita(Cita cita) {

        if (cita == null) {

            System.out.println(
                    "Error: la cita no puede ser nula."
            );

            return;
        }

        if (buscarCita(cita.getId()) != null) {

            System.out.println(
                    "La cita con ID "
                            + cita.getId()
                            + " ya está registrada."
            );

            return;
        }

        if (cita.getDoctor() == null) {

            System.out.println(
                    "Error: la cita debe tener un doctor."
            );

            return;
        }

        if (cita.getPaciente() == null) {

            System.out.println(
                    "Error: la cita debe tener un paciente."
            );

            return;
        }

        citas.add(cita);

        guardarDatos();

        System.out.println(
                "Cita registrada correctamente."
        );
    }

    // Buscar doctor por ID
    public Doctor buscarDoctor(String id) {

        for (Doctor doctor : doctores) {

            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }

        return null;
    }

    // Buscar paciente por ID
    public Paciente buscarPaciente(String id) {

        for (Paciente paciente : pacientes) {

            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }

        return null;
    }

    // Buscar cita por ID
    public Cita buscarCita(String id) {

        for (Cita cita : citas) {

            if (cita.getId().equals(id)) {
                return cita;
            }
        }

        return null;
    }

    // Mostrar toda la información registrada
    public void consultarInformacion() {

        System.out.println();
        System.out.println(
                "===== DOCTORES ====="
        );

        if (doctores.isEmpty()) {

            System.out.println(
                    "No hay doctores registrados."
            );

        } else {

            for (Doctor doctor : doctores) {

                System.out.println(
                        doctor.getId()
                                + " - "
                                + doctor.getNombreCompleto()
                                + " - "
                                + doctor.getEspecialidad()
                );
            }
        }

        System.out.println();
        System.out.println(
                "===== PACIENTES ====="
        );

        if (pacientes.isEmpty()) {

            System.out.println(
                    "No hay pacientes registrados."
            );

        } else {

            for (Paciente paciente : pacientes) {

                System.out.println(
                        paciente.getId()
                                + " - "
                                + paciente.getNombreCompleto()
                );
            }
        }

        System.out.println();
        System.out.println(
                "===== CITAS ====="
        );

        if (citas.isEmpty()) {

            System.out.println(
                    "No hay citas registradas."
            );

        } else {

            for (Cita cita : citas) {

                System.out.println(
                        "ID Cita: "
                                + cita.getId()
                );

                System.out.println(
                        "Fecha: "
                                + cita.getFecha()
                );

                System.out.println(
                        "Hora: "
                                + cita.getHora()
                );

                System.out.println(
                        "Motivo: "
                                + cita.getMotivo()
                );

                System.out.println(
                        "Doctor: "
                                + cita.getDoctor()
                                .getNombreCompleto()
                );

                System.out.println(
                        "Especialidad: "
                                + cita.getDoctor()
                                .getEspecialidad()
                );

                System.out.println(
                        "Paciente: "
                                + cita.getPaciente()
                                .getNombreCompleto()
                );

                System.out.println();
            }
        }
    }

    // Guardar toda la información
    public void guardarDatos() {

        persistencia.guardarDatos(
                doctores,
                pacientes,
                citas
        );
    }

    // Recargar información desde los CSV
    public void cargarDatos() {

        persistencia.cargarDatos(
                doctores,
                pacientes,
                citas
        );
    }

    // Inicio de sesión del administrador
    public boolean iniciarSesion(
            String usuario,
            String contrasena) {

        return administrador.autenticar(
                usuario,
                contrasena
        );
    }

    // Métodos auxiliares
    public int getNumeroDoctores() {
        return doctores.size();
    }

    public int getNumeroPacientes() {
        return pacientes.size();
    }

    public int getNumeroCitas() {
        return citas.size();
    }
}