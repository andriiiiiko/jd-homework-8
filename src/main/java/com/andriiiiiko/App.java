package com.andriiiiiko;

import com.andriiiiiko.database.OsbbCRUD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class represents the main application entry point.
 */
public class App {

    private static final Logger LOG = LogManager.getLogger(App.class);

    /**
     * The main method of the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        LOG.info("The program has started");

        try (OsbbCRUD osbbCRUD = new OsbbCRUD().connectToDatabase()) {
            osbbCRUD.readResidentsWithCarEntryPermission();
            LOG.info("Residents with car entry permission have been retrieved successfully");
        } catch (Exception e) {
            LOG.error("An error occurred while processing residents with car entry permission", e);
        }

        LOG.info("The program has finished");
    }
}
