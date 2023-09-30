package org.peaksoft;

import org.peaksoft.model.Car;
import org.peaksoft.model.User;
import org.peaksoft.service.CarServiceImpl;
import org.peaksoft.service.UserServiceImpl;
import org.peaksoft.util.Util;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        // TODO: 27.09.2023   реализуйте алгоритм здесь
        Util.connection().close();
        UserServiceImpl userService = new UserServiceImpl();
        userService.createTable();
        userService.save(new User("name", "last_name", (byte) 3, 1L));
        System.out.println(userService.getAll());
        userService.createTable();
        CarServiceImpl carService = new CarServiceImpl();
        carService.createTable();
        Car car1 = new Car("fit", LocalDate.of(2013, 10, 15), "green");
        carService.save(car1);
        carService.removeById(1);
        System.out.println(carService.getById(1));


    }
}
