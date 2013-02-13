public interface Queue {
    //pre: element != null
    //post: size == size_old + 1 && elements[size_old] = element
    void store(Object element);

    //pre: size > 0
    //post: return element != null
    Object retrieve();

    int size();

    boolean isEmpty();
}
