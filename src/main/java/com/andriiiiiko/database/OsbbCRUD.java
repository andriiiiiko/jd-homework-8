package com.andriiiiiko.database;

import com.andriiiiiko.database.model.Apartment;
import com.andriiiiiko.database.model.Building;
import com.andriiiiiko.database.model.Person;
import com.andriiiiiko.database.model.Resident;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.andriiiiiko.database.Config.*;

/**
 * This class handles CRUD (Create, Read, Update, Delete) operations for an OSBB (Apartment Owners' Association).
 */
public class OsbbCRUD implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(OsbbCRUD.class);
    private Connection connection;
    private static final String FETCH_RESIDENTS_WITH_CAR_ENTRY_PERMISSION_QUERY = "SELECT " +
            "p.first_name, " +
            "p.last_name, " +
            "p.middle_name, " +
            "p.email, " +
            "p.phone_number, " +
            "b.section_number, " +
            "b.street, " +
            "a.number AS apartment_number, " +
            "a.area " +
            "FROM " +
            "resident r " +
            "JOIN " +
            "person p ON r.person_id = p.id " +
            "JOIN " +
            "apartment a ON r.apartment_id = a.id " +
            "JOIN " +
            "building b ON a.building_id = b.id " +
            "WHERE " +
            "r.car_entry_permission = FALSE " +
            "AND a.floor " +
            "AND (SELECT COUNT(*) FROM resident r2 WHERE r2.person_id = p.id) = 1;";

    /**
     * Connects to the database and performs Flyway migration to ensure database schema consistency.
     *
     * @return An instance of OsbbCRUD with a database connection.
     */
    public OsbbCRUD connectToDatabase() {
        try {
            flywayMigration();
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            LOG.info("Connected to the database");
        } catch (SQLException e) {
            LOG.error("Error connecting to the database", e);
        }
        return this;
    }

    /**
     * Perform database migration using Flyway to ensure database schema consistency.
     */
    private void flywayMigration() {
        LOG.info("Flyway migration execute");

        Flyway.configure()
                .dataSource(JDBC_URL, USERNAME, PASSWORD)
                .locations("classpath:flyway/scripts")
                .load()
                .migrate();

        LOG.info("Flyway migration completed");
    }

    /**
     * Fetches a list of residents without car entry permission from the database.
     *
     * @return A list of Resident objects.
     */
    private List<Resident> fetchResidentsWithCarEntryPermission() {
        List<Resident> residents = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(FETCH_RESIDENTS_WITH_CAR_ENTRY_PERMISSION_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Resident resident = new Resident(new Person(resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("middle_name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number")),
                        new Apartment(resultSet.getInt("apartment_number"),
                                resultSet.getDouble("area"),
                                new Building(resultSet.getString("section_number"),
                                        resultSet.getString("street"))));

                residents.add(resident);
            }
        } catch (SQLException e) {
            LOG.error("Error fetching residents", e);
        }

        return residents;
    }

    /**
     * Reads and displays residents without car entry permission.
     */
    public void readResidentsWithCarEntryPermission() {
        List<Resident> residents = fetchResidentsWithCarEntryPermission();

        for (Resident resident : residents) {
            System.out.println(resident.toString());
        }
    }

    /**
     * Closes the database connection when the instance is no longer needed.
     */
    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
            LOG.info("Database connection closed");
        }
    }
}
