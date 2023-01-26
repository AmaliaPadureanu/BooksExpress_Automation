package testClasses.ObjectModels;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContactModel {

    private int subject;
    private String anotherSubject;
    private String message;
    private String name;
    private String email;
    private String orderNumber;
    private String subjectError;
    private String anotherSubjectError;
    private String messageError;
    private String nameError;
    private String emailError;

    public ContactModel() {

    }

    public ContactModel(int subject, String anotherSubject, String message, String name, String email, String orderNumber,
                        String subjectError, String anotherSubjectError, String messageError, String nameError, String emailError) {
        this.subject = subject;
        this.anotherSubject = anotherSubject;
        this.message = message;
        this.name = name;
        this.email = email;
        this.orderNumber = orderNumber;
        this.subjectError = subjectError;
        this.anotherSubjectError = anotherSubjectError;
        this.messageError = messageError;
        this.nameError = nameError;
        this.emailError = emailError;
    }
}
