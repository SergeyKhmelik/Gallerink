package dto.user;

import domain.user.User;
import dto.IDto;
import dto.IValidated;

public class _SimpleUser implements IDto, IValidated {

    private String name;

    private String location;

    private String about;

    private String avatar;

    public _SimpleUser() {
    }

    public _SimpleUser(User user) {
        setName(user.getName());
        setLocation(user.getLocation());
        setAbout(user.getAbout());
        setAvatar(user.getAvatar());
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
