package dto.user;

import domain.user.User;
import dto.IDto;
import dto.IValidated;

public class _User extends _SimpleUser {

    private String email;

    private String password;

    public _User() {
    }

    public _User(User user) {
        super(user);
        setEmail(user.getEmail());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
