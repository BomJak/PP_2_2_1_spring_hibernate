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

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        User newUser = new User("carUser1", "carULN1", "1@1.ru");
        newUser.setCar(new Car("Bmw", 1488));
        userService.add(newUser);

        newUser = new User("carUser2", "carULN2", "2@2.ru");
        newUser.setCar(new Car("Audio", 1823));
        userService.add(newUser);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        List<User> carAudios = userService.getUserWithCarModelSeries("Audio", 1823);

        for (User carAudio : carAudios) {
            System.out.println("Id = " + carAudio.getId());
            System.out.println("First Name = " + carAudio.getFirstName());
            System.out.println("Last Name = " + carAudio.getLastName());
            System.out.println("Email = " + carAudio.getEmail());
            if (carAudio.getCar() != null) {
                System.out.println("Car model = " + carAudio.getCar().getModel());
                System.out.println("Car series = " + carAudio.getCar().getSeries());
            }
            System.out.println();
        }

        context.close();
    }
}
