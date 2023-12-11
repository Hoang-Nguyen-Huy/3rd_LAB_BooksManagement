package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nguyen Huy Hoang SE180435
 */
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import model.Author;
import model.Book;
import model.I_AuthorList;
import model.I_BookList;
import model.I_Menu;
import utils.Utils;
public class BookList extends ArrayList<Book> implements I_BookList {

    @Override
    public void show() {
        BookList list = this.readFile();
        if (list.isEmpty()) {
            System.out.println("Empty file");
            return;
        }
        
        Collections.sort(list, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getId().compareTo(b2.getId());
            }
        });
        
        for (Book book : list) {
            System.out.println(book.toString());
        }
    }

    @Override
    public void add() {
        
        boolean isAdding = true;
        while(isAdding) {
            String id = Utils.inpId(this);
            String title = Utils.inpTitle();
            float price = Utils.inpPrice();
            String authorId = Utils.inpAuthorId();
            String authorName = "";
            I_AuthorList list = new AuthorList();
            AuthorList authors = list.readAuthorFromFile();
            for (Author author : authors) {
                if (authorId.equals(author.getAuthorId())) {
                    authorName = author.getName();
                    break;
                }
            }
            Author author1 = new Author(authorId, authorName);
            Book book = new Book(id, title, price, author1);
            this.add(book);
            System.out.println("Added successfully!!!");
            System.out.println("You should saved to file before doing others stuff!!!");
            
            I_Menu menu = new Menu();
            isAdding = menu.confirmYesNo("Do you want to continue?(Y/N)");
        }
        
    }

    @Override
    public void update() {
        BookList list = this.readFile();
        if (list.isEmpty()) {
            System.out.println("Empty file");
            return;
        }
        
        String id = Utils.findId(this);
        
        boolean found = false;
        for (Book book : list) {
            if (id.trim().equals(book.getId())) {
                System.out.println(book.toString());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No book found!!!");
            return;
        }
        
        System.out.println("Leave blank if you don't want to change that information!!");
        String title = Utils.upTitle();
        float price = Utils.upPrice();
        String authorId = Utils.upAuthorId();
        
        for (Book upBook : list) {
            if (id.trim().equals(upBook.getId())) {
                if (!title.isEmpty()) {
                    upBook.setTitle(title);
                }
                if (price != 0) {
                    upBook.setPrice(price);
                }
                if(!authorId.isEmpty()) {
                    upBook.getAuthor().setAuthorId(authorId.trim());
                    I_AuthorList authorList = new AuthorList();
                    AuthorList check = authorList.readAuthorFromFile();
                    for (Author author : check) {
                        if (authorId.trim().equals(author.getAuthorId())) {
                            upBook.getAuthor().setName(author.getName());
                        }
                    }
                }
            }
        }
        
        
        upFile(list);
        System.out.println("Update successfully!!!");
        
    }

    @Override
    public void delete() {
        BookList list = this.readFile();
        if (list.isEmpty()) {
            System.out.println("Empty file");
            return;
        }
        
        String id = Utils.findId(this);
        
        boolean found = false;
        for (Book checkBook : list) {
            if (id.trim().equals(checkBook.getId())) {
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found!!!");
            return;
        } else {
            System.out.println("Book exist");
            I_Menu menu = new Menu();
            boolean choice = menu.confirmYesNo("Are you sure you want to delete this book?(Y/N)");
            if (choice) {
                Iterator<Book> iterator = list.iterator();
                while(iterator.hasNext()) {
                    Book book = iterator.next();
                    if (id.trim().equals(book.getId())) {
                        iterator.remove();
                    }
                }
                upFile(list);
                System.out.println("Deleted successfully!!!");
            } else {
                return;
            }
        }
        
    }

    @Override
    public void search() {
        
        BookList list = this.readFile();
        if (list.isEmpty()) {
            System.out.println("Empty list");
            return;
        }
        
        String title = Utils.inpTitle();
        
        ArrayList<Book> res = new ArrayList<>();
        
        for (Book book : list) {
            if (book.getTitle().contains(title.trim())) {
                res.add(book);
            }
        }
        
        for (Book found : res) {
            System.out.println(found.toString());
        }
    }

    @Override
    public void saveToFile() {
        BookList list = this.readFile();
        list.addAll(this);
        if (list.isEmpty() || this.isEmpty()) {
            System.out.println("You have not added anything yet!!!");
            return;
        }
        
        try(FileOutputStream fileOutput = new FileOutputStream("src\\data\\book.dat");
             ObjectOutputStream write = new ObjectOutputStream(fileOutput)) {
            
            write.writeObject(list);
            System.out.println("Saved to file successfully!!!");
            this.clear();
        } catch (IOException e) {
            System.out.println("Failed to save to file!!!");
        }
        
    }
    
    
    public BookList readFile() {
        BookList res = new BookList();
        try (FileInputStream fileInput = new FileInputStream("src\\data\\book.dat");
             ObjectInputStream read = new ObjectInputStream(fileInput)) {
            
            res = (BookList) read.readObject();
        } catch(EOFException eof) {
            
        } catch (IOException | ClassNotFoundException ex) {
            
        }
        return res;
    }
    
    public void upFile(BookList list) {
        try (FileOutputStream fileOutput = new FileOutputStream("src\\data\\book.dat");
             ObjectOutputStream update = new ObjectOutputStream(fileOutput)) {
            
            update.writeObject(list);
        } catch (IOException e) {
            
        }
    }
}
