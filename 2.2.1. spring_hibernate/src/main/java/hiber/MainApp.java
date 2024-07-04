package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User Vadim = new User("Vadim", "Ivanov", "VIvanov@mail.ru");
        User Dima = new User("Dima", "Sichov", "DimaSich@mail.ru");
        User Alina = new User("Alina", "Alieva", "AAeva@mail.ru");
        User Masha = new User("Masha", "Popova", "Popmash@mail.ru");

        Car Kia = new Car(1, "Rio");
        Car BMV = new Car(7, "Long");
        Car Mers = new Car(6, "Kaban");
        Car Lada = new Car(1, "Kopeika");

        Masha.setCar(Kia);
        userService.add(Masha);
        Vadim.setCar(Lada);
        userService.add(Vadim);
        Dima.setCar(BMV);
        userService.add(Dima);
        Alina.setCar(Mers);
        userService.add(Alina);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getCar());
        }

        try {
            System.out.println(userService.getUserByCar("Long", 7));
        } catch (NoResultException e) {
            System.out.println("Не существуют такого Юзера");
        }

        context.close();
    }
}
