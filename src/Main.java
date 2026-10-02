import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

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

    // =========================================================
    // ENCABEZADO
    // =========================================================

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

    // =========================================================
    // INICIO DE SESIÓN
    // =========================================================

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

    // =========================================================
    // MENÚ PRINCIPAL
    // =========================================================

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

    // =========================================================
    // ALTA DE DOCTOR
    // =========================================================

    private static void registrarDoctor(
            SistemaCitas sistema) {

        System.out.println();
        System.out.println(
                "===== ALTA DE DOCTOR ====="
        );

        String id =
                leerIdValido(
                        "ID del doctor: ",
                        "DOC"
                );

        if (sistema.buscarDoctor(id) != null) {

            System.out.println();
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

    // =========================================================
    // ALTA DE PACIENTE
    // =========================================================

    private static void registrarPaciente(
            SistemaCitas sistema) {

        System.out.println();
        System.out.println(
                "===== ALTA DE PACIENTE ====="
        );

        String id =
                leerIdValido(
                        "ID del paciente: ",
                        "PAC"
                );

        if (sistema.buscarPaciente(id) != null) {

            System.out.println();
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

    // =========================================================
    // CREAR CITA
    // =========================================================

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
                leerIdValido(
                        "ID de la cita: ",
                        "CIT"
                );

        if (sistema.buscarCita(idCita) != null) {

            System.out.println();
            System.out.println(
                    "Error: ya existe una cita "
                            + "con el ID "
                            + idCita
                            + "."
            );

            return;
        }

        String fecha =
                leerFechaValida();

        String hora =
                leerHoraValida();

        String motivo =
                leerTextoObligatorio(
                        "Motivo de la cita: "
                );

        String idDoctor =
                leerIdValido(
                        "ID del doctor: ",
                        "DOC"
                );

        Doctor doctor =
                sistema.buscarDoctor(idDoctor);

        if (doctor == null) {

            System.out.println();
            System.out.println(
                    "Error: no existe un doctor "
                            + "con el ID "
                            + idDoctor
                            + "."
            );

            return;
        }

        String idPaciente =
                leerIdValido(
                        "ID del paciente: ",
                        "PAC"
                );

        Paciente paciente =
                sistema.buscarPaciente(
                        idPaciente
                );

        if (paciente == null) {

            System.out.println();
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

    // =========================================================
    // VALIDACIÓN DE TEXTO
    // =========================================================

    private static String leerTextoObligatorio(
            String mensaje) {

        String valor;

        do {

            System.out.print(mensaje);

            valor =
                    scanner.nextLine().trim();

            if (valor.isEmpty()) {

                System.out.println(
                        "Error: el campo no puede quedar vacío."
                );
            }

        } while (valor.isEmpty());

        return valor;
    }

    // =========================================================
    // VALIDACIÓN DE IDENTIFICADORES
    // =========================================================

    private static String leerIdValido(
            String mensaje,
            String prefijo) {

        while (true) {

            String id =
                    leerTextoObligatorio(mensaje)
                            .toUpperCase();

            if (id.matches(
                    prefijo + "\\d{3}"
            )) {

                return id;
            }

            System.out.println();
            System.out.println(
                    "Error: el identificador debe tener "
                            + "el formato "
                            + prefijo
                            + " seguido de tres números."
            );

            System.out.println(
                    "Ejemplo válido: "
                            + prefijo
                            + "001"
            );

            System.out.println();
        }
    }

    // =========================================================
    // VALIDACIÓN DE FECHA
    // =========================================================

    private static String leerFechaValida() {

        DateTimeFormatter formato =
                DateTimeFormatter
                        .ofPattern(
                                "dd/MM/uuuu"
                        )
                        .withResolverStyle(
                                ResolverStyle.STRICT
                        );

        while (true) {

            String fecha =
                    leerTextoObligatorio(
                            "Fecha (DD/MM/AAAA): "
                    );

            try {

                LocalDate.parse(
                        fecha,
                        formato
                );

                return fecha;

            } catch (DateTimeParseException e) {

                System.out.println();
                System.out.println(
                        "Error: la fecha ingresada "
                                + "no es válida."
                );

                System.out.println(
                        "Use el formato DD/MM/AAAA."
                );

                System.out.println(
                        "Ejemplo válido: 15/10/2026"
                );

                System.out.println();
            }
        }
    }

    // =========================================================
    // VALIDACIÓN DE HORA
    // =========================================================

    private static String leerHoraValida() {

        DateTimeFormatter formato =
                DateTimeFormatter
                        .ofPattern(
                                "HH:mm"
                        )
                        .withResolverStyle(
                                ResolverStyle.STRICT
                        );

        while (true) {

            String hora =
                    leerTextoObligatorio(
                            "Hora (HH:MM): "
                    );

            try {

                LocalTime.parse(
                        hora,
                        formato
                );

                return hora;

            } catch (DateTimeParseException e) {

                System.out.println();
                System.out.println(
                        "Error: la hora ingresada "
                                + "no es válida."
                );

                System.out.println(
                        "Use el formato HH:MM de 24 horas."
                );

                System.out.println(
                        "Ejemplo válido: 14:30"
                );

                System.out.println();
            }
        }
    }
}