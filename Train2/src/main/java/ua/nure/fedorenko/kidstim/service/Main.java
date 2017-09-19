package ua.nure.fedorenko.kidstim.service;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ua.nure.fedorenko.kidstim.model.entity.*;


import java.util.*;

public class Main {
    public static void main(String[] args) {

        Session session = buildSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Parent parent=new Parent("nastia.federenko@gmail.com","123456","Anastasia","Fedorenko");
            List<Child>children=new ArrayList<>();
            Child child1=new Child("ana@nure.ua","123456","Ana","F",new Date(1997,1,12),1,"ggg.png",0);
            Child child2=new Child("anga@nure.ua","12345g6","Affna","F",new Date(1997,1,12),1,"gffgg.png",5);
            children.add(child1);
            children.add(child2);

            parent.setChildren(children);
            session.save(parent);
            Reward reward=new Reward("To go to the zoo",45, RewardStatus.RECEIVED,parent);
            reward.setChildren(children);
            session.save(reward);



            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

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
