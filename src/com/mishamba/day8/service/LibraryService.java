package com.mishamba.day8.service;

import com.mishamba.day8.model.entity.CustomBook;
import com.mishamba.day8.model.exception.ModelException;
import com.mishamba.day8.service.exception.ServiceException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public interface LibraryService {
    void addBook(@NotNull String title, int pages,
                 ArrayList<String> authors) throws ModelException, ServiceException;
    void removeBook(@NotNull String title, int pages, ArrayList<String> authors)
            throws ServiceException;
    ArrayList<CustomBook> findByTitle(String title) throws ModelException, ServiceException;
    ArrayList<CustomBook> findByAuthors(String ... authors) throws ModelException, ServiceException;
    ArrayList<CustomBook> findByPages(int pages) throws ModelException, ServiceException;
    ArrayList<CustomBook> sortByTitle() throws ServiceException;
    ArrayList<CustomBook> sortByAuthors() throws ServiceException;
    ArrayList<CustomBook> sortByPages() throws ServiceException;
    ArrayList<CustomBook> selectAllBooks() throws ServiceException;
}
