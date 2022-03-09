package services;

import entities.User;

import java.util.List;

public interface UserService {
    List<User> getUsers(String firstName, String lastName, String middleName, String phone, String email);
    void deleteUsers(List<User> users);
    User updateUser(String id, String firstName, String lastName, String middleName, String phone, String email);
    User createUser(String firstName, String lastName, String middleName, String phone, String email);
}
