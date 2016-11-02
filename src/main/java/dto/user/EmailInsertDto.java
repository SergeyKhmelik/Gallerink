package dto.user;

import dto.IDto;

public class EmailInsertDto implements IDto {

    private String email;

    public EmailInsertDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
