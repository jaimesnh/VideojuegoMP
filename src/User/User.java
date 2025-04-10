package User;

public class User {
    private String nombre;
    private String nick;
    private String password;

    public User(String nombre, String nick, String password) {
        this.nombre = nombre;
        this.nick = nick;
        this.password = password;
    }

    public void registrarse() {
    }

    public void darseDeBaja() {
    }

    public void iniciarSesion() {
    }

    public void cerrarSesion() {
    }

    // Getters y Setters opcionales
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}