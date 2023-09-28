/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author ASUS
 */
public class DepartmentTable {

    public static void insertDepartment(Department dep) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DepartmentPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(dep);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public static void updateDepartment(Department dep) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DepartmentPU");
        EntityManager em = emf.createEntityManager();
        Department fromDb = em.find(Department.class, dep.getDepartmentid());
        fromDb.setName(dep.getName());
        em.getTransaction().begin();
        try {
            em.persist(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public static Department findDepartmentById(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DepartmentPU");
        EntityManager em = emf.createEntityManager();
        Department dep = em.find(Department.class, id);
        em.close();
        return dep;
    }
    public static List<Department> findAllDepartment() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DepartmentPU");
        EntityManager em = emf.createEntityManager();
        List<Department> depList = (List<Department>) em.createNamedQuery("Department.findAll").getResultList();
        em.close();
        return depList;
    }
    public static Department findDepartmentByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DepartmentPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Department.findByName");
        query.setParameter("name", name);
        Department dep = (Department) query.getSingleResult();
        em.close();
        return dep;
    }
    public static void removeDepartment(Department dep) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DepartmentPU");
        EntityManager em = emf.createEntityManager();
        Department fromDb = em.find(Department.class, dep.getDepartmentid());
        em.getTransaction().begin();
        try {
            em.remove(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
