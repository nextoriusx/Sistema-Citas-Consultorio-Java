import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RepositorioCSV implements Persistencia {

    private final Path directorioDb;
    private final Path archivoDoctores;
    private final Path archivoPacientes;
    private final Path archivoCitas;

    public RepositorioCSV(String rutaDirectorio) {

        directorioDb = Paths.get(rutaDirectorio);

        archivoDoctores =
                directorioDb.resolve("doctores.csv");

        archivoPacientes =
                directorioDb.resolve("pacientes.csv");

        archivoCitas =
                directorioDb.resolve("citas.csv");
    }

    /*
     * Verifica que exista la carpeta db y los tres
     * archivos CSV necesarios para el sistema.
     */
    private void asegurarArchivos() {

        try {

            if (!Files.exists(directorioDb)) {

                Files.createDirectories(directorioDb);

                System.out.println(
                        "Carpeta de datos creada: "
                                + directorioDb
                );
            }

            crearArchivoSiNoExiste(archivoDoctores);
            crearArchivoSiNoExiste(archivoPacientes);
            crearArchivoSiNoExiste(archivoCitas);

        } catch (IOException e) {

            System.out.println(
                    "Error al preparar los archivos de datos: "
                            + e.getMessage()
            );
        }
    }

    /*
     * Crea un archivo vacío cuando todavía no existe.
     */
    private void crearArchivoSiNoExiste(Path archivo)
            throws IOException {

        if (!Files.exists(archivo)) {

            Files.createFile(archivo);

            System.out.println(
                    "Archivo creado: "
                            + archivo
            );
        }
    }

    /*
     * Guarda toda la información del sistema.
     */
    @Override
    public void guardarDatos(
            List<Doctor> doctores,
            List<Paciente> pacientes,
            List<Cita> citas) {

        asegurarArchivos();

        guardarDoctores(doctores);
        guardarPacientes(pacientes);
        guardarCitas(citas);
    }

    /*
     * Carga toda la información almacenada.
     */
    @Override
    public void cargarDatos(
            List<Doctor> doctores,
            List<Paciente> pacientes,
            List<Cita> citas) {

        asegurarArchivos();

        doctores.clear();
        pacientes.clear();
        citas.clear();

        /*
         * Es importante cargar primero doctores y pacientes,
         * porque las citas dependen de ellos.
         */
        cargarDoctores(doctores);
        cargarPacientes(pacientes);

        cargarCitas(
                citas,
                doctores,
                pacientes
        );

        System.out.println();
        System.out.println(
                "Información cargada correctamente."
        );

        System.out.println(
                "Doctores: " + doctores.size()
        );

        System.out.println(
                "Pacientes: " + pacientes.size()
        );

        System.out.println(
                "Citas: " + citas.size()
        );

        System.out.println();
    }

    /*
     * Guarda doctores.csv
     *
     * Formato:
     * ID,Nombre,Especialidad
     */
    private void guardarDoctores(
            List<Doctor> doctores) {

        try (BufferedWriter escritor =
                     Files.newBufferedWriter(
                             archivoDoctores,
                             StandardCharsets.UTF_8)) {

            for (Doctor doctor : doctores) {

                escritor.write(
                        limpiarCampo(doctor.getId())
                                + ","
                                + limpiarCampo(
                                doctor.getNombreCompleto())
                                + ","
                                + limpiarCampo(
                                doctor.getEspecialidad())
                );

                escritor.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar doctores: "
                            + e.getMessage()
            );
        }
    }

    /*
     * Guarda pacientes.csv
     *
     * Formato:
     * ID,Nombre
     */
    private void guardarPacientes(
            List<Paciente> pacientes) {

        try (BufferedWriter escritor =
                     Files.newBufferedWriter(
                             archivoPacientes,
                             StandardCharsets.UTF_8)) {

            for (Paciente paciente : pacientes) {

                escritor.write(
                        limpiarCampo(paciente.getId())
                                + ","
                                + limpiarCampo(
                                paciente.getNombreCompleto())
                );

                escritor.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar pacientes: "
                            + e.getMessage()
            );
        }
    }

    /*
     * Guarda citas.csv
     *
     * Formato:
     * ID,Fecha,Hora,Motivo,IdDoctor,IdPaciente
     */
    private void guardarCitas(
            List<Cita> citas) {

        try (BufferedWriter escritor =
                     Files.newBufferedWriter(
                             archivoCitas,
                             StandardCharsets.UTF_8)) {

            for (Cita cita : citas) {

                escritor.write(
                        limpiarCampo(cita.getId())
                                + ","
                                + limpiarCampo(cita.getFecha())
                                + ","
                                + limpiarCampo(cita.getHora())
                                + ","
                                + limpiarCampo(cita.getMotivo())
                                + ","
                                + limpiarCampo(
                                cita.getDoctor().getId())
                                + ","
                                + limpiarCampo(
                                cita.getPaciente().getId())
                );

                escritor.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar citas: "
                            + e.getMessage()
            );
        }
    }

    /*
     * Lee doctores.csv y reconstruye
     * los objetos Doctor.
     */
    private void cargarDoctores(
            List<Doctor> doctores) {

        try (BufferedReader lector =
                     Files.newBufferedReader(
                             archivoDoctores,
                             StandardCharsets.UTF_8)) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos =
                        linea.split(",", -1);

                if (datos.length == 3) {

                    Doctor doctor =
                            new Doctor(
                                    datos[0].trim(),
                                    datos[1].trim(),
                                    datos[2].trim()
                            );

                    doctores.add(doctor);

                } else {

                    System.out.println(
                            "Registro de doctor inválido: "
                                    + linea
                    );
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al cargar doctores: "
                            + e.getMessage()
            );
        }
    }

    /*
     * Lee pacientes.csv y reconstruye
     * los objetos Paciente.
     */
    private void cargarPacientes(
            List<Paciente> pacientes) {

        try (BufferedReader lector =
                     Files.newBufferedReader(
                             archivoPacientes,
                             StandardCharsets.UTF_8)) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos =
                        linea.split(",", -1);

                if (datos.length == 2) {

                    Paciente paciente =
                            new Paciente(
                                    datos[0].trim(),
                                    datos[1].trim()
                            );

                    pacientes.add(paciente);

                } else {

                    System.out.println(
                            "Registro de paciente inválido: "
                                    + linea
                    );
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al cargar pacientes: "
                            + e.getMessage()
            );
        }
    }

    /*
     * Lee citas.csv y reconstruye cada Cita.
     *
     * Primero busca el Doctor y Paciente
     * correspondientes mediante sus ID.
     */
    private void cargarCitas(
            List<Cita> citas,
            List<Doctor> doctores,
            List<Paciente> pacientes) {

        try (BufferedReader lector =
                     Files.newBufferedReader(
                             archivoCitas,
                             StandardCharsets.UTF_8)) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos =
                        linea.split(",", -1);

                if (datos.length == 6) {

                    String idCita =
                            datos[0].trim();

                    String fecha =
                            datos[1].trim();

                    String hora =
                            datos[2].trim();

                    String motivo =
                            datos[3].trim();

                    String idDoctor =
                            datos[4].trim();

                    String idPaciente =
                            datos[5].trim();

                    Doctor doctor =
                            buscarDoctor(
                                    doctores,
                                    idDoctor
                            );

                    Paciente paciente =
                            buscarPaciente(
                                    pacientes,
                                    idPaciente
                            );

                    if (doctor != null
                            && paciente != null) {

                        Cita cita =
                                new Cita(
                                        idCita,
                                        fecha,
                                        hora,
                                        motivo,
                                        doctor,
                                        paciente
                                );

                        citas.add(cita);

                    } else {

                        System.out.println(
                                "No se pudo reconstruir la cita "
                                        + idCita
                                        + ": doctor o paciente "
                                        + "no encontrado."
                        );
                    }

                } else {

                    System.out.println(
                            "Registro de cita inválido: "
                                    + linea
                    );
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al cargar citas: "
                            + e.getMessage()
            );
        }
    }

    /*
     * Busca un doctor mediante su ID.
     */
    private Doctor buscarDoctor(
            List<Doctor> doctores,
            String id) {

        for (Doctor doctor : doctores) {

            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }

        return null;
    }

    /*
     * Busca un paciente mediante su ID.
     */
    private Paciente buscarPaciente(
            List<Paciente> pacientes,
            String id) {

        for (Paciente paciente : pacientes) {

            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }

        return null;
    }

    /*
     * Limpia los campos antes de escribirlos
     * en el archivo CSV.
     *
     * Esto evita que una coma o salto de línea
     * rompa la estructura del archivo.
     */

    private String limpiarCampo(String valor) {

        if (valor == null) {
            return "";
        }

        return valor
                .replace(",", " ")
                .replace("\n", " ")
                .replace("\r", " ")
                .trim();
    }
}