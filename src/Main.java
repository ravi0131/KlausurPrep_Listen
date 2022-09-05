import java.util.*;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        FoodStuff[] dailyFoods = intializeFoodStuffs();
        Arrays.sort(dailyFoods);
        String name = "bugaboo";
        //int calories = FoodStuff.allFoodStuffs.get(name);
        /*
        if(BinarySearch.binarySearchRecursive(dailyFoods, new FoodStuff(name))){
            System.out.println("FOUND");
        }else{
            System.out.println("NOT FOUND");
        }
        */
        //klausur2021_09_Aufgabe2();
        BinaryTreeNode<Integer> root = ListToBinaryTreeInitializer(8);
        BinaryTreeNode.inOrderPrint(root);

    }

    /**
     * @param : size of array
     * @return : initializes an array of given size with type Integer and returns a binaryTree constructed from the array
     */
    private static BinaryTreeNode<Integer> arrayToBinaryTreeInitializer(int size){
        int min = 1;
        int max = 30;
        Integer[] arr = new Integer[size];
        for(int i = 0; i < arr.length; i++){
            arr[i] = min + (int)(Math.random() * max);
        }
        Arrays.sort(arr);
        System.out.println("The initial array is "+ Arrays.deepToString(arr));
        return BinaryTreeNode.sortedArrayToTree(arr,new Vergleicher());

    }

    /**
     *
     * @param size : size of list
     * @return : a binary tree constructed from a list of given size. The list is initalized with random Integers
     */
    private static BinaryTreeNode<Integer> ListToBinaryTreeInitializer(int size){

        int min = 1;
        int max = 30;
        List<Integer> lst = new ArrayList<>();
        for(int i = 0; i < size; i++){
            lst.add(min + (int)(Math.random() * max));
        }
        Collections.sort(lst);
        System.out.println("The initial List is" + Arrays.deepToString(lst.toArray()));

        return BinaryTreeNode.sortedListToTree(lst,size);

        /*
        int min = 1;
        int max = 30;
        Integer[] arr = new Integer[size];
        for(int i = 0; i < arr.length; i++){
            arr[i] = min + (int)(Math.random() * max);
        }
        Arrays.sort(arr);
        System.out.println("The initial List is" + Arrays.deepToString(arr));
        ListItem<Integer> head = ListItem.arrayToListItem(arr);
        return BinaryTreeNode.sortedListToTree(head,size);
        */
    }

    private static void klausur2021_09_Aufgabe2() {
        // Driver code for testing the Aufgabe

        Integer[] arr = new Integer[10];
        int min = 1;
        int max = 20;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = min + (int) (Math.random() * max);
        }

        int limit = 30;
        Adder adder = new Adder();
        Vergleicher cmp = new Vergleicher();
        Integer[] brr = {limit - 1};
        System.out.println(Arrays.deepToString(arr));
        ListWrapper nestedLsts = ListItem.makeListOfLimitedListsFromArray(arr, adder, limit, cmp);

        for (ListWrapper mainLst = nestedLsts; mainLst != null; mainLst = mainLst.next) {
            StringBuilder str = new StringBuilder("Sublist: ");
            for (ListItem<Integer> subLst = mainLst.key; subLst != null; subLst = subLst.next) {
                str.append(subLst.key);
                str.append(", ");
            }
            System.out.println(str);
        }
    }

    private static FoodStuff[] intializeFoodStuffs() {
        // size is 20 or 15 ( randomly selected)

        String[] names1 = {"apple", "banana", "blueberries", "strawberries", "redbull", "milk", "chicken breast", "ramen noodles", "eggs", "walnuts",
                "raisins", "pumpkin seeds", "chia seeds", "oranges", "blackberry", "sardine", "tuna", "smoked salmon", "hummus", "french fries"};
        String[] names2 = {"apple", "banana", "blueberries", "strawberries", "redbull", "milk", "chicken breast", "ramen noodles", "eggs", "walnuts",
                "raisins", "pumpkin seeds", "chia seeds", "oranges"};
        Random rand = new Random(System.currentTimeMillis());
        int coinToss = rand.nextInt() % 2;
        String[] names = coinToss == 0 ? names1 : names2;

        int size = names.length;
        FoodStuff[] arr = new FoodStuff[size];
        int min = 1;
        int max = 300;

        for (int i = 0; i < arr.length; i++) {
            int cal = min + (int) (Math.random() * max);
            arr[i] = new FoodStuff(names[i], cal);
        }
        return arr;
    }

}

class ListWrapper {
    ListItem<Integer> key;
    ListWrapper next;
}

class Adder {
    public int add(int a, int b) {
        return a + b;
    }
}

class Vergleicher {
    public int compare(int a, int b) {
        return Integer.compare(a, b);
    }
}

class BinarySearch {
    /**
     * @param arr
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> boolean binarySearchIterative(T[] arr, T elem) {
        assert arr != null;
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (elem.compareTo(arr[m]) > 0) {
                l = m + 1;
            } else if (elem.compareTo(arr[m]) < 0) {
                r = m - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static <T extends Comparable<T>> boolean binarySearchRecursive(T[] arr, T elem) {
        return binarySearchRecursiveHelper(arr, elem, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> boolean binarySearchRecursiveHelper(T[] arr, T elem, int l, int r) {
        if(l > r){
            return false;
        }
        int m = (l + r) / 2;
        if (elem.compareTo(arr[m]) > 0) {
            return binarySearchRecursiveHelper(arr, elem, m + 1, r);
        }
        if (elem.compareTo(arr[m]) < 0) {
            return binarySearchRecursiveHelper(arr, elem, l, m - 1);
        }
        return elem.compareTo(arr[m]) == 0;
    }

}

class MyPred implements Predicate<Integer>{

    @Override
    public boolean test(Integer integer) {
        return integer % 2 == 0;
    }
}



