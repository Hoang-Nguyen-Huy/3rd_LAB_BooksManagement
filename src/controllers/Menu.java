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
import java.util.ArrayList;
import model.I_Menu;
import utils.Utils;

public class Menu extends ArrayList<String> implements I_Menu {

    public Menu() {
        super();
    }
    // must implement all abstract method of I_Menu interface

    @Override
    public void addItem(String s) {
        this.add(s);
    }

    @Override
    public void showMenu() {
        for (String s : this) {
            System.out.println(s);
        }
    }

    @Override
    public boolean confirmYesNo(String welcome) {
        return Utils.confirmYesNo(welcome);
    }

    @Override
    public int getChoice() {
        int choices = Utils.getInt();
        return choices;
    }

}
