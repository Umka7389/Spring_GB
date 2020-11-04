package GB.app;

import GB.entity.Product;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = factory.createEntityManager();

        createProduct(em, "Milk", 123.5);
        createProduct(em, "Beer", 150.0);
        createProduct(em, "Bread", 23.5);
        createProduct(em, "Butter", 250.0);
        createProduct(em, "Cheese", 300.0);


    }


    private static void createProduct(EntityManager em, String title, Double price) {

        System.out.println("Creating product");

        Product product = new Product(title, price);

        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();

        System.out.println("Creating finished");

    }

    private static <T> T readEntity(EntityManager em, long id, Class<T> tClass) {
        System.out.println("Start reading");

        em.getTransaction().begin();
        T entity = em.find(tClass, id);
        em.getTransaction().commit();

        System.out.println("Reading completed->" + entity);
        return entity;
    }

    private static <T> T saveEntity(EntityManager em, T entity) {
        System.out.println("Start saving");

        em.getTransaction().begin();
        T newEntity = em.merge(entity);
        em.getTransaction().commit();

        System.out.println("Saving completed->" + newEntity);

        return newEntity;
    }



    private static <T> void deleteEntity(EntityManager em, Class<T> entity, long id) {

        System.out.println("Deleting entity");

        em.getTransaction().begin();
        em.remove(em.find(entity, id));
        em.getTransaction().commit();

        System.out.println("Deleting finished");

    }



}
