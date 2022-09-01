public class ListItem<T extends Number> {
    T key;
    ListItem<T> next;

    public static ListWrapper makeListOfLimitedListsFromArray(Integer[] a, Adder adder, Integer limit, Vergleicher cmp) {
        // check case a.length == 0
        // return head of new mainLst
        if (a.length == 0) {
            return new ListWrapper();
        }
        ListWrapper head = new ListWrapper();
        ListWrapper mainLstPtr = head;
        ListItem<Integer> subLstPtr = null;
        int sum = a[0];
        mainLstPtr.key = new ListItem<>();
        subLstPtr = mainLstPtr.key;
        subLstPtr.key = a[0];


        // SubLstPtr points to the last added elem of a sublist
        // mainLstPtr points to the current mainLst
        for (int i = 1; i < a.length; i++) {
            Integer elem = a[i];
            sum = adder.add(sum, elem);

            if (cmp.compare(limit, sum) >= 0) {
                if (mainLstPtr.key == null) {
                    mainLstPtr.key = new ListItem<>();
                    subLstPtr = mainLstPtr.key;
                    subLstPtr.key = elem;
                    continue;
                }
                ListItem<Integer> tmp = new ListItem<>();
                tmp.key = elem;
                subLstPtr.next = tmp;
                subLstPtr = subLstPtr.next;
            } else {
                mainLstPtr.next = new ListWrapper();
                mainLstPtr = mainLstPtr.next;
                mainLstPtr.key = new ListItem<>();
                subLstPtr = mainLstPtr.key;
                subLstPtr.key = elem;
                sum = a[i];
            }
        }
        return head;

    }
}