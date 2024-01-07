package home.parham.loginsample.beans;

import home.parham.loginsample.validator.LoginValidator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by parham on 9/19/14.
 */

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean implements Serializable {
    @NotNull
    private String username;
    private String password;

    public LoginBean() {
        System.out.println("INFO: LoginBean is created.");
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String doLogin() {
        LoginValidator validator = new LoginValidator();
        validator.logValidation(this);
        System.out.println("INFO: doLogin is called.");
        System.out.println(username + " " + password);
        return "index";
    }
}
