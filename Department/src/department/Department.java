/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.DepartmentTable;
import model.Employee;
import model.EmployeeTable;

/**
 *
 * @author User
 */
public class Department {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        model.Department dep1 = new model.Department();
        dep1.setName("IT");
        model.Department dep2 = new model.Department();
        dep1.setName("HR");
        DepartmentTable.insertDepartment(dep1);
        
        DepartmentTable.insertDepartment(dep2);
        Employee emp1 = new Employee(1,"John","Network Admin",56789,dep1);
        EmployeeTable.insertEmployee(emp1);
        emp1 = new Employee(2,"Marry","HR Manager",46789,dep2);
        EmployeeTable.insertEmployee(emp1);
        emp1 = new Employee(3,"Henry","Programmer",67890,dep1);
        EmployeeTable.insertEmployee(emp1);
        emp1 = new Employee(4,"Clark","HR recruiter",36789,dep2);
        EmployeeTable.insertEmployee(emp1);
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DepartmentPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
