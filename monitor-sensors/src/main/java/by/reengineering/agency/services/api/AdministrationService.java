package by.reengineering.agency.services.api;

import java.util.UUID;

public interface AdministrationService<S> {
    /**
     * Method to save a new sensor
     * @param sensor new sensor
     */
    void saveSensor(S sensor);

    /**
     * Method to update saved sensor
     */
    void updateSensor(S sensor);

    /**
     * Method to delete saved sensor
     */
    void deleteSensor(UUID id);
}
