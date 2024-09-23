public class Library {
    private DynamicArray books;
    private DynamicArray undoStack; 

    public Library() {
        books = new DynamicArray();
        undoStack = new DynamicArray();
    }

    public void addBook(String bookTitle) {
        books.add(bookTitle);
        undoStack.push("add:" + bookTitle); 
    }

    public void insertBook(int index, String bookTitle) {
        try {
            books.insert(index, bookTitle);
            undoStack.push("insert:" + index + ":" + bookTitle); 
        } catch (IndexOutOfBoundsException e) {
            
        }
    }

    public void removeBook(int index) {
        try {
            String book = (String) books.get(index);
            books.remove(index);
            undoStack.push("remove:" + index + ":" + book); 
        } catch (IndexOutOfBoundsException e) {
            
        }
    }

    public String searchBook(int index) {
        try {
            return (String) books.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public int getTotalBooks() {
        return books.size();
    }

    public boolean isEmpty() {
        return books.isEmpty();
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }

        String lastAction = (String) undoStack.pop();
        String[] actionParts = lastAction.split(":");

        switch (actionParts[0]) {
            case "add":
                books.remove(books.size() - 1); 
                break;
            case "insert":
                int insertIndex = Integer.parseInt(actionParts[1]);
                books.remove(insertIndex); 
                break;
            case "remove":
                int removeIndex = Integer.parseInt(actionParts[1]);
                books.insert(removeIndex, actionParts[2]); 
                break;
        }
    }

    public void sortBooks() {
        int n = books.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                String book1 = (String) books.get(j);
                String book2 = (String) books.get(minIndex);
                if (book1.compareTo(book2) < 0) {
                    minIndex = j;
                }
            }
            String temp = (String) books.get(minIndex);
            books.insert(minIndex, books.get(i));
            books.remove(minIndex + 1); 
            books.insert(i, temp);
            books.remove(i + 1);
        }
    }
}
