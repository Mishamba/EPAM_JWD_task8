package com.mishamba.day8.dao;

import com.mishamba.day8.dao.exception.DaoException;
import com.mishamba.day8.model.entity.CustomBook;
import com.mishamba.day8.model.exception.ModelException;

import java.util.ArrayList;

public interface LibraryDAO {
    void addBook(CustomBook book) throws ModelException, DaoException;
    void removeBook(CustomBook book) throws ModelException, DaoException;
    ArrayList<CustomBook> findByTitle(String title);
    ArrayList<CustomBook> findByAuthors(String ... authors);
    ArrayList<CustomBook> findByPages(int pages);
    ArrayList<CustomBook> selectAllBooks();
}
