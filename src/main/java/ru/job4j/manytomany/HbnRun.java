package ru.job4j.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbnRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book book1 = Book.of("Война и Мир");
            Book book2 = Book.of("Евгений Онегин");
            Book book3 = Book.of("Бородино");
            Book unrealBook = Book.of("Выдуманная книга");

            Author author1 = Author.of("Пушкин");
            Author author2 = Author.of("Толстой");
            Author author3 = Author.of("Лермонтов");
            Author deleteAuthor = Author.of("Удаляемый автор");

            author1.getBookList().add(book2);
            author1.getBookList().add(unrealBook);
            author2.getBookList().add(book1);
            author3.getBookList().add(book3);
            author3.getBookList().add(unrealBook);
            deleteAuthor.getBookList().add(unrealBook);

            session.persist(author1);
            session.persist(author2);
            session.persist(author3);
            session.persist(deleteAuthor);
            session.persist(book1);
            session.persist(book2);
            session.persist(book3);
            session.persist(unrealBook);

            Author author = session.get(Author.class, 4);
            session.remove(author);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
