import java.util.List;

public class ListItem<T > {
    T key;
    ListItem<T> next;
    ListItem<T> prev;

    /**
     * This method is from Aufgabe 2 Klausur 09.2021 (AuD)
     * @param a : array
     * @param adder : adds two elems
     * @param limit : the sum of each sublist should not extend this value
     * @param cmp : a comparator object to compare values
     * @return : a nested list with each sublist containing elements that don't cross the limit when summed up
     */
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
        // if lst only has 1 element then we won't go into loop body at all
        for (int i = 1; i < a.length; i++) {
            Integer elem = a[i];
            sum = adder.add(sum, elem);

            if (cmp.compare(limit, sum) >= 0) {
                // Untere Fall tritt ein nur beim ersten Durchlauf
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


    /**
     *
     * @param lst : Kopf einer korrekt gebildeten Liste
     * @param key : key value to be inserted
     * @param pos : position at which key is to be inserted
     * @param <T> : TypeParameter needed because method is static
     */
    public static <T>void addSinglyLinked(ListItem<T> lst,T key, int pos){
        assert  key != null;
        ListItem<T> head = lst;
        if(pos < 0){
            throw new IndexOutOfBoundsException("Invalid Position: "+pos);
        }
        ListItem<T> tmp = new ListItem<T>();
        tmp.key = key;
        if(pos == 0){
            tmp.next = head;
            head = tmp;
            return;
        }
        for(ListItem<T> p = head; p != null; p = p.next){
            pos--;  // this ensures the p always points to the element after which the key is to be inserted
            if(pos == 0){
                tmp.next = p.next;
                p.next = tmp;
                return;
            }
        }

    }

    public static <T> void addDoublyLinked(ListItem<T> lst, T key, int pos){
        assert  key != null;
        ListItem<T> head = lst;
        if(pos < 0){
            throw new IndexOutOfBoundsException("Invalid Position: "+pos);
        }
        ListItem<T> tmp = new ListItem<T>();
        tmp.key = key;
        if(pos == 0){
            tmp.next = head;
            head.prev = tmp;
            head = tmp;
            return;
        }
        for(ListItem<T> p = head; p != null; p = p.next){
            pos--;  // this ensures the p always points to the element after which the key is to be inserted
            if(pos == 0){
                ListItem<T> pNextOld = p.next;
                tmp.next = pNextOld;
                p.next = tmp;
                // update prev refrences
                pNextOld.prev = tmp;
                tmp.prev = p;
                return;
            }
        }
    }

    public static <T> ListItem<T> arrayToListItem(T[] array){
        if(array == null){
            return null;
        }
        ListItem<T> head = null;
        ListItem<T> current = null;
        for(int i = 0; i < array.length; i++){
            // only occurs in first iteration
            if(current == null){
                current = new ListItem<>();
                current.key = array[i];
                head = current;
                continue;
            }
            current = current.next;
            current = new ListItem<>();
            current.key = array[i];

        }
        return head;
    }
}