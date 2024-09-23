import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryGUI {
    private Library library;
    private JTextArea outputArea;
    private JTextField indexField;
    private JTextField bookField;

    public LibraryGUI() {
        library = new Library();
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        panel.add(new JLabel("Book Title:"));
        bookField = new JTextField();
        panel.add(bookField);

        panel.add(new JLabel("Index:"));
        indexField = new JTextField();
        panel.add(indexField);

        JButton addButton = new JButton("Add Book");
        JButton insertButton = new JButton("Insert Book");
        JButton removeButton = new JButton("Remove Book");
        JButton searchButton = new JButton("Search Book");
        JButton displayButton = new JButton("Display Total Books");
        JButton checkEmptyButton = new JButton("Check Empty");
        JButton undoButton = new JButton("Undo");
        JButton sortButton = new JButton("Sort Books");

        panel.add(addButton);
        panel.add(insertButton);
        panel.add(removeButton);
        panel.add(searchButton);
        panel.add(displayButton);
        panel.add(checkEmptyButton);
        panel.add(undoButton);
        panel.add(sortButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookTitle = bookField.getText();
                library.addBook(bookTitle);
                outputArea.append("Book \"" + bookTitle + "\" added.\n");
                bookField.setText("");
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = Integer.parseInt(indexField.getText());
                    String bookTitle = bookField.getText();
                    library.insertBook(index, bookTitle);
                    outputArea.append("Book \"" + bookTitle + "\" inserted at index " + index + ".\n");
                } catch (NumberFormatException ex) {
                    outputArea.append("Invalid index.\n");
                }
                indexField.setText("");
                bookField.setText("");
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = Integer.parseInt(indexField.getText());
                    library.removeBook(index);
                    outputArea.append("Book removed from index " + index + ".\n");
                } catch (NumberFormatException ex) {
                    outputArea.append("Invalid index.\n");
                }
                indexField.setText("");
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = Integer.parseInt(indexField.getText());
                    String book = library.searchBook(index);
                    if (book != null) {
                        outputArea.append("Book at index " + index + ": \"" + book + "\".\n");
                    } else {
                        outputArea.append("No book found at index " + index + ".\n");
                    }
                } catch (NumberFormatException ex) {
                    outputArea.append("Invalid index.\n");
                }
                indexField.setText("");
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.append("Total number of books: " + library.getTotalBooks() + ".\n");
            }
        });

        checkEmptyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (library.isEmpty()) {
                    outputArea.append("Library is empty.\n");
                } else {
                    outputArea.append("Library is not empty.\n");
                }
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                library.undo();
                outputArea.append("Undo last operation.\n");
            }
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                library.sortBooks();
                outputArea.append("Books sorted alphabetically.\n");
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryGUI();
            }
        });
    }
}
