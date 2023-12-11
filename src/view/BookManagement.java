package view;

import model.I_Menu;
import controllers.Menu;
import controllers.BookList;
import model.I_BookList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nguyen Huy Hoang SE180435
 */
public class BookManagement {

    public static void main(String args[]) {
        I_Menu menu = new Menu();
        menu.addItem("==========HKT Book Store==========");
        menu.addItem("       1. Show all books.");
        menu.addItem("       2. Add new books.");
        menu.addItem("       3. Update books.");
        menu.addItem("       4. Delete books.");
        menu.addItem("       5. Search books.");
        menu.addItem("       6. Save books to file.");
        menu.addItem("       7. Quit.");
        int choice;
        boolean cont = true;
        I_BookList list = new BookList();
        while(cont) {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    list.show();
                    break;
                case 2:
                    list.add();
                    break;
                case 3:
                    list.update();
                    break;
                case 4:
                    list.delete();
                    break;
                case 5:
                    list.search();
                    break;
                case 6:
                    list.saveToFile();
                    break;
                case 7:
                    cont = menu.confirmYesNo("Do you want to continue?(Y/N)");
                    break;
                default: 
                    System.out.println("You must enter number from 1-7");
                    break;
            }
        }
    }
}
