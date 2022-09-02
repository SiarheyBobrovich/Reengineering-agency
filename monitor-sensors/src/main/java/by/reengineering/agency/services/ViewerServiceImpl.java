package by.reengineering.agency.services;

import by.reengineering.agency.dao.repository.api.SensorRepository;
import by.reengineering.agency.entity.Sensor;
import by.reengineering.agency.services.api.ViewerService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Primary
public class ViewerServiceImpl implements ViewerService {

    private final SensorRepository<Sensor, UUID> repository;

    public ViewerServiceImpl(SensorRepository<Sensor, UUID> repository) {
        this.repository = repository;
    }

    @Override
    public List<Sensor> getAllSensors() {
        return repository.getAllSensors();
    }

    @Override
    public List<Sensor> findSensorsByText(String text) {
        return repository.findSensorsByText(text);
    }
}
