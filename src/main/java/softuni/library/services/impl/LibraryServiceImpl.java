package softuni.library.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.library.models.dto.xmls.LibraryImportDto;
import softuni.library.models.dto.xmls.LibraryImportRootDto;
import softuni.library.models.entity.Book;
import softuni.library.models.entity.Library;
import softuni.library.repositories.BookRepository;
import softuni.library.repositories.LibraryRepository;
import softuni.library.services.LibraryService;
import softuni.library.util.ValidatorUtil;
import softuni.library.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final static String LIBRARY_PATH = "src/main/resources/files/xml/libraries.xml";

    @Autowired
    private final LibraryRepository libraryRepository;

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final XmlParser xmlParser;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final ValidatorUtil validatorUtil;

    public LibraryServiceImpl(LibraryRepository libraryRepository, BookRepository bookRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }


    @Override
    public boolean areImported() {
        return this.libraryRepository.count()>0;
    }

    @Override
    public String readLibrariesFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(LIBRARY_PATH)));
    }

    @Override
    public String importLibraries() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        LibraryImportRootDto libraryImportRootDto = this.xmlParser.parseXml(LibraryImportRootDto.class, LIBRARY_PATH);

        for (LibraryImportDto libraryImportDto : libraryImportRootDto.getLibraryImportDtos() ) {
            Optional<Library> byName = this.libraryRepository.getByName(libraryImportDto.getName());
            if (validatorUtil.isValid(libraryImportDto) && byName.isEmpty()){
                Library library = this.modelMapper.map(libraryImportDto, Library.class);

                Book book = this.bookRepository.findById(libraryImportDto.getBook().getId()).get();
                Set<Book> books = new HashSet<>();
                books.add(book);
                library.setBooks(books);
                this.libraryRepository.saveAndFlush(library);
                sb.append(String.format("Successfully imported Library: %s - %s", library.getName(),library.getLocation()))
                        .append(System.lineSeparator());

            }else {
                sb.append("Invalid library").append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}
