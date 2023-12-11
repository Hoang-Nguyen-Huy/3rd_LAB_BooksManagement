/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import model.Author;
import model.I_AuthorList;

/**
 *
 * @author Nguyen Huy Hoang SE180435
 * 
 */
public class AuthorList extends ArrayList<Author> implements I_AuthorList {

    @Override
    public void saveAuthorIdToFile() {
        Author author1 = new Author("A001", "Nguyen Nhat Anh");
        Author author2 = new Author("A002", "Ho Chi Minh");
        Author author3 = new Author("A003", "Nam Cao");
        Author author4 = new Author("A004", "Xuan Dieu");
        Author author5 = new Author("A005", "To Huu");
        this.add(author1);
        this.add(author2);
        this.add(author3);
        this.add(author4);
        this.add(author5);
        
        try(FileOutputStream fileOutput = new FileOutputStream("src\\data\\author.dat");
            ObjectOutputStream write = new ObjectOutputStream(fileOutput)) {
            
            write.writeObject(this);
        } catch (IOException e) {
            
        }
    }

    @Override
    public boolean checkExist(String id) {
        AuthorList check = readAuthorFromFile();
        for (Author author : check) {
            if (id.equals(author.getAuthorId())) {
                return true;
            }
        }
       return false;
    }
    
    @Override
    public AuthorList readAuthorFromFile() {
        AuthorList res = new AuthorList();
        try (FileInputStream fileInput = new FileInputStream("src\\data\\author.dat");
             ObjectInputStream read = new ObjectInputStream(fileInput)) {
            
            res = (AuthorList) read.readObject();
        } catch(EOFException eof) {
            
        } catch (IOException | ClassNotFoundException ex) {
            
        }
        return res;
    }
           
}
