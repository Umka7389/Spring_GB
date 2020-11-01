package service;

import org.hibernate.cfg.Configuration;
import repository.Client;
import repository.Product;
import repository.Purchase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = factory.createEntityManager();

        createClient(em, "Vasiliy", "Vasiliev");
        createClient(em, "Ivan", "Ivanov");
        createClient(em, "Sergey", "Sergeev");
        createClient(em, "Fedor", "Fedorov");


        createProduct(em, "Milk", 123.5);
        createProduct(em, "Beer", 150.0);
        createProduct(em, "Bread", 23.5);
        createProduct(em, "Butter", 250.0);
        createProduct(em, "Cheese", 300.0);

        readEntity(em, 2, Client.class);

        purchase(em, 2, 2);
        purchase(em, 4, 2);
        purchase(em, 3, 2);
        purchase(em, 1, 2);
        purchase(em, 3, 1);


        findClientsPurchaseById(em, 1);
        deleteEntity(em,Client.class, 1);
        deleteProduct(em, 4);
        findPurchasedProducts(em, 3);

        purchaseWithNewEntity(em,1,3,2,new Date());

    }

    private static void createClient(EntityManager em, String firstName, String lastName) {

        System.out.println("Creating client");

        Client client = new Client();

        client.setFirstName(firstName);
        client.setLastName(lastName);
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();

        System.out.println("Creating finished");

    }

    private static void createProduct(EntityManager em, String title, Double price) {

        System.out.println("Creating product");

        Product product = new Product();

        product.setTitle(title);
        product.setPrice(price);
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

    private static void purchase(EntityManager em, long productId, long clientId) {
        System.out.println("Start purchase");

        em.getTransaction().begin();
        Product newProduct = em.find(Product.class, productId);
        Client client = em.find(Client.class, clientId);
        List<Product> productList;
        if (client.getProducts() == null) {
            productList = new ArrayList<>();
        } else {
            productList = client.getProducts();
        }
        productList.add(newProduct);
        client.setProducts(productList);
        em.merge(client);
        em.getTransaction().commit();

        System.out.println("Saving completed->" + client.getProducts());
    }

    private static void findClientsPurchaseById(EntityManager em, long id) {
        Client client = em.find(Client.class, id);
        System.out.println(client.getLastName() + " " + client.getFirstName() + " buy next products:");
        List<Product> productList = client.getProducts();
        for (Product prod : productList) {
            System.out.println(prod.getTitle());
        }
        System.out.println("End of the list");
    }

    private static void findPurchasedProducts(EntityManager em, long id) {
        List<Client> clientList = em.createQuery("select client from Client client inner JOIN client.products p where p.id = :id", Client.class)
                .setParameter("id",id)
                .getResultList();
        Product product = em.find(Product.class, id);
        System.out.println(product.getTitle() + " " + " buy next clients:");

        for (Client client : clientList) {
            System.out.println(client.getLastName() + " " + client.getFirstName());
        }
        System.out.println("End of the list");
    }

    private static <T> void deleteEntity(EntityManager em, Class<T> entity, long id) {

        System.out.println("Deleting entity");

        em.getTransaction().begin();
        em.remove(em.find(entity, id));
        em.getTransaction().commit();

        System.out.println("Deleting finished");

    }

    private static void deleteProduct (EntityManager em, long id) {
        em.getTransaction().begin();
        System.out.println("Deleting entity");
        em.createNativeQuery("DELETE FROM clients_products WHERE product_id = :id").setParameter("id",id).executeUpdate();
        Product product = em.find(Product.class, id);
        em.remove(product);
        em.getTransaction().commit();

        System.out.println("Deleting finished");

    }

    private static void purchaseWithNewEntity (EntityManager em, long productId, long clientId, long amount, Date date) {
        System.out.println("Start purchase");

        em.getTransaction().begin();
        Product product = em.find(Product.class, productId);
        Client client = em.find(Client.class, clientId);
        Purchase purchase = new Purchase();
        purchase.setClient(client);
        purchase.setProduct(product);
        purchase.setAmount(amount);
        purchase.setDate(date);

        em.persist(purchase);
        em.getTransaction().commit();

        System.out.println("Saving completed->" + purchase);
    }

}
