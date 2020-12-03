package softuni.library.models.dto.jsons;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import softuni.library.config.LocalDateAdaptor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BooksImportDto {

    @Expose
    @Length(min = 100)
    private String description;
    @Expose
    @Min(value = 1)
    @Max(value = 5)
    private int edition;
    @Expose
    @Length(min = 5)
    @NotNull
    private String name;
    @Expose
    @NotNull
    private LocalDate written;
    @Expose
    private int author;

    public BooksImportDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getWritten() {
        return written;
    }

    public void setWritten(LocalDate written) {
        this.written = written;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
}
