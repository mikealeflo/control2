/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlHorario;

import java.util.ArrayList;

/**
 *
 * @author Miguel
 */
public class CHMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int i;
        int numEmployees=5;
        ArrayList<Employee> employees=new ArrayList<Employee>(numEmployees);
        
        employees.add(new Employee("rosi", "111", "ROSA DELIA PEREZ RAMIREZ"));
        employees.add(new Employee("carmen", "222", "MARIA DEL CARMEN IGLESIAS ALVAREZ"));
        employees.add(new Employee("isabel", "333", "ISABEL GUEDES GUEDES"));
        employees.add(new Employee("javier", "444", "JAVIER ARROYO GAMALLO"));
        employees.add(new Employee("manuel", "555", "MANUEL MOYA YANES"));
        
        List[] list=new List[numEmployees];
        CHFile[] faefile=new CHFile[numEmployees];
        for(i=0;i<numEmployees;i++){
            list[i]=new List();
            faefile[i]=new CHFile(i, employees);
            faefile[i].loadFromFile(list[i]);
        }
        
        
        //faefile[0].escribir_resumen(list[0]);
        CHFrame form=new CHFrame();
        form.assign(list, faefile, numEmployees, employees);
        form.setVisible(true);
    }
    
}
