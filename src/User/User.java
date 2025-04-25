package User;

public abstract class User {
    private String name;
    private String nick;
    private String password;

    public User(String name, String nick, String password) {
        this.name = name;
        this.nick = nick;
        this.password = password;
    }

    public abstract int getScore();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
