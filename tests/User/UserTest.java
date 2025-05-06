package User;

import Utils.Const;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getScore() {
        Player user = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        int score = user.getScore();

        assertEquals(Const.INITIAL_GOLD, score);
    }

    @Test
    void getName() {
        String name = "Nombre";

        Player player = new Player(name, "Apodo", "Contraseña", "A11A1");

        assertEquals(name, player.getName());
    }

    @Test
    void setName() {
        Player user = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        user.setName("Pedro Sánchez");

        assertEquals("Pedro Sánchez", user.getName());
    }

    @Test
    void getNick() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        String nick = player.getNick();

        assertEquals("Apodo", nick);
    }

    @Test
    void setNick() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        player.setNick("Pedrito");

        assertEquals("Pedrito", player.getNick());
    }

    @Test
    void getPassword() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        String password = player.getPassword();

        assertEquals("Contraseña", password);
    }

    @Test
    void setPassword() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        player.setPassword("ContraseñaDePedrito");

        assertEquals("ContraseñaDePedrito", player.getPassword());
    }
}