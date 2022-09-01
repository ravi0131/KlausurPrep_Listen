import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
    }

    private void klausur2021_09_Aufgabe2() {
        // Driver code for testing the Aufgabe
        Integer[] arr = new Integer[2];
        int min = 1;
        int max = 30;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = min + (int) (Math.random() * max);
        }

        int limit = 20;
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
