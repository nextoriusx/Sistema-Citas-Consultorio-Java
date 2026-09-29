public class Main {

    public static void main(String[] args) {

        SistemaCitas sistema = new SistemaCitas();

        System.out.println("===== SISTEMA DE CITAS - CONSULTORIO CLINICO =====");

        // Inicio de sesión
        if (sistema.iniciarSesion("admin", "1234")) {

            System.out.println("Acceso autorizado.");

            // Crear y registrar doctor
            Doctor doctor = new Doctor(
                    "DOC001",
                    "Laura Hernandez",
                    "Medicina General"
            );

            sistema.registrarDoctor(doctor);

            // Crear y registrar paciente
            Paciente paciente = new Paciente(
                    "PAC001",
                    "Carlos Ramirez"
            );

            sistema.registrarPaciente(paciente);

            // Crear cita
            Cita cita = new Cita(
                    "CIT001",
                    "30/09/2026",
                    "10:30",
                    "Consulta general",
                    doctor,
                    paciente
            );

            sistema.crearCita(cita);

            // Mostrar información registrada
            sistema.consultarInformacion();

        } else {

            System.out.println("Error: credenciales incorrectas.");
        }

        System.out.println("Sistema finalizado correctamente.");
    }
}