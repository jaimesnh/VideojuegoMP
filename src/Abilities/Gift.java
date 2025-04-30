package Abilities;

import java.util.ArrayList;
import SystemGame.SystemGame;

public class Gift extends SpecialAbility{
    private int minimumRange;

    public Gift() {
        super();
    }

    public Gift (String name, int attack, int defense, int minimumRange){
        super(name, attack, defense);
        this.minimumRange = minimumRange;
    }


    public String toString() {
        return super.toString() + String.format(", minimum range: %d", minimumRange);
    }

    public static String[] listAvailableGifts() {
        String[] Gifts = new String[SystemGame.giftsAvailable.size()];
        for (int i = 0; i < SystemGame.giftsAvailable.size(); i++) {
            Gift gift = SystemGame.giftsAvailable.get(i);
            Gifts[i] = gift.toString();
        }

        return Gifts;
    }

    public static ArrayList<Gift> loadFromArray(String[][] giftsList) {
        ArrayList<Gift> gifts = new ArrayList<>();
        for (String[] gft : giftsList) {
            gifts.add(new Gift(gft[0], Integer.parseInt(gft[1]), Integer.parseInt(gft[2]), Integer.parseInt(gft[3])));
        }
        return gifts;
    }

    public int getMinimumRange() {
        return minimumRange;
    }
    public void setMinimumRange(int minimumRange) {
        this.minimumRange = minimumRange;
    }
}
