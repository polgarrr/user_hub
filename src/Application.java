import entities.User;
import repository.impl.UserRepositoryImpl;
import services.UserService;
import services.impl.UserServiceImpl;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl(new UserRepositoryImpl());
        List<User> users = userService.getUsers("fN", "lN", "mN", "77", "e");
        System.out.println(users);
        User user = userService.createUser("fN", "lN", "mN", "77", "e");
        System.out.println(user);
        List<User> users1 = userService.getUsers("fN", "lN", "mN", "77", "e");
        System.out.println(users1);
        User user1 = userService.updateUser(users1.get(0).getId(), "fN", "lN", "mN", "77", "e");
        System.out.println(user1);
        userService.deleteUsers(Arrays.asList(user1));
        List<User> users2 = userService.getUsers("fN", "lN", "mN", "77", "e");
        System.out.println(users2);

        System.out.println("-----------");

        User user2 = userService.createUser("fN", "lN", "mN", "77", "e");
        User user3 = userService.createUser("fN", "lN", "mN", "77", "e");
        List<User> users3 = userService.getUsers("fN", "lN", "mN", "77", "e");
        System.out.println(users3);
        userService.deleteUsers(Arrays.asList(user2, user3));
        List<User> users4 = userService.getUsers("fN", "lN", "mN", "77", "e");
        System.out.println(users4);
    }
}
