/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import controllers.BookList;
import controllers.Validation;
import java.util.Scanner;

/**
 *
 * @author Nguyen Huy Hoang SE180435
 */
public class Utils {

    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static int getInt() {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter your choices: ");
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check);
        return number;
    }

    public static boolean confirmYesNo(String welcome) {
        boolean result = false;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }
    
    public static String inpId(BookList list) {
        String inp;
        Scanner sc = new Scanner(System.in);
        System.out.println("ID format Bxxx. Eg: B001");
        do {
            System.out.print("Enter id: ");
            inp = sc.nextLine();
        } while(!Validation.checkId(inp, list));
        return inp;
    }
    
    public static String inpTitle() {
        String inp;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter title: ");
            inp = sc.nextLine();
        } while(!Validation.checkTitle(inp));
        return inp;
    }
    
    public static float inpPrice() {
        String inp = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter price: ");
            inp = sc.nextLine();
        } while(!Validation.checkPrice(inp));
        return Float.parseFloat(inp);
    }
    
    public static String inpAuthorId() {
        String inp;
        Scanner sc = new Scanner(System.in);
        System.out.println("ID format Axxx. Eg: A001");
        do {
            System.out.print("Enter author id: ");
            inp = sc.nextLine();
        } while(!Validation.checkAuthorId(inp));
        return inp;
    }
    
    public static String findId(BookList list) {
        String inp;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter id: ");
            inp = sc.nextLine();
        } while(!Validation.checkFindId(inp, list));
        return inp;
    }
    
    public static String upTitle() {
        String inp;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter title: ");
            inp = sc.nextLine();
        } while(!Validation.checkUpTitle(inp));
        return inp;
    }
    
     public static float upPrice() {
        String inp;
        boolean empty = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter new price: ");
            inp = sc.nextLine();
            if (inp.isEmpty()) {
                empty = true;
                break;
            }
        } while(!Validation.checkPrice(inp));
        
        if (empty) {
            return 0;
        }
        return Float.parseFloat(inp);
    }
     
    public static String upAuthorId() {
        String inp;
        Scanner sc = new Scanner(System.in);
        System.out.println("ID format Axxx. Eg: A001");   
        do {
            System.out.print("Enter author id: ");
            inp = sc.nextLine();
        } while(!Validation.checkUpAuthorId(inp));
        return inp;
    }
     
}
