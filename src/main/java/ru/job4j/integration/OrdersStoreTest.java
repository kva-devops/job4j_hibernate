package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_001.sql"))
        )) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @After
    public void dropTable() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/drop_table_001.sql"))
        )) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrderStore store = new OrderStore(pool);
        store.save(Order.of("name1", "description1"));
        List<Order> all = (List<Order>) store.findAll();
        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenUpdateOrderAndFindAll() {
        OrderStore store = new OrderStore(pool);
        Order orderBefore = Order.of("name1", "description1");
        store.save(orderBefore);
        orderBefore.setName("name2");
        orderBefore.setDescription("description2");
        store.update(orderBefore);
        List<Order> all = (List<Order>) store.findAll();
        assertThat(all.size(), is(1));
        assertThat(all.get(0).getName(), is("name2"));
        assertThat(all.get(0).getDescription(), is("description2"));
    }

    @Test
    public void whenSaveOneOrderAndFindThemById() {
        OrderStore store = new OrderStore(pool);
        Order order = Order.of("name", "description");
        store.save(order);
        Order rsl = store.findById(1);
        assertThat(rsl.getId(), is(1));
        assertThat(rsl.getName(), is("name"));
        assertThat(rsl.getDescription(), is("description"));
    }

    @Test
    public void whenSaveOneOrderAndFindThemByName() {
        OrderStore store = new OrderStore(pool);
        Order order = Order.of("name", "description");
        store.save(order);
        Order rsl = store.findByName("name");
        assertThat(rsl.getId(), is(1));
        assertThat(rsl.getName(), is("name"));
        assertThat(rsl.getDescription(), is("description"));
    }
}
