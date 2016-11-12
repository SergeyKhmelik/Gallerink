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
        setEmail(user.getEmail());
        setLocation(user.getLocation());
        setAbout(user.getAbout());
        setName(user.getName());
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
