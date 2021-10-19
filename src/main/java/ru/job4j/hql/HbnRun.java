package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbnRun {
    public static void main(String[] args) {

       List<Candidate> rslCandidates = new ArrayList<>();

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            rslCandidates = session.createQuery(
                    "select distinct c from Candidate c "
                    + "join  fetch c.baseVacancies b "
                    + "join fetch b.vacancyList vl ", Candidate.class
            ).list();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        rslCandidates.forEach(System.out::println);
    }
}
