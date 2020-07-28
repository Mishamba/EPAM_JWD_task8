package com.mishamba.day8.dao;

import com.mishamba.day8.model.entity.CustomBook;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LibraryDAO {
    void addBook(CustomBook book) throws SQLException;
    void removeBook(CustomBook book) throws SQLException;
    ArrayList<CustomBook> findByTitle(String title) throws SQLException;
    ArrayList<CustomBook> findByAuthors(String ... authors) throws SQLException;
    ArrayList<CustomBook> findByPages(int pages) throws SQLException;
    ArrayList<CustomBook> selectAllBooks() throws SQLException;
}
