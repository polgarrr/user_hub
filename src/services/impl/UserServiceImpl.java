package services.impl;

import entities.User;
import entities.UserStatus;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static entities.UserStatus.ACTIVE;
import static entities.UserStatus.DELETED;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //реализовывать поиск по пользователям, а именно: по ФИО, телефону и емейлу.
    //причем ФИО приходит с фронта цельной строкой, то есть, например, "Иванов Иван Иванович"
    // Метод должен выводить только пользователей в статусе ACTIVE.

    @Override
    public List<User> getUsers(String firstName, String lastName, String middleName, String phone, String email) {
        List<User> users = userRepository.getAll();
        System.out.println("All users: " + users);
        List<User> filteredUsers = users.stream().filter(user ->
                        user.getFirstName().equals(firstName) &&
                        user.getLastName().equals(lastName) &&
                        user.getMiddleName().equals(middleName) &&
                        user.getPhone().equals(phone) &&
                        user.getEmail().equals(email) &&
                        user.getStatus().equals(ACTIVE)
        ).collect(Collectors.toList());
        return filteredUsers;
    }

    //принимает на вход список идентификаторов пользователей и выставляет найденным пользователям статус DELETED
    //затем вызывает метод saveAll у userRepository

    @Override
    public void deleteUsers(List<User> users) {
        users.forEach(user -> user.setStatus(DELETED));
        userRepository.saveAll(users);
    }

    @Override
    public User updateUser(String id, String firstName, String lastName, String middleName, String phone, String email) {
        User user = userRepository.getBy(id);// get user from repo
        if (user.getStatus().equals(DELETED)){
//            System.out.println("Can't update user with id " + id + " ,because it's status is DELETED");
            // if deleted - print message and return
            throw new RuntimeException("Can't update user with id " + id + " ,because it's status is DELETED");
        }
        // else - update user params by arguments
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMiddleName(middleName);
        user.setPhone(phone);
        user.setEmail(email);
        // save user in repo
        userRepository.save(user);
        return user;
    }

    @Override
    public User createUser(String firstName, String lastName, String middleName, String phone, String email) {
        User user = new User(UUID.randomUUID().toString(), firstName, lastName, middleName, phone, email, ACTIVE);
        userRepository.save(user);
        return user;
    }
}
