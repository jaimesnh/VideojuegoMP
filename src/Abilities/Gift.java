package Abilities;

import java.util.ArrayList;

public class Gift extends SpecialAbility{
    private int minimumRange;

    public Gift (String name, int attack, int defense, int minimumRange){
        super(name, attack, defense);
        this.minimumRange = minimumRange;
    }

  //  public int useGift(int amountRange) {
    //    if (amountRange >= this.minimumRange) {
      //      return super.getAttack();
    //    } else {
      //      return 0;
   //     }
  //  }

    public String toString() {
        return super.toString() + String.format(", minimum range: %d", minimumRange);
    }

    public static String[] listGifts() {
        String[] Gifts = new String[SystemGame.giftsAvailable.size()];
        for (int i = 0; i < SystemGame.giftsAvailable.size(); i++) {
            Discipline discipline = SystemGame.giftsAvailable.get(i);
            Gifts[i] = discipline.toString();
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
