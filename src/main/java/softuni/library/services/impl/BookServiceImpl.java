package softuni.library.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.library.models.dto.jsons.BooksImportDto;
import softuni.library.models.entity.Book;
import softuni.library.repositories.AuthorRepository;
import softuni.library.repositories.BookRepository;
import softuni.library.services.BookService;
import softuni.library.util.ValidatorUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class BookServiceImpl implements BookService {

    private final static String BOOK_PATH = "src/main/resources/files/json/books.json";

    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final Gson gson;
    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final ValidatorUtil validatorUtil;

    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, Gson gson, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public boolean areImported() {
        return this.bookRepository.count()>0;
    }

    @Override
    public String readBooksFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(BOOK_PATH)));
    }

    @Override
    public String importBooks() throws IOException {
       StringBuilder sb = new StringBuilder();
        BooksImportDto[] booksImportDtos = this.gson.fromJson(this.readBooksFileContent(), BooksImportDto[].class);
        for (BooksImportDto booksImportDto : booksImportDtos) {

         if (this.validatorUtil.isValid(booksImportDto)){
            Book book = this.modelMapper.map(booksImportDto, Book.class);
            book.setAuthor(this.authorRepository.getOne(booksImportDto.getAuthor()));

            this.bookRepository.saveAndFlush(book);
            sb.append(String
                    .format("Successfully imported Book: %s written in %s", booksImportDto.getName(),booksImportDto.getWritten().toString()))
                    .append(System.lineSeparator());

         }else {
        sb.append("Invalid Book")
                .append(System.lineSeparator());
         }
        }




        return sb.toString();
    }
}
