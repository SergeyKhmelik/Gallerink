package dto.user;

import domain.user.User;
import dto.IDto;
import dto.IValidated;

public class _User implements IDto, IValidated {

    private String username;

    private String name;

    private String email;

    private String password;

    private String location;

    private String about;

    public _User() {
    }

    public _User(User user) {
        setEmail(user.getEmail());
        setLocation(user.getLocation());
        setAbout(user.getAbout());
        setName(user.getName());
        setUsername(user.getUsername());
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
