package by.reengineering.agency.dao.repository.api;

import java.util.List;

public interface SensorRepository<E, I> {

    void save(E entity);
    void update(E entity);
    void delete(I id);

    boolean isExist(I id);

    List<E> getAllSensors();

    List<E> findSensorsByText(String text);
}
