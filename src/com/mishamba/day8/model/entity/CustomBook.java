package com.mishamba.day8.model.entity;

import com.mishamba.day8.model.exception.ModelException;
import com.mishamba.day8.validator.NegativePagesValidator;
import com.mishamba.day8.validator.SameAuthorsValidator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomBook {
    private int id;
    private String title;
    private ArrayList<String> authors;
    private int pages;

    public CustomBook(String title, int pages, ArrayList<String> authors) {
        this.title = title;
        this.pages = pages;
        this.authors = authors;
    }

    public CustomBook(String title, int pages) {
        this.title = title;
        this.pages = pages;
        this.authors = new ArrayList<>();
    }

    public CustomBook() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public int getPages() {
        return pages;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthors(@NotNull ArrayList<String> authors) {
        this.authors = new ArrayList<>();
        for (String author : authors) {
            authors.add(author);
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public static class Creator {
        @Contract("_, _, _ -> new")
        public static @NotNull CustomBook create(@NotNull String title, int pages,
                                                 @NotNull ArrayList<String> authors)
                throws ModelException {
            NegativePagesValidator negativePagesValidator =
                    new NegativePagesValidator();
            SameAuthorsValidator sameAuthorsValidator
                    = new SameAuthorsValidator();
            if (!negativePagesValidator.isNegative(pages) &&
                    !sameAuthorsValidator.haveNoSame(authors)) {
                throw new ModelException("parameters couldn't pass the validation");
            }

            return new CustomBook(title, pages, authors);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomBook)) {
            return false;
        }

        CustomBook book = (CustomBook) o;

        return book.getTitle().equals(this.getTitle()) &&
                book.getPages() == this.getPages() &&
                book.getAuthors().equals(this.getAuthors());
    }

    @Override
    public int hashCode() {
        int prime = 72;
        int hashCode = prime * pages;
        hashCode += prime * title.hashCode();
        for (String author : this.getAuthors()) {
            hashCode += author.hashCode();
        }

        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder book = new StringBuilder();
        book.append(this.id).append("\n");
        book.append(this.title).append("\n");
        book.append(this.pages).append("\n");
        book.append(this.authors).append("\n");
        return book.toString();
    }
}
