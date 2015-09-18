/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classicmodels;

import entity.Customer;
import entity.Employee;
import entity.Office;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.Order;

/**
 *
 * @author Pernille
 */
public class Facade
{
    
     EntityManagerFactory emf;
     
     public Facade(EntityManagerFactory emf) 
     {    
         this.emf = emf;  
     }  
     EntityManager getEntityManager()
     {      
         return emf.createEntityManager();  
     } 
    
    public Employee createEmployee(Employee e) //Creates and return a new Employee2  
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("classicmodelsPU");
        EntityManager em = getEntityManager(); 
        try{
            em.getTransaction().begin();
            Office o = em.find(Office.class,"1");
            e.setOffice(o);
            em.persist(e);
            em.getTransaction().commit();
            
            }finally
        {  
            em.close(); 
        }
        return e;
    }
    public Customer findCustomer(int customerNumber)
    {
        EntityManager em = getEntityManager(); 
        try{
            return em.find(Customer.class, customerNumber);
            }finally
        {  
            em.close(); 
        }
        
    }
    public Customer updateCustomer(Customer cust) //Updates and returns the updated Customer 
    {
        EntityManager em = getEntityManager(); 
        try{
            em.getTransaction().begin();
            em.merge(cust);
            em.getTransaction().commit();
            }finally
        {  
            em.close(); 
        }
        return cust;
    }
    public long getEmployeCount()  // return total employees 
            {
        EntityManager em = getEntityManager(); 
        try{
            long i = (long) em.createQuery("SELECT COUNT(e) from Employee e").getSingleResult();
            return i;
            }finally
        {  
            em.close(); 
        }
    }
    public List<Customer>  getCustomerInCity(String city) // Return all customers living in a given city (Barcelona = 1) 
            {
        EntityManager em = getEntityManager(); 
        try{
            return em.createNamedQuery("Customer.findByCity").setParameter("city", city).getResultList();
            
            }finally
        {  
            em.close(); 
        }
        
    }
    public List<Employee> getEmployeMaxCustomers() // Return the employees with most customers 
            {
        EntityManager em = getEntityManager(); 
        try{
             List<Employee> emp = em.createQuery("SELECT e FROM Employee e WHERE(size(e.customerCollection)) = SELECT MAX(size(ee.customerCollection)) FROM Employee ee").getResultList();
            return emp;
            }finally
        {  
            em.close(); 
        }
        
    }
    public List<Order>getOrdersOnHold() // Return all orders where status is "On Hold"   
            {
        EntityManager em = getEntityManager(); 
        try{
            return em.createNamedQuery("ClassicOrder.findByStatus").setParameter("status", "On Hold").getResultList();
            }finally
        {  
            em.close(); 
        }
    }
    public List<Order>getOrdersOnHold(int customerNumber) // Return all orders on hold for a given customer (try Customer 144) 
            {
        EntityManager em = getEntityManager(); 
        try{
            List<Order> query = em.createQuery("SELECT o FROM ClassicOrder o WHERE o.customer.customerNumber = :customerNumber AND o.status = :status ").setParameter("customerNumber", customerNumber).setParameter("status", "On Hold").getResultList();
            return query;
            }finally
        {  
            em.close(); 
        }
    }
   
}
