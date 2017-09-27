package ua.nure.fedorenko.kidstim.service;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.nure.fedorenko.kidstim.model.entity.Child;
import ua.nure.fedorenko.kidstim.model.entity.Parent;
import ua.nure.fedorenko.kidstim.model.entity.Reward;
import ua.nure.fedorenko.kidstim.model.entity.Task;

public class Main {
    public static void main(String[] args) {

       /* String email = "lena@nure.ua";

        SessionFactory sessionFactory = buildSessionFactory();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();


            Query query = session.createQuery("FROM Child c where email=:email");
            query.setParameter("email", email);
            Child child = (Child) query.getResultList().get(0);
            System.out.println(child);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }*/
       String pass="123456";
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        System.out.println(encoder.encode(pass));
    }

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Child.class);
            configuration.addAnnotatedClass(Parent.class);

            configuration.addAnnotatedClass(Task.class);
            configuration.addAnnotatedClass(Reward.class);
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            return configuration.buildSessionFactory(ssrb.build());

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
