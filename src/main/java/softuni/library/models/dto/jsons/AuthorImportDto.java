package softuni.library.models.dto.jsons;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

public class AuthorImportDto {

    @Expose
    @Length(min = 2)
    private String firstName;
    @Expose
    @Length(min = 2)
    private String lastName;
    @Expose
    @Length(min = 3,max = 12)
    private String birthTown;

    public AuthorImportDto() {
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

    public String getBirthTown() {
        return birthTown;
    }

    public void setBirthTown(String birthTown) {
        this.birthTown = birthTown;
    }
}
