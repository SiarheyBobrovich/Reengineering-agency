package by.reengineering.agency.services;

import by.reengineering.agency.dao.repository.api.SensorRepository;
import by.reengineering.agency.dao.repository.exceptions.NotFoundException;
import by.reengineering.agency.entity.Sensor;
import by.reengineering.agency.services.api.AdministrationService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdministrationServiceImpl implements AdministrationService<Sensor> {

    private final SensorRepository<Sensor, UUID> repository;

    public AdministrationServiceImpl(SensorRepository<Sensor, UUID> repository) {
        this.repository = repository;
    }

    @Override
    public void saveSensor(final Sensor sensor) {
        sensor.setUuid(UUID.randomUUID());

        repository.save(sensor);
    }

    @Override
    public void updateSensor(Sensor sensor) {
        if (!repository.isExist(sensor.getUuid())) {
            throw new NotFoundException("sensor does not exist");
        }

        repository.update(sensor);
    }

    @Override
    public void deleteSensor(UUID uuid) {
        repository.delete(uuid);
    }
}


