import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class BinaryTreeNode<T> {
    public T key;
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;


    // Klausur 09.2021 Aufgabe 3
    /**
     *
     * @param root : Wurtzel eines korrekt gebildeten Baums
     * @param pred : Predicate
     * @param <T> : Typparameter
     * @return : ein Baum, der keine Knoten enthält, für die pred zum true liefert
     */
    public static <T> BinaryTreeNode<T> removeIf(BinaryTreeNode<T> root, Predicate<T> pred){
        if(root == null){
            return null;
        }

        // Wir fangen an, bei Blättern zu löschen und gehen dann schrittweise nach oben
        if(root.left != null){
           root.left =  removeIf(root.left, pred);
        }
        if(root.right != null){
           root.right =  removeIf(root.right, pred);
        }

        if(pred.test(root.key)){

            //Blatt des Baumes
            if(root.left == null && root.right == null){
                return null;
            }
            // Nur ein Zweig
            if(root.left == null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }
            // Zwei zweige
            if(root.left.right == null){
                root.left.right = root.right;
                return root.left;
            }

            root.key = removeRightmostDescendantAndReturnItsKey(root.left);
        }

        return null;
    }

    /**
     *
     * @param keyValusArray : a sorted array of Integer-type values
     * @param cmp : a comparator to compare integer values
     * @return : a binary search tree in ascending order (level-traversal)
     */
    public static BinaryTreeNode<Integer> sortedArrayToTree(Integer[] keyValusArray, Vergleicher cmp){
        return sortedArrayToTreeHelper(keyValusArray,cmp,0,keyValusArray.length-1);
    }
    /**
     *
     * @param keyValuesArray: a sorted array of Integer-type values
     * @param cmp : a comparator to compare integer values
     * @param leftBound : leftmost index which marks the starting point
     * @param rightBound : rightmost index which marks the end
     * @return  : a binary search tree in ascending order (level-traversal)
     */
    private static  BinaryTreeNode<Integer> sortedArrayToTreeHelper(Integer[] keyValuesArray, Vergleicher cmp, int leftBound, int rightBound){
        if(keyValuesArray == null){
            return null;
        }
        if(leftBound > rightBound) {
            return null;
        }
        int middle = (leftBound + rightBound)/2;
        BinaryTreeNode<Integer> node = new BinaryTreeNode();
        node.key = keyValuesArray[middle];
        node.left = sortedArrayToTreeHelper(keyValuesArray,cmp,leftBound,middle-1);
        node.right = sortedArrayToTreeHelper(keyValuesArray,cmp,middle + 1, rightBound);
        return node;
    }

    /**
     *
     * @param lst : of type java.util.List
     * @param size : size of list
     * @return : a binary Search tree constructed on the basis of list
     */
    public static BinaryTreeNode<Integer> sortedListToTree(List<Integer> lst, int size){
        return sortedListToTreeHelper(lst,lst.iterator(),size);
    }
    private static BinaryTreeNode<Integer> sortedListToTreeHelper(List<Integer> lst,Iterator<Integer> iter, int size){
        if( size <= 0){
            return null;
        }

        BinaryTreeNode<Integer> left = sortedListToTreeHelper(lst,iter,size/2);

        BinaryTreeNode<Integer> node = new BinaryTreeNode<>();
        node.key = iter.next();
        node.left = left;
        node.right = sortedListToTreeHelper(lst,iter,size- size/2 - 1);
        /*
        node.key = lst.key;
        node.left = left;
        lst = lst.next;
        node.right = sortedListToTreeHelper(lst,mid +1,rightBound);
        */
        return node;
    }

    public static <T> void inOrderPrint(BinaryTreeNode<T> node){
        if(node == null){
            return;
        }
        System.out.print("(  ");
        inOrderPrint(node.left);
        System.out.print("|  "+node.key.toString()+ " ");
        System.out.print("|");
        inOrderPrint(node.right);
        System.out.print(" )");
    }

    private static <T> T removeRightmostDescendantAndReturnItsKey(BinaryTreeNode<T> root){
        //      4 -> (1, 9)
        //      9 -> (8, null)
        // Then we need a reference on the node "4" so that we can delete node "9" from the tree before copying its value.
        // Hence we check for root.right.right == null.
        // We have once already checked that root.right != null in calling method so no NullPointerException will be thrown
        // when the condition is true then that means root.right is the rightmost node
        if(root.right.right  == null){
            T toReturn = root.right.key;
            root.right = root.right.left;
            return toReturn;
        }else{
            return removeRightmostDescendantAndReturnItsKey(root.right);
        }
    }
}
