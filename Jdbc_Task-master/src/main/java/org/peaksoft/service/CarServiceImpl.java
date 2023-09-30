package org.peaksoft.service;


import org.peaksoft.model.Car;
import org.peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements Service<Car> {


    @Override
    public void createTable() {
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE TABLE cars(" +
                    "id SERIAL PRIMARY KEY," +
                    "model VARCHAR(100)NOT NULL, " +
                    "year_of_release DATE," +
                    "color VARCHAR (100)NOT NULL)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropTable() {
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS cars");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void save(Car car) {
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO cars(model,year_of_release,color)" +
                             "VALUES (?,?,?)")) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setDate(2, Date.valueOf(car.getYearOfRelease()));
            preparedStatement.setString(3, car.getColor());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeById(long id) {
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM cars WHERE id=?")) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getAll() {
        List<Car> carList = new ArrayList<>();
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT  * FROM cars")) {
            while (resultSet.next()) {
                Car car = new Car();
                car.setModel(resultSet.getString("model"));
                car.setYearOfRelease(resultSet.getDate("yearOfRelease").toLocalDate());
                car.setColor(resultSet.getString("color"));
                carList.add(car);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return carList;
    }

    public void cleanTable() {
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            String cleanTable = "TRUNCATE TABLE cars";
            statement.executeUpdate(cleanTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Car getById(long id) {
        Car car = new Car();
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT  * FROM cars WHERE id=?) ")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                car.setModel(resultSet.getString("model"));
                car.setYearOfRelease(resultSet.getDate(2).toLocalDate());
                car.setColor(resultSet.getString("color"));

            }
        } catch (SQLException x) {
            throw new RuntimeException(x);
        }
        return car;
    }
}
