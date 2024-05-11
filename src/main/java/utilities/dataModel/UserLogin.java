package utilities.dataModel;

public class UserLogin {
    private String email, password;

    public static UserLogin getUser() {
        return new UserLogin();
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
