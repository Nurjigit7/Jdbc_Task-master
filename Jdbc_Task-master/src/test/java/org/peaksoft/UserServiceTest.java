package org.peaksoft;


import org.junit.Assert;
import org.junit.Test;
import org.peaksoft.model.Car;
import org.peaksoft.model.User;
import org.peaksoft.service.CarServiceImpl;
import org.peaksoft.service.Service;
import org.peaksoft.service.UserServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class UserServiceTest {

    private final Service<User> userService = new UserServiceImpl();
    private final Service<Car> carService = new CarServiceImpl();
    private final User testUser = new User("Tom", "Hardy", (byte) 46, 1L);
    private final Car testCar = new Car("BMW", LocalDate.of(2020, 10, 20), "Black");

    @Test
    public void dropUsersTable() {
        try {
            userService.dropTable();
            userService.dropTable();
        } catch (Exception e) {
            Assert.fail("При тестировании удаления таблицы произошло исключение\n" + e);
        }
    }

    @Test
    public void createUsersTable() {
        try {
            userService.dropTable();
            carService.createTable();
            userService.createTable();
        } catch (Exception e) {
            Assert.fail("При тестировании создания таблицы пользователей произошло исключение\n" + e.getMessage());
        }
    }

    @Test
    public void saveUser() {
        try {
            userService.dropTable();
            userService.createTable();
            carService.save(testCar);
            userService.save(testUser);
            User user = userService.getAll().get(0);

            if (!testUser.getName().equals(user.getName())
                    || !testUser.getLastName().equals(user.getLastName())
                    || testUser.getAge() != user.getAge()) {
                Assert.fail("User был некорректно добавлен в базу данных");
            }

        } catch (Exception e) {
            Assert.fail("Во время тестирования сохранения пользователя произошло исключение\n" + e);
        }
    }

    @Test
    public void removeUserById() {
        try {
            userService.dropTable();
            userService.createTable();
            userService.save(testUser);
            userService.removeById(1L);
        } catch (Exception e) {
            Assert.fail("При тестировании удаления пользователя по id произошло исключение\n" + e);
        }
    }

    @Test
    public void getAllUsers() {
        try {
            userService.dropTable();
            userService.createTable();
            userService.save(testUser);
            List<User> userList = userService.getAll();

            if (userList.size() != 1) {
                Assert.fail("Проверьте корректность работы метода сохранения пользователя/удаления или создания таблицы");
            }
        } catch (Exception e) {
            Assert.fail("При попытке достать всех пользователей из базы данных произошло исключение\n" + e);
        }
    }

    @Test
    public void cleanUsersTable() {
        try {
            userService.dropTable();
            userService.createTable();
            userService.save(testUser);
            userService.cleanTable();

            if (userService.getAll().size() != 0) {
                Assert.fail("Метод очищения таблицы пользователей реализован не корректно");
            }
        } catch (Exception e) {
            Assert.fail("При тестировании очистки таблицы пользователей произошло исключение\n" + e);
        }
    }

    @Test
    public void getUserById() {
        try {
            userService.dropTable();
            userService.createTable();
            userService.save(testUser);
            User user = userService.getById(1L);
            if (!testUser.getName().equals(user.getName())
                    || !testUser.getLastName().equals(user.getLastName())
                    || testUser.getAge() != user.getAge()) {
                Assert.fail("Вышло ошибка");
            }

        } catch (Exception e) {
            Assert.fail("Во время тестирования сохранения пользователя произошло исключение\n" + e);
        }
    }
}
