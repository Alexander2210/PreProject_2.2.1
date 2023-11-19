package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Alexey", "Vatin", "vatalex@mail.ru");
        User user2 = new User("Dmitriy", "Ivanov", "divanov@yandex.ru");
        User user3 = new User("Ivan", "Sokolov", "ivsokol@gmail.com");
        User user4 = new User("Maria", "Bunina", "buninamar@mail.ru");

        Car volvo = new Car("Volvo S", 60);
        Car bmw = new Car("BMW", 5);
        Car suzuki = new Car("Chery Tiggo", 4);
        Car lada = new Car("Lada", 21015);

        user1.setCar(volvo);
        user2.setCar(bmw);
        user3.setCar(suzuki);
        user4.setCar(lada);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(user.getCar());
            System.out.println();
        }

        userService.getUserByCar("BMW", 5);

        userService.getUserByCar("Mazda", 9);

        context.close();
    }
}
