package Character;

public class Strength extends Modifier {

    private int power;

    // Constructor
    public Strength(String name, int effectiveness) {
        super(name);
        this.power = effectiveness;
    }

    public String toString() {
        return String.format("%s (+%d)", this.getName(), this.power);
    }

    // GETTERS/SETTERS
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

}