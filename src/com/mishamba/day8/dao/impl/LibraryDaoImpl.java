package com.mishamba.day8.dao.impl;

import com.mishamba.day8.dao.LibraryDAO;
import com.mishamba.day8.dao.exception.DaoException;
import com.mishamba.day8.model.entity.CustomBook;
import com.mishamba.day8.model.exception.ModelException;

import java.util.ArrayList;

public class LibraryDaoImpl implements LibraryDAO {
    @Override
    public void addBook(CustomBook book) throws ModelException, DaoException {

    }

    @Override
    public void removeBook(CustomBook book) throws ModelException, DaoException {

    }

    @Override
    public ArrayList<CustomBook> findByTitle(String title) {
        return null;
    }

    @Override
    public ArrayList<CustomBook> findByAuthors(String... authors) {
        return null;
    }

    @Override
    public ArrayList<CustomBook> findByPages(int pages) {
        return null;
    }

    @Override
    public ArrayList<CustomBook> selectAllBooks() {
        return null;
    }
}
