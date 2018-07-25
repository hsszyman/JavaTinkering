
/**
 * Simple exercise i'm doing to practice linked list surgery and linked list collections
 */
public class Driver {
    public static void main(String[] args) {
        List<Integer> list = new List<>();
    }
}

/**
 * Simple implementation of a list
 * @param <T> : The data type that the list will store
 */
class List<T> {

    // Keep a reference to the first node in the list
    private Node<T> first;

    // Init list with a single value
    List (T value) {
        first = new Node<>(value);
    }

    // Init list with multiple values
    List (T... values) {
        if (values.length > 0)
            first = new Node<>(values[0]);
        addRest(first, values, 1);
    }

    // Init list as an empty list
    List () {}

    /**
     * Given an existing node, a list of values and an index
     * of where to select the next value from the array,
     * populate
     * the list with the values in the array recursively
     * NOTE: This is used internally and ideally, only once in the
     * multi-value constructor
     * @param current
     * @param values
     * @param currentArrIndex
     */
    private void addRest(Node<T> current, T[] values, int currentArrIndex) {
        if (current != null && currentArrIndex < values.length) {
            current.next = new Node<>(values[currentArrIndex]);
            ++currentArrIndex;
            addRest(current.next, values, currentArrIndex);
        }
    }

    /**
     * add set of values to possiblan existing linked list
     * @param values : values to add
     */
    public void addSet(T... values) {
        if (first != null)
            addRest(getLastNode(first), values, 0);
        else {
            first = new Node<>(values[0]);
            addRest(getLastNode(first), values, 1);
        }
    }


    /**
     * Add a value to the end of the linked list
     * @param value : value to be added at the end of list
     */
    void add(T value) {
        if (first != null) {
            getLastNode(first).setNext(new Node<>(value));
        }
        else {
            first = new Node<>(value);
        }
    }


    boolean remove(T target) {
        Node<T> tmpNode = find(target, first);
        boolean exists = false;
        if (first.getVal().equals(target)) {
            exists = true;
            first = first.getNext();
        }
        else if (tmpNode != null) {
            tmpNode.setNext(tmpNode.getNext().getNext());
            return true;
        }
        return exists;
    }

    void printAll() {
        printAll(first);
    }
    private void printAll(Node<T> current) {
        System.out.println(current.getVal().toString());
        if (current.hasNext()) {
            printAll(current.getNext());
        }
    }

    /**
     * @return last value in the list
     */
    T getLast() {
        return getLastNode(first).getVal();
    }

    /**
     * PRECONDITION : Node is non-null
     * For a given node depending on if the next is null either recurse with the next node or return current
     * (If current.getNext is null, we know it's at the end of the list)
     * @param current : current node to check
     * @return last node in the linked list
     */
    private Node<T> getLastNode(Node<T> current) {
        return (current.getNext() != null) ? (getLastNode(current.getNext())) : (current);
    }

    // Returns pointer one before the one found
    private Node<T> find(T val, Node<T> current) {
        if (current.hasNext()) {
            return current.getNext().getVal().equals(val) ? current : find(val, current.getNext());
        }
        else {
            return null;
        }
    }



    private class Node<T> {
        private T val;
        private Node<T> next;
        private Node(T val, Node<T> next) {
            this.val = val;
            this.next = next;
        }

        Node() {
            this(null, null);
        }

        Node(T val) {
            this(val, null);
        }

        T getVal() {
            return val;
        }

        void setVal(T val) {
            this.val = val;
        }

        Node<T> getNext() {
            return next;
        }

        void setNext(Node<T> next) {
            this.next = next;
        }

        boolean hasNext() {
            return next != null;
        }
    }
}