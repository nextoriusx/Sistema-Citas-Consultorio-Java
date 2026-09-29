public class Administrador {

    private String usuario;
    private String contrasena;

    public Administrador(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public boolean autenticar(String usuario, String contrasena) {
        return this.usuario.equals(usuario)
                && this.contrasena.equals(contrasena);
    }
}