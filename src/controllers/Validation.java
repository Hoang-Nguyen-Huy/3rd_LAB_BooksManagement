/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Book;
import model.I_AuthorList;

/**
 *
 * @author Nguyen Huy Hoang SE180435
 */
public class Validation {
    
    public static boolean checkId(String inp, BookList list) {
        if (inp.isEmpty()) {
            System.out.println("ID can't not be empty");
            return false;
        }
        String patern = "^B\\d{3}$";
        Pattern regex = Pattern.compile(patern);
        Matcher matcher = regex.matcher(inp);
        if (!matcher.matches()) {
            System.out.println("This id is not valid");
            return false;
        }
        for (Book book : list) {
            if (inp.equals(book.getId())) {
                System.out.println("ID must not be duplicated");
                return false;
            }
        }
        if (!checkDuplicate(inp, list)) {
            System.out.println("ID must not be duplicated");
            return false;
        }
        return true;
    }
    
    public static boolean checkTitle(String inp) {
        if (inp.isEmpty()) {
            System.out.println("This can not be empty");
            return false;
        }
        if (inp.matches("^.*[0-9!@#$%^&*(){}_+-=*/.<>?|].*")) {
            System.out.println("This can only contain characters");
            return false;
        }
        
        return true;
    }
    
    public static boolean checkPrice(String inp) {
        try {
            Float.parseFloat(inp);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number");
            return false;
        }
    }
    
    public static boolean checkAuthorId(String inp) {
        if (inp.isEmpty()) {
            System.out.println("This can't be empty");
            return false;
        }
        String patern = "^A\\d{3}$";
        Pattern regex = Pattern.compile(patern);
        Matcher matcher = regex.matcher(inp);
        if (!matcher.matches()) {
            System.out.println("This id is not valid");
            return false;
        }
        I_AuthorList check = new AuthorList();
        if (!check.checkExist(inp)) {
            System.out.println("This author id does not exist in our system");
            return false;
        }
        return true;
    }
        
    public static boolean checkDuplicate(String inp, BookList list) {
        BookList check = list.readFile();
        for (Book book : check) {
            if (inp.equals(book.getId())) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean checkFindId(String inp, BookList list) {
        String patern = "^B\\d{3}$";
        Pattern regex = Pattern.compile(patern);
        Matcher matcher = regex.matcher(inp);
        if (!matcher.matches()) {
            System.out.println("This id is not valid");
            return false;
        }
        for (Book book : list) {
            if (inp.equals(book.getId())) {
                System.out.println("This book has not been saved to file yet!!!");
                return false;
            }
        }
        if (!checkDuplicate(inp, list)) {
            return true;
        }
        return true;
    }
    
    public static boolean checkUpTitle(String inp) {
        if (inp.matches("^.*[0-9!@#$%^&*(){}_+-=*/.<>?|].*")) {
            System.out.println("This can only contain characters");
            return false;
        }
        
        return true;
    }
    
    public static boolean checkUpAuthorId(String inp) {
        if (inp.isEmpty()) {
            return true;
        }
        String patern = "^A\\d{3}$";
        Pattern regex = Pattern.compile(patern);
        Matcher matcher = regex.matcher(inp);
        if (!matcher.matches()) {
            System.out.println("This id is not valid");
            return false;
        }
        I_AuthorList check = new AuthorList();
        if (!check.checkExist(inp)) {
            System.out.println("This author id does not exist in our system");
            return false;
        }
        return true;
    }
    
}
