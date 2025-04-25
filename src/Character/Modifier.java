package Character;

public abstract class Modifier {

    private String name;
    private int value;

    public Modifier(String name, int value) {
        this.name = name;
        setValue(value); // Use setter to apply validation
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        if (value < 1 || value > 5) {
            throw new IllegalArgumentException("Value must be between 1 and 5.");
        }
        this.value = value;
    }
}