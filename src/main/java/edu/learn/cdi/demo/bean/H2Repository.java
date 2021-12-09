package edu.learn.cdi.demo.bean;

import edu.learn.cdi.demo.domain.models.Item;
import edu.learn.cdi.demo.domain.repos.CreateDatabase;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class H2Repository {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    static final String USER = "";
    static final String PASS = "";

    @PostConstruct
    public void init() {
        loadClasses();
        initDb();
    }

    private void loadClasses() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void initDb() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement stmt = conn.prepareStatement(CreateDatabase.createDatabaseSQL());
            stmt.execute();
            stmt.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Item> getAllItems() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            List<Item> allItems = new ArrayList<>();
            String sql = "SELECT * FROM ITEMS";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                int price = rs.getInt("PRICE");
                int amount = rs.getInt("AMOUNT");
                Item item = new Item(id, name, price, amount);
                allItems.add(item);
            }
            // STEP 5: Clean-up environment
            rs.close();

            return allItems;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    public void create(Item item) {
        String sql = "INSERT INTO ITEMS (ID,NAME,PRICE,AMOUNT) VALUES (?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, item.getId());
            statement.setString(2, item.getName());
            statement.setInt(3, item.getPrice());
            statement.setInt(4, item.getAmount());

            statement.executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void update(Item item) {
        String sql = "UPDATE ITEMS set NAME=?, PRICE=?, AMOUNT=? where ID =?";
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, item.getAmount());
            statement.setInt(4, item.getId());
            statement.executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void delete(Item item) {
        String sql = "delete from ITEMS where ID =?";
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, item.getId());
            statement.executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
