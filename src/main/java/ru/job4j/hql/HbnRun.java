package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HbnRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
//            Candidate candidate1 = new Candidate("Petr", "some experience 1", 1000);
//            Candidate candidate2 = new Candidate("Ivan", "some experience 2", 2000);
//            Candidate candidate3 = new Candidate("Vladimir", "some experience 3", 4000);
//            session.save(candidate1);
//            session.save(candidate2);
//            session.save(candidate3);

//            all candidates
//            List<Candidate> candidateList = session.createQuery("from Candidate").list();
//            candidateList.forEach(candidate -> System.out.println(candidate.getName()));

//            candidate by ID
//            Candidate candidate = (Candidate) session.createQuery("from Candidate where id = :id")
//                    .setParameter("id", 3)
//                    .uniqueResult();
//            System.out.println(candidate.getName());

//            candidate by NAME
//            Candidate candidate = (Candidate) session.createQuery("from Candidate where name = :name")
//                    .setParameter("name", "Ivan")
//                    .uniqueResult();
//            System.out.println(candidate.getName());

//            update candidate
//            session.createQuery(
//                    "update Candidate c set c.name = :newName, c.experience = :newExp, c.salary = :newSalary where c.id = :id")
//            .setParameter("newName", "Vladimir Kutyavin")
//            .setParameter("newExp", "new experience")
//            .setParameter("newSalary", 10000)
//            .setParameter("id", 3)
//            .executeUpdate();

//            delete by ID
//            session.createQuery("delete from Candidate where id = :id")
//                    .setParameter("id", 3)
//                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
