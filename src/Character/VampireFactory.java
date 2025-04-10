package Character;

public class VampireFactory implements PersonajeFactory {
    public Character crearPersonaje() {
        return new Character(
                "Drácula",
                HabilidadEspecial.VUELO,
                Arrays.asList(Armas.GARRAS),
                Armadura.NINGUNA,
                100,
                80
        );
    }
}
