package User;

import Utils.Const;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getScore() {
        Player user = new Player("Arturo", "Turo", "aaaaaaaa", "RPG21");

        int score = user.getScore();

        assertEquals(Const.INITIAL_GOLD, score);
    }

    @Test
    void getName() {
        String name = "Diana";

        Player player = new Player(name, "Susan", "ssssssss", "RPG21");

        assertEquals(name, player.getName());
    }

    @Test
    void setName() {
        Player user = new Player("Jaime", "Jauma", "dddddddd", "RPG21");

        user.setName("Pedro");

        assertEquals("Pedro", user.getName());
    }

    @Test
    void getNick() {
        Player player = new Player("Ivan", "Pineaple", "fffffffff", "RPG21");

        String nick = player.getNick();

        assertEquals("Pineaple", nick);
    }

    @Test
    void setNick() {
        Player player = new Player("Arturo", "Turo", "aaaaaaaa", "RPG21");

        player.setNick("Pedrito");

        assertEquals("Pedrito", player.getNick());
    }

    @Test
    void getPassword() {
        Player player = new Player("Pedro", "Pedrito", "ContraseñaDePedrito", "RPG21");

        String password = player.getPassword();

        assertEquals("ContraseñaDePedrito", password);
    }

    @Test
    void setPassword() {
        Player player = new Player("Pedro", "Pedrito", "ContraseñaDePedrito", "RPG21");

        player.setPassword("ContraseñaDePedrito");

        assertEquals("ContraseñaDePedrito", player.getPassword());
    }
}