package com.ducnd.chattfunn.model.request;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDate;


public class RegisterRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String displayName;
    private String avatar;
    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
