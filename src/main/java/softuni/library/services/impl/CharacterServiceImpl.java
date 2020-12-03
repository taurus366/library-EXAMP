package softuni.library.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.library.models.dto.xmls.CharacterImportDto;
import softuni.library.models.dto.xmls.CharacterRootImportDto;
import softuni.library.models.entity.Character;
import softuni.library.repositories.CharacterRepository;
import softuni.library.services.CharacterService;
import softuni.library.util.ValidatorUtil;
import softuni.library.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

@Service
@Transactional
public class CharacterServiceImpl implements CharacterService {

    private final static String CHARACTER_PATH = "src/main/resources/files/xml/characters.xml";

    @Autowired
    private final CharacterRepository characterRepository;

    @Autowired
    private final XmlParser xmlParser;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final ValidatorUtil validatorUtil;
    

    public CharacterServiceImpl(CharacterRepository characterRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.characterRepository = characterRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }


    @Override
    public boolean areImported() {
        return this.characterRepository.count()>0;
    }

    @Override
    public String readCharactersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(CHARACTER_PATH)));
    }

    @Override
    public String importCharacters() throws JAXBException {
       StringBuilder sb = new StringBuilder();

        CharacterRootImportDto characterRootImportDto = this.xmlParser.parseXml(CharacterRootImportDto.class, CHARACTER_PATH);

        for (CharacterImportDto characterImportDto : characterRootImportDto.getCharacterImportDtos() ) {
                if (this.validatorUtil.isValid(characterImportDto)){
                    Character character = this.modelMapper.map(characterImportDto, Character.class);


                    this.characterRepository.saveAndFlush(character);
                    sb.append(String
                            .format("Successfully import Character: %s - %s - %s - age: %d",character.getFirstName(),character.getMiddleName(),character.getLastName(),character.getAge())).append(System.lineSeparator());
                }else {
                    sb.append("Invalid Character").append(System.lineSeparator());
                }
        }
        return sb.toString();
    }

    @Override
    public String findCharactersInBookOrderedByLastNameDescendingThenByAge() {
        StringBuilder sb = new StringBuilder();

        Set<Character> characters = this.characterRepository.findAllByAgeAfterOrderByLastNameDescAgeAsc(32);

        for (Character character : characters) {
            sb
                    .append(String.format("Character name %s, age %d, in book %s", character.getFirstName()+" "+character.getMiddleName()+" "+character.getLastName(),character.getAge(),String.join("", character.getBook().getName())))
                    .append(System.lineSeparator());
        }
            sb.append("asdasdasdasd");

        return sb.toString();
    }
}
