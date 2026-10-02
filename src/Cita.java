public class Cita {

    private String id;
    private String fecha;
    private String hora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(String id, String fecha, String hora, String motivo,
                Doctor doctor, Paciente paciente) {

        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }
}