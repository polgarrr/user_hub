package repository.impl;

import entities.User;
import repository.UserRepository;
import services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    // local database
    private List<User> dbUsers = new ArrayList<>();

    public UserRepositoryImpl() {
    }

    @Override // возвращает список всех пользователей (из нашего приватного поля).
    public List<User> getAll() {
        return dbUsers;
    }

    @Override //возвращает пользователя из списка users по идентификатору. Если пользователь не найден, возврашать null.
    public User getBy(String id) {
        Optional<User> optionalUser = dbUsers.stream().filter(user -> user.getId().equals(id)).findFirst();
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @Override //пробежаться по списку users и если там нет пользователя с таким же id, как и в переданном пользователе в аргументе функции, то просто его добавить в список. Если такой пользователь с таким id присутствует, то просто его обновить.
    public void save(User user) {
        Optional<User> optionalUser = dbUsers.stream().filter(currentUser -> currentUser.getId().equals(user.getId())).findFirst();
        if (optionalUser.isPresent()) {
            User oldUser = optionalUser.get();
            dbUsers.remove(oldUser);
        }
        dbUsers.add(user);
    }

    @Override //все абсолютно аналогично, только со списком пользователей. И возвращает аналогично список пользователей.
    public void saveAll(List<User> users) {
        users.forEach(user -> {
            save(user);
        });
    }
}
