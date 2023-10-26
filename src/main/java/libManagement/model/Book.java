package libManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String version;
    private boolean borrowed;

    //Empty Constructor for JPA
    public Book(){}

    public Book(String title, String version){
        this.title = title;
        this.version = version;
        this.borrowed = false;
    }

    public Long getID(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getVersion(){
        return this.version;
    }

    public void setVersion(String version){
        this.version = version;
    }

    public boolean getBorrowed(){
        return this.borrowed;
    }

    public void setBorrowed(boolean borrowedStatus){
        this.borrowed = borrowedStatus;
    }
}


    
