package softuni.library.models.dto.xmls;

import org.hibernate.validator.constraints.Length;
import softuni.library.config.LocalDateAdaptor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "character")
@XmlAccessorType(XmlAccessType.FIELD)
public class CharacterImportDto {
    @XmlElement(name = "age")
    @Min(value = 10)
    @Max(value = 66)
    @NotNull
    private int age;
    @XmlElement(name = "first-name")
    @Length(min = 3)
    @NotNull
    private String firstName;
    @XmlElement(name = "last-name")
    @Length(min = 3)
    @NotNull
    private String lastName;
    @XmlElement(name = "middle-name")
    @NotNull
    private String middleName;
    @XmlElement(name = "role")
    @Length(min = 5)
    @NotNull
    private String role;
    @XmlElement(name = "birthday")
    @NotNull
    @XmlJavaTypeAdapter(LocalDateAdaptor.class)
    private LocalDate birthday;
    @XmlElement(name = "book")
    private BookImportXMLDto bookImportXMLDto;

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public BookImportXMLDto getBookImportXMLDto() {
        return bookImportXMLDto;
    }

    public void setBookImportXMLDto(BookImportXMLDto bookImportXMLDto) {
        this.bookImportXMLDto = bookImportXMLDto;
    }

    public CharacterImportDto() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
