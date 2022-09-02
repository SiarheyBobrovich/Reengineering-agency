package by.reengineering.agency.services.api;

import by.reengineering.agency.entity.Sensor;

import java.util.List;

public interface ViewerService {

    /**
     * Get all sensors stored in the repository
     * @return List of stored sensors
     */
    List<Sensor> getAllSensors();

    /**
     * Find sensors matching text from the repository
     * @return List of found sensors
     */
    List<Sensor> findSensorsByText(String text);
}
