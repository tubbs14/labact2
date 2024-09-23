public class DynamicArray {
    private Object[] array;
    private int size;

    public DynamicArray() {
        array = new Object[1];
        size = 0;
    }

    public void add(Object element) {
        if (size == array.length) {
            resize();
        }
        array[size] = element;
        size++;
    }

    public void insert(int index, Object element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (size == array.length) {
            resize();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return array[index];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        int newCapacity = (array.length == 0) ? 1 : 2 * array.length;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    // Stack operations
    public void push(Object element) {
        add(element);
    }

    public Object pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        Object top = array[size - 1];
        remove(size - 1);
        return top;
    }

    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return array[size - 1];
    }
}
