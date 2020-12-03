package softuni.library.models.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "characters")
public class Character extends BaseEntity{

    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "middle_name",nullable = false)
    private String middleName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column
    private int age;
    @Column
    private String role;
    @Column
    private LocalDate birthday;

    @ManyToOne
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Character() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
