package by.reengineering.agency.controllers;

import by.reengineering.agency.controllers.api.ViewerAPI;
import by.reengineering.agency.dto.ResponseDto;
import by.reengineering.agency.entity.Sensor;
import by.reengineering.agency.services.api.ViewerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Tag(name = "Views")
public class ViewerController implements ViewerAPI {

    private final ViewerService viewerService;

    public ViewerController(ViewerService viewerService) {
        this.viewerService = viewerService;
    }

    @Override
    public String apiV1UserSensorsViewAllGet(final Model model) {
        final List<Sensor> allSensors = viewerService.getAllSensors();
        List<ResponseDto> responseSensors = convertToResponse(allSensors);

        model.addAttribute("sensors", responseSensors);

        return "all";
    }

    @Override
    public String apiV1UserSensorsViewGet(final Model model, String text) {
        List<Sensor> sensorsByText = viewerService.findSensorsByText(text);
        List<ResponseDto> responseSensors = convertToResponse(sensorsByText);

        model.addAttribute("sensors", responseSensors);

        return "all";
    }

    private List<ResponseDto> convertToResponse(List<Sensor> sensors) {
        return sensors.stream()
                .map(x -> new ResponseDto(x.getUuid(), x.getName(), x.getModel()))
                .collect(Collectors.toList());
    }
}