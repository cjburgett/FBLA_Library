package sample.bookScreen;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookData extends RecursiveTreeObject<BookData> {
    private final StringProperty ID;
    private final StringProperty ISBN;
    private final StringProperty aLast;
    private final StringProperty aFirst;
    private final StringProperty title;
    private final StringProperty checkedOut;
    private final StringProperty whoChecked;
    private final StringProperty whoCheckedT;

    public BookData(String id, String ISBN, String aLast, String aFirst, String title, String checkedOut, String whoChecked, String whoCheckedT)
    {
        this.ID = new SimpleStringProperty(id);
        this.ISBN = new SimpleStringProperty(ISBN);
        this.aLast = new SimpleStringProperty(aLast);
        this.aFirst = new SimpleStringProperty(aFirst);
        this.title = new SimpleStringProperty(title);
        this.checkedOut = new SimpleStringProperty(checkedOut);
        this.whoChecked = new SimpleStringProperty(whoChecked);
        this.whoCheckedT = new SimpleStringProperty(whoCheckedT);
    }

    public String getID()
    {
        return (String)this.ID.get();
    }

    public String getISBN()
    {
        return (String)this.ISBN.get();
    }

    public String getaLast()
    {
        return (String)this.aLast.get();
    }

    public String getaFirst()
    {
        return (String)this.aFirst.get();
    }

    public String getTitle()
    {
        return (String)this.title.get();
    }

    public String getcheckedOut()
    {
        return (String)this.checkedOut.get();
    }

    public String getwhoChecked()
    {
        return (String)this.whoChecked.get();
    }

    public String getwhoCheckedT()
    {
        return (String)this.whoCheckedT.get();
    }


    public void setID(String value)
    {
        this.ID.set(value);
    }

    public void setISBN(String value)
    {
        this.ISBN.set(value);
    }

    public void setALast(String value)
    {
        this.aLast.set(value);
    }

    public void setAFirst(String value)
    {
        this.aFirst.set(value);
    }

    public void setTitle(String value)
    {
        this.title.set(value);
    }

    public void setCheckoutOut(String value)
    {
        this.checkedOut.set(value);
    }

    public void setWhoChecked(String value)
    {
        this.whoChecked.set(value);
    }

    public void setWhoCheckedT(String value)
    {
        this.whoCheckedT.set(value);
    }


    public StringProperty idProperty()
    {
        return this.ID;
    }

    public StringProperty ISBNProperty()
    {
        return this.ISBN;
    }

    public StringProperty aLastProperty()
    {
        return this.aLast;
    }

    public StringProperty aFirstProperty()
    {
        return this.aFirst;
    }

    public StringProperty titleProperty()
    {
        return this.title;
    }

    public StringProperty checkedOutProperty()
    {
        return this.checkedOut;
    }

    public StringProperty whoCheckedProperty()
    {
        return this.whoChecked;
    }

    public StringProperty whoCheckedTProperty()
    {
        return this.whoCheckedT;
    }
}
