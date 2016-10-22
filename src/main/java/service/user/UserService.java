package service.user;

import domain.user.User;

public interface UserService {

    User save(User user);

    void delete(User user);

}
