import java.util.HashMap;

public class FoodStuff implements Comparable<FoodStuff> {
    private final String name;
    private int calorieCount;
    static public HashMap<String,Integer> allFoodStuffs;
    static{
        allFoodStuffs = new HashMap<>();
    }

    public FoodStuff(String name){
        this.name = name;
    }

    public FoodStuff(String name, int calorieCount) {
        this(name);
        this.calorieCount = calorieCount;
        allFoodStuffs.put(name,calorieCount);
    }

    public String getName() {
        return name;
    }

    public int getCalorieCount() {
        return calorieCount;
    }

    public void setCalorieCount(int calorieCount) {
        this.calorieCount = calorieCount;
    }

    @Override
    public int compareTo(FoodStuff o) {
        return this.name.compareTo(o.getName());
    }
}
