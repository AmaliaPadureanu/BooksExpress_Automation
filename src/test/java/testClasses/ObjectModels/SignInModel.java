package testClasses.ObjectModels;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignInModel {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String emailError;
    private String firstNameError;
    private String lastNameError;
    private String passwordError;
    private String confirmPasswordError;

    public SignInModel() {

    }

    public SignInModel(String email, String firstName, String lastName, String password, String confirmPassword,
                       String emailError, String firstNameError, String lastNameError, String passwordError,
                       String confirmPasswordError) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.emailError = emailError;
        this.firstNameError = firstNameError;
        this.lastNameError = lastNameError;
        this.passwordError = passwordError;
        this.confirmPasswordError = confirmPasswordError;
    }

    @Override
    public String toString() {
        return "SignInModel{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", emailError='" + emailError + '\'' +
                ", firstNameError='" + firstNameError + '\'' +
                ", lastNameError='" + lastNameError + '\'' +
                ", passwordError='" + passwordError + '\'' +
                ", confirmPasswordError='" + confirmPasswordError + '\'' +
                '}';
    }
}
