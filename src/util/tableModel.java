/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sir Parish
 */
public class tableModel extends DefaultTableModel{
    public tableModel(int r, int c){
        super();
    }
 /*@override*/ 
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }
}
