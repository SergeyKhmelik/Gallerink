package dto.user;

import domain.user.User;
import dto.IDto;
import dto.IValidated;

public class _SimpleUser implements IDto, IValidated {

    private String username;

    private String name;

    private String location;

    private String about;

    public _SimpleUser() {
    }

    public _SimpleUser(User user) {
        setUsername(user.getUsername());
        setName(user.getName());
        setLocation(user.getLocation());
        setAbout(user.getAbout());
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
}
