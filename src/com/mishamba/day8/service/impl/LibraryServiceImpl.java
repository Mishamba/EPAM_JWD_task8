package com.mishamba.day8.service.impl;

import com.mishamba.day8.dao.exception.DaoException;
import com.mishamba.day8.dao.impl.LibraryDAOImpl;
import com.mishamba.day8.model.entity.CustomBook;
import com.mishamba.day8.model.exception.ModelException;
import com.mishamba.day8.service.LibraryService;
import com.mishamba.day8.service.exception.ServiceException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class LibraryServiceImpl implements LibraryService {
    private static LibraryServiceImpl instance;

    private LibraryServiceImpl() {
    }

    public static LibraryServiceImpl getInstance() {
        if (instance == null) {
            instance = new LibraryServiceImpl();
        }

        return instance;
    }


    public void addBook(@NotNull String title, int pages,
                        ArrayList<String> authors) throws ServiceException {
        LibraryDAOImpl dataAccessObject = LibraryDAOImpl.getInstance();
        try {
            CustomBook book = CustomBook.Creator.create(title, pages, authors);
            dataAccessObject.addBook(book);
        } catch (SQLException | ModelException ex) {
            throw new ServiceException("can't add book", ex);
        }
    }

    @Override
    public void removeBook(@NotNull String title,
                           int pages, ArrayList<String> authors)
            throws ServiceException {
        LibraryDAOImpl dataAccessObject = LibraryDAOImpl.getInstance();
        try {
            CustomBook book = CustomBook.Creator.create(title, pages, authors);
            dataAccessObject.removeBook(book);
        } catch (SQLException | ModelException ex) {
            throw new ServiceException("can't remove book", ex);
        }
    }

    @Override
    public ArrayList<CustomBook> findByTitle(@NotNull String title)
            throws ServiceException {
        try {
            LibraryDAOImpl dataAccessObject = LibraryDAOImpl.getInstance();
            ArrayList<CustomBook> books = dataAccessObject.findByTitle(title);
            if (books.isEmpty()) {
                throw new ServiceException("no book with such title : " + title);
            }

            return books;
        } catch (SQLException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public ArrayList<CustomBook> findByAuthors(@NotNull String[] authors)
            throws ServiceException {
        try {
            LibraryDAOImpl dataAccessObject = LibraryDAOImpl.getInstance();
            ArrayList<CustomBook> books = dataAccessObject.findByAuthors(authors);
            if (books.isEmpty()) {
                throw new ServiceException("no book with this authors : " +
                        Arrays.toString(authors));
            }

            return books;
        } catch (SQLException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public ArrayList<CustomBook> findByPages(int pages) throws ServiceException {
        try {
            LibraryDAOImpl dataAccessObject = LibraryDAOImpl.getInstance();
            ArrayList<CustomBook> books = dataAccessObject.findByPages(pages);
            if (books.isEmpty()) {
                throw new ServiceException("no book with this count of pages : " +
                        pages);
            }

            return books;
        } catch (SQLException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public ArrayList<CustomBook> sortByTitle() throws ServiceException {
        Comparator title = com.mishamba.day8.model.comparator.
                Comparator::compareByTitle;
        return sortBy(title);
    }

    @Override
    public ArrayList<CustomBook> sortByAuthors() throws ServiceException {
        Comparator authors = com.mishamba.day8.model.comparator.
                Comparator::compareByAuthors;
        return sortBy(authors);
    }

    @Override
    public ArrayList<CustomBook> sortByPages() throws ServiceException {
        Comparator pages = com.mishamba.day8.model.comparator.
                Comparator::compareByPages;
        return sortBy(pages);
    }

    private @NotNull ArrayList<CustomBook> sortBy(Comparator comparator)
            throws ServiceException {
        try {
            LibraryDAOImpl dataAccessObject = LibraryDAOImpl.getInstance();
            ArrayList<CustomBook> books = dataAccessObject.selectAllBooks();
            if (books.isEmpty()) {
                throw new ServiceException("no books found");
            }
            books.sort(comparator);
            return books;
        } catch (SQLException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public ArrayList<CustomBook> selectAllBooks() throws ServiceException {
        LibraryDAOImpl dataAccessObject = LibraryDAOImpl.getInstance();

        try {
            ArrayList<CustomBook> books = dataAccessObject.selectAllBooks();
            if (books.isEmpty()) {
                throw new ServiceException("no books found");
            }
            return books;
        } catch (SQLException ex) {
            throw new ServiceException(ex);
        }
    }
}

interface Comparator extends java.util.Comparator<CustomBook> {
    int compare(CustomBook firstBook, CustomBook secondBook);
}
