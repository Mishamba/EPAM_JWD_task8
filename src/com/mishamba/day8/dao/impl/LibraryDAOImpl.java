package com.mishamba.day8.dao.impl;

import com.mishamba.day8.dao.LibraryDAO;
import com.mishamba.day8.model.entity.CustomBook;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class LibraryDAOImpl implements LibraryDAO {
    private static final String SELECT_BOOKS = "SELECT " +
            "book.id, book.title, book.pages, author.pseudonym FROM author " +
            "LEFT JOIN author_book_references " +
            "ON author.id = author_book_references.author_id " +
            "LEFT JOIN book ON book.id = author_book_references.book_id";
    private static final String INSERT_BOOK_IN_BOOKS = "INSERT INTO" +
            " book(id, title, pages) VALUES (?, ?);";
    private static final String REMOVE_BOOK = "DELETE FROM book" +
            " WHERE title = ? AND pages = ?;" +
            "DELETE FROM authors_book_references WHERE book_id = ?;";
    private static final String LOG4JPROPERTIES = "log4j.properties";
    private static final Logger logger = Logger.getRootLogger();
    private static final String DB_URL =
            "jdbc:postgresql://localhost:5432/library";
    private static LibraryDAOImpl instance;

    private LibraryDAOImpl() {
        PropertyConfigurator.configure(LOG4JPROPERTIES);
    }

    public static LibraryDAOImpl getInstance() {
        if (instance == null) {
            instance = new LibraryDAOImpl();
        }

        return instance;
    }

    @Override
    public void addBook(CustomBook book) throws SQLException {
        for (CustomBook bookFromDB : getBooksFromDB(book)) {
            if (book.equals(bookFromDB)) {
                throw new SQLException("such book already exists");
            }
        }

        try {
            Class.forName("");
        } catch (ClassNotFoundException e) {
            logger.error(e);
        }

        try (Connection connection = DriverManager.getConnection(
                DB_URL, getProperties());
             PreparedStatement executeStatement =
                     connection.prepareStatement(INSERT_BOOK_IN_BOOKS)) {
            executeStatement.setString(1, book.getTitle());
            executeStatement.setInt(2, book.getPages());
            executeStatement.executeUpdate();
        }
    }

    @Override
    public void removeBook(CustomBook book) throws SQLException {
        for (CustomBook bookFromDB : getBooksFromDB(book)) {
            if (book.equals(bookFromDB)) {
                try {
                    Class.forName("");
                } catch (ClassNotFoundException e) {
                    logger.error(e);
                }

                try (Connection connection = DriverManager.getConnection(
                        DB_URL, getProperties());
                     PreparedStatement executeStatement =
                             connection.prepareStatement(REMOVE_BOOK)) {
                    executeStatement.setString(1, book.getTitle());
                    executeStatement.setInt(2, book.getPages());
                    executeStatement.setInt(3, book.getId());
                    executeStatement.executeUpdate();
                }
            }
        }
    }

    private @NotNull ArrayList<CustomBook> getBooksFromDB(CustomBook book)
            throws SQLException {
        try {
            Class.forName("");
        } catch (ClassNotFoundException e) {
            logger.error(e);
        }

        String sqlForBooks = SELECT_BOOKS + " WHERE title = " + book.getTitle() +
                " pages = " + book.getPages() + ";";
        ArrayList<CustomBook> formedBooks = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                DB_URL, getProperties());
             Statement bookStatement =
                     connection.createStatement();
             ResultSet books = bookStatement.executeQuery(sqlForBooks)) {
            ArrayList<String> authors = new ArrayList<>();
            while (books.next()) {
                for (String author : book.getAuthors()) {
                    if (books.getString("pseudonym").equals(author)) {
                        authors.add(books.getString("pseudonym"));
                    }
                }
                formedBooks.add(new CustomBook(book.getTitle(),
                        book.getPages(), authors));
                formedBooks.get(formedBooks.size() - 1).
                        setId(books.getInt("id"));
            }
        }

        return formedBooks;
    }

    @Override
    public ArrayList<CustomBook> findByTitle(String title) throws SQLException {
        return getBooksFromDB(SELECT_BOOKS + " WHERE title = '" + title + "' ORDER BY pages;");
    }

    @Override
    public ArrayList<CustomBook> findByAuthors(String... authors) throws SQLException {
        String sql = formSQL(authors);
        return getBooksFromDB(sql);
    }

    private @NotNull String formSQL(String @NotNull ... parameters) {
        StringBuilder result = new StringBuilder(SELECT_BOOKS);
        result.append(" WHERE ");
        for (String parameter : parameters) {
            result.append(" ").append("pseudonym").append(" = ").append(parameter).append(" OR");
        }

        result. append(";");

        return result.toString();
    }

    @Override
    public ArrayList<CustomBook> findByPages(int pages) throws SQLException {
        return getBooksFromDB(SELECT_BOOKS + " WHERE pages = " + pages + " ORDER BY pages;");
    }

    @Override
    public ArrayList<CustomBook> selectAllBooks() throws SQLException {
        return getBooksFromDB(SELECT_BOOKS + ";");
    }

    private @NotNull ArrayList<CustomBook> getBooksFromDB(String sql) throws SQLException {
        try {
            Class.forName("");
        } catch (ClassNotFoundException e) {
            logger.error(e);
        }

        ArrayList<CustomBook> books = new ArrayList<>();
        CustomBook currentBook = new CustomBook();

        try (Connection connection = DriverManager.getConnection(
                DB_URL, getProperties());
            Statement bookStatement =
                     connection.createStatement();
            ResultSet bookSet = bookStatement.executeQuery(sql)) {
            while(bookSet.next()) {
                if (currentBook.getTitle().equals(bookSet.getString("title")) &&
                        currentBook.getPages() == bookSet.getInt("pages") &&
                        currentBook.getPages() != 0 &&
                        !currentBook.getTitle().isEmpty()) {
                    ArrayList<String> authors = currentBook.getAuthors();
                    authors.add(bookSet.getString("pseudonym"));
                    currentBook.setAuthors(authors);
                } else if (currentBook.getTitle().isEmpty() &&
                        currentBook.getPages() == 0) {
                    currentBook.setPages(bookSet.getInt("pages"));
                    currentBook.setTitle(bookSet.getString("title"));
                    ArrayList<String> authors = new ArrayList<>();
                    authors.add(bookSet.getString("pseudonym"));
                    currentBook.setAuthors(authors);
                } else {
                    books.add(currentBook);
                    currentBook = new CustomBook();
                }
            }
        }

        return books;
    }

    private @NotNull Properties getProperties() {
        Properties properties = new Properties();
        properties.put("user", "postgres");
        properties.put("password", "123");
        return properties;
    }
}
