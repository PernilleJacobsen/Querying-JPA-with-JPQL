/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classicmodels;

import entity.Customer;
import entity.Employee;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Pernille
 */
public class Classicmodels
{
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("classicmodelsPU");
//       EntityManager em = emf.createEntityManager();
       Facade f = new Facade(emf);
       //
//       Employee e1 = new Employee(1716, "Jacobsen", "Pernille", "ext", "jjjj@mail.dk","Student");
//       Employee e = f.createEmployee(e1);
//       System.out.println(""+e.getEmployeeNumber());
       
       Customer cust = f.findCustomer(103);
       cust.setCity("Hvidovre");
       f.updateCustomer(cust);
       System.out.println("Employee count er: "+ f.getEmployeCount());
       System.out.println("Barcelona:  "+f.getCustomerInCity("Barcelona"));
       System.out.println(f.getEmployeMaxCustomers());
        
        System.out.println(f.getOrdersOnHold().toString());
        
        System.out.println(f.getOrdersOnHold(144).toString());
       
       
    }
    
}
