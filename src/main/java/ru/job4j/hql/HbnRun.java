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

//            BaseVacancies base1 = new BaseVacancies("front-end");
//            BaseVacancies base2 = new BaseVacancies("back-end");
//            session.save(base1);
//            session.save(base2);

//            Vacancy vacancy1 = new Vacancy(
//                    "JS developer",
//                    "Description vacancy JS developer",
//                    1000,
//                    "+79088658985");
//            Vacancy vacancy2 = new Vacancy(
//                    "html and CSS",
//                    "Description vacancy html and css",
//                    500,
//                    "+780022211144");
//            Vacancy vacancy3 = new Vacancy(
//                    "Java developer",
//                    "Description vacancy Java developer",
//                    2000,
//                    "+79052566586");
//            Vacancy vacancy4 = new Vacancy(
//                    "Python developer",
//                    "Description vacancy Python developer",
//                    1800,
//                    "+79828585658");
//            session.save(vacancy1);
//            session.save(vacancy2);
//            session.save(vacancy3);
//            session.save(vacancy4);

//            BaseVacancies base1 = (BaseVacancies) session.createQuery("from BaseVacancies where id = 1").uniqueResult();
//            Vacancy vacancy1 = (Vacancy) session.createQuery("from Vacancy where id = 1").uniqueResult();
//            Vacancy vacancy2 = (Vacancy) session.createQuery("from Vacancy where id = 2").uniqueResult();
//            base1.addVacancy(vacancy1);
//            base1.addVacancy(vacancy2);
//            session.save(base1);

//            BaseVacancies base2 = (BaseVacancies) session.createQuery("from BaseVacancies where id = 2").uniqueResult();
//            Vacancy vacancy3 = (Vacancy) session.createQuery("from Vacancy where id = 3").uniqueResult();
//            Vacancy vacancy4 = (Vacancy) session.createQuery("from Vacancy where id = 4").uniqueResult();
//            base2.addVacancy(vacancy3);
//            base2.addVacancy(vacancy4);
//            session.save(base2);

//            Candidate candidate1 = new Candidate("Petr", "some experience 1", 1000);
//            Candidate candidate2 = new Candidate("Ivan", "some experience 2", 2000);
//            Candidate candidate3 = new Candidate("Vladimir", "some experience 3", 4000);
//            session.save(candidate1);
//            session.save(candidate2);
//            session.save(candidate3);

//            Candidate candidate1 = (Candidate) session.createQuery("from Candidate where id = 1").uniqueResult();
//            Candidate candidate2 = (Candidate) session.createQuery("from Candidate where id = 2").uniqueResult();
//            Candidate candidate3 = (Candidate) session.createQuery("from Candidate where id = 3").uniqueResult();
//            BaseVacancies base1 = (BaseVacancies) session.createQuery("from BaseVacancies where id = 1").uniqueResult();
//            BaseVacancies base2 = (BaseVacancies) session.createQuery("from BaseVacancies where id = 2").uniqueResult();
//            candidate1.setBaseVacancies(base1);
//            candidate2.setBaseVacancies(base1);
//            candidate3.setBaseVacancies(base2);

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
