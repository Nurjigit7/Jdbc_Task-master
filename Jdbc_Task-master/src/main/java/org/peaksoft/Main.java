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
        CarServiceImpl carService=new CarServiceImpl();
//        carService.createTable();
//        carService.save(new Car("toyota",LocalDate.of(2020,10,14),"Black"));
    UserServiceImpl userService=new UserServiceImpl();
    userService.createTable();
    userService.save(new User("Nurjigit","Umarov", (byte) 22,1L));

    }
}
