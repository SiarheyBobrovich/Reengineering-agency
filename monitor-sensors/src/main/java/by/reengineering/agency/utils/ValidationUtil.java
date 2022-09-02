package by.reengineering.agency.utils;

import by.reengineering.agency.dto.RangeDto;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;
import java.util.UUID;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static boolean isInvalidUuid(final UUID uuid) {
        return Objects.isNull(uuid);
    }

    public static boolean isInvalidName(final String name) {
        return Strings.isEmpty(name) || name.length() > 30;
    }

    public static boolean isInvalidModel(final String model) {
        return Strings.isEmpty(model) || model.length() > 15;
    }

    public static boolean isInvalidRange(final RangeDto range) {
        final Double fromRange = range.getFrom();
        final Double toRange = range.getTo();

        return Objects.isNull(toRange) || (!Objects.isNull(fromRange) && Double.compare(toRange, fromRange) < 1);
    }

    public static boolean isInvalidLocation(final String location) {
        return !Objects.isNull(location) && location.length() > 40;
    }

    public static boolean isInvalidDescription(String description) {
        return !Objects.isNull(description) && description.length() > 200;
    }
}
