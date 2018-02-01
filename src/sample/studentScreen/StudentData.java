package sample.studentScreen;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentData extends RecursiveTreeObject<StudentData> {
    private final StringProperty ID;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty booksAllowed;
    private final StringProperty daysAllowed;

    public StudentData(String id, String firstname, String lastname, String booksAllowed, String daysAllowed)
    {
        this.ID = new SimpleStringProperty(id);
        this.firstName = new SimpleStringProperty(firstname);
        this.lastName = new SimpleStringProperty(lastname);
        this.booksAllowed = new SimpleStringProperty(booksAllowed);
        this.daysAllowed = new SimpleStringProperty(daysAllowed);
    }

    public String getID()
    {
        return (String)this.ID.get();
    }

    public String getFirstName()
    {
        return (String)this.firstName.get();
    }

    public String getLastName()
    {
        return (String)this.lastName.get();
    }

    public String getBooksAllowed()
    {
        return (String)this.booksAllowed.get();
    }

    public String getDaysAllowed()
    {
        return (String)this.daysAllowed.get();
    }

    public void setID(String value)
    {
        this.ID.set(value);
    }

    public void setFirstName(String value)
    {
        this.firstName.set(value);
    }

    public void setLastName(String value)
    {
        this.lastName.set(value);
    }

    public void setBooksAllowed(String value)
    {
        this.booksAllowed.set(value);
    }

    public void setDaysAllowed(String value)
    {
        this.daysAllowed.set(value);
    }

    public StringProperty idProperty()
    {
        return this.ID;
    }

    public StringProperty firstNameProperty()
    {
        return this.firstName;
    }

    public StringProperty lastNameProperty()
    {
        return this.lastName;
    }

    public StringProperty emailProperty()
    {
        return this.booksAllowed;
    }

    public StringProperty dobProperty()
    {
        return this.daysAllowed;
    }
}

