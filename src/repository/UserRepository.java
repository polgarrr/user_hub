package repository;
import entities.User;
import java.util.List;

public interface UserRepository {
    List<User> getAll();
    User getBy(String id);
    void save(User user);
    void saveAll(List<User> users);
}

