package ru.job4j.many;

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

            CarModel cherokee = CarModel.of("Cherokee");
            session.save(cherokee);
            CarModel wrangler = CarModel.of("Wrangler");
            session.save(wrangler);
            CarModel compass = CarModel.of("Compass");
            session.save(compass);
            CarModel liberty = CarModel.of("Liberty");
            session.save(liberty);
            CarModel renegade = CarModel.of("Renegade");
            session.save(renegade);

            CarBrand jeep = CarBrand.of("JEEP");
            jeep.addModel(session.load(CarModel.class, 1));
            jeep.addModel(session.load(CarModel.class, 2));
            jeep.addModel(session.load(CarModel.class, 3));
            jeep.addModel(session.load(CarModel.class, 4));
            jeep.addModel(session.load(CarModel.class, 5));
            session.save(jeep);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
