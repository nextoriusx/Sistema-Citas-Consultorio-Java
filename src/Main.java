import java.util.Scanner;

public class Main {

    private static final Scanner scanner =
            new Scanner(System.in);

    public static void main(String[] args) {

        SistemaCitas sistema =
                new SistemaCitas();

        mostrarEncabezado();

        iniciarSesion(sistema);

        int opcion;

        do {

            mostrarMenu();

            opcion = leerOpcion();

            try {

                switch (opcion) {

                    case 1:
                        registrarDoctor(sistema);
                        break;

                    case 2:
                        registrarPaciente(sistema);
                        break;

                    case 3:
                        crearCita(sistema);
                        break;

                    case 4:
                        sistema.consultarInformacion();
                        break;

                    case 5:

                        sistema.guardarDatos();

                        System.out.println();
                        System.out.println(
                                "Información guardada correctamente."
                        );

                        System.out.println(
                                "Sistema finalizado correctamente."
                        );

                        break;

                    default:

                        System.out.println();
                        System.out.println(
                                "Opción no válida. "
                                        + "Seleccione una opción del 1 al 5."
                        );
                }

            } catch (Exception e) {

                System.out.println();
                System.out.println(
                        "Ocurrió un error: "
                                + e.getMessage()
                );

                System.out.println(
                        "El sistema continuará en ejecución."
                );
            }

        } while (opcion != 5);

        scanner.close();
    }

    private static void mostrarEncabezado() {

        System.out.println(
                "========================================"
        );

        System.out.println(
                "   SISTEMA DE CITAS - CONSULTORIO"
        );

        System.out.println(
                "========================================"
        );

        System.out.println();
    }

    private static void iniciarSesion(
            SistemaCitas sistema) {

        boolean accesoAutorizado = false;

        while (!accesoAutorizado) {

            System.out.println(
                    "===== INICIO DE SESIÓN ====="
            );

            System.out.print(
                    "Usuario: "
            );

            String usuario =
                    scanner.nextLine().trim();

            System.out.print(
                    "Contraseña: "
            );

            String contrasena =
                    scanner.nextLine().trim();

            accesoAutorizado =
                    sistema.iniciarSesion(
                            usuario,
                            contrasena
                    );

            if (accesoAutorizado) {

                System.out.println();
                System.out.println(
                        "Acceso autorizado."
                );

            } else {

                System.out.println();
                System.out.println(
                        "Error: credenciales incorrectas."
                );

                System.out.println(
                        "Intente nuevamente."
                );

                System.out.println();
            }
        }
    }

    private static void mostrarMenu() {

        System.out.println();
        System.out.println(
                "========== MENÚ PRINCIPAL =========="
        );

        System.out.println(
                "1. Dar de alta doctor"
        );

        System.out.println(
                "2. Dar de alta paciente"
        );

        System.out.println(
                "3. Crear cita"
        );

        System.out.println(
                "4. Consultar información"
        );

        System.out.println(
                "5. Salir"
        );

        System.out.println(
                "===================================="
        );

        System.out.print(
                "Seleccione una opción: "
        );
    }

    private static int leerOpcion() {

        try {

            return Integer.parseInt(
                    scanner.nextLine().trim()
            );

        } catch (NumberFormatException e) {

            return -1;
        }
    }

    private static void registrarDoctor(
            SistemaCitas sistema) {

        System.out.println();
        System.out.println(
                "===== ALTA DE DOCTOR ====="
        );

        String id =
                leerTextoObligatorio(
                        "ID del doctor: "
                );

        if (sistema.buscarDoctor(id) != null) {

            System.out.println(
                    "Error: ya existe un doctor "
                            + "con el ID "
                            + id
                            + "."
            );

            return;
        }

        String nombre =
                leerTextoObligatorio(
                        "Nombre completo: "
                );

        String especialidad =
                leerTextoObligatorio(
                        "Especialidad: "
                );

        Doctor doctor =
                new Doctor(
                        id,
                        nombre,
                        especialidad
                );

        sistema.registrarDoctor(doctor);
    }

    private static void registrarPaciente(
            SistemaCitas sistema) {

        System.out.println();
        System.out.println(
                "===== ALTA DE PACIENTE ====="
        );

        String id =
                leerTextoObligatorio(
                        "ID del paciente: "
                );

        if (sistema.buscarPaciente(id) != null) {

            System.out.println(
                    "Error: ya existe un paciente "
                            + "con el ID "
                            + id
                            + "."
            );

            return;
        }

        String nombre =
                leerTextoObligatorio(
                        "Nombre completo: "
                );

        Paciente paciente =
                new Paciente(
                        id,
                        nombre
                );

        sistema.registrarPaciente(paciente);
    }

    private static void crearCita(
            SistemaCitas sistema) {

        System.out.println();
        System.out.println(
                "===== CREAR CITA ====="
        );

        if (sistema.getNumeroDoctores() == 0) {

            System.out.println(
                    "No hay doctores registrados."
            );

            System.out.println(
                    "Primero debe dar de alta un doctor."
            );

            return;
        }

        if (sistema.getNumeroPacientes() == 0) {

            System.out.println(
                    "No hay pacientes registrados."
            );

            System.out.println(
                    "Primero debe dar de alta un paciente."
            );

            return;
        }

        String idCita =
                leerTextoObligatorio(
                        "ID de la cita: "
                );

        if (sistema.buscarCita(idCita) != null) {

            System.out.println(
                    "Error: ya existe una cita "
                            + "con el ID "
                            + idCita
                            + "."
            );

            return;
        }

        String fecha =
                leerTextoObligatorio(
                        "Fecha (DD/MM/AAAA): "
                );

        String hora =
                leerTextoObligatorio(
                        "Hora (HH:MM): "
                );

        String motivo =
                leerTextoObligatorio(
                        "Motivo de la cita: "
                );

        String idDoctor =
                leerTextoObligatorio(
                        "ID del doctor: "
                );

        Doctor doctor =
                sistema.buscarDoctor(idDoctor);

        if (doctor == null) {

            System.out.println(
                    "Error: no existe un doctor "
                            + "con el ID "
                            + idDoctor
                            + "."
            );

            return;
        }

        String idPaciente =
                leerTextoObligatorio(
                        "ID del paciente: "
                );

        Paciente paciente =
                sistema.buscarPaciente(
                        idPaciente
                );

        if (paciente == null) {

            System.out.println(
                    "Error: no existe un paciente "
                            + "con el ID "
                            + idPaciente
                            + "."
            );

            return;
        }

        Cita cita =
                new Cita(
                        idCita,
                        fecha,
                        hora,
                        motivo,
                        doctor,
                        paciente
                );

        sistema.crearCita(cita);

        System.out.println();
        System.out.println(
                "Doctor asignado: "
                        + doctor.getNombreCompleto()
        );

        System.out.println(
                "Paciente asignado: "
                        + paciente.getNombreCompleto()
        );
    }

    private static String leerTextoObligatorio(
            String mensaje) {

        String valor;

        do {

            System.out.print(mensaje);

            valor =
                    scanner.nextLine().trim();

            if (valor.isEmpty()) {

                System.out.println(
                        "El campo no puede quedar vacío."
                );
            }

        } while (valor.isEmpty());

        return valor;
    }
}