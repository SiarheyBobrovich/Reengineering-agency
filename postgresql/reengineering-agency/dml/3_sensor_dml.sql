\connect monitor

INSERT INTO sensor.types(type)
    VALUES
        ('Pressure'),
        ('Voltage'),
        ('Temperature'),
        ('Humidity');

INSERT INTO sensor.units(unit)
    VALUES
        ('bar'),
        ('voltage'),
        ('°C'),
        ('%');
