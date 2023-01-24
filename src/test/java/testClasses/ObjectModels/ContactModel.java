package testClasses.ObjectModels;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContactModel {

    private String anotherSubject;
    private String message;
    private String name;
    private String email;
    private String orderNo;
    private String anotherSubjectError;
    private String messageError;
    private String nameError;
    private String emailError;

    public ContactModel() {

    }

    public ContactModel(String anotherSubject, String message, String name, String email, String orderNo,
                        String anotherSubjectError, String messageError, String nameError, String emailError) {
        this.anotherSubject = anotherSubject;
        this.message = message;
        this.name = name;
        this.email = email;
        this.orderNo = orderNo;
        this.anotherSubjectError = anotherSubjectError;
        this.messageError = messageError;
        this.nameError = nameError;
        this.emailError = emailError;
    }
}
