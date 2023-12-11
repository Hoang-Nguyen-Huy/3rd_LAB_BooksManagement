/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controllers.AuthorList;

/**
 *
 * @author Nguyen Huy Hoang SE180435
 */
public interface I_AuthorList {
    
    void saveAuthorIdToFile();
     
    boolean checkExist(String id);
    
    AuthorList readAuthorFromFile();
}
