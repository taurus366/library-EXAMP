package softuni.library.models.dto.xmls;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "library")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryImportDto {
    @XmlElement
    @NotNull
    @Length(min = 3)
    private String name;
    @XmlElement
    @NotNull
    @Length(min = 5)
    private String location;
    @XmlElement
    @Min(value = 1)
    @Max(value = 10)
    private int rating;
    @XmlElement
    private BookImportXMLDto book;

    public LibraryImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public BookImportXMLDto getBook() {
        return book;
    }

    public void setBook(BookImportXMLDto book) {
        this.book = book;
    }

    //    <name>Georgia Library</name>
//        <location>Dela</location>
//        <rating>6</rating>
//        <book>
//            <id>64</id>
//        </book>


}
