package Character;

public class Weakness extends Modifier {

    private int sensitivity;

    // Constructor
    public Weakness(String name, int sensitivity) {
        super(name);
        this.sensitivity = sensitivity;
    }

    public String toString() {
        return String.format("%s (-%d)", this.getName(), this.sensitivity);
    }


    // GETTERS/SETTERS
    public int getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(int sensitivity) {
        this.sensitivity = sensitivity;
    }
}