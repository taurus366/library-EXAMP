package softuni.library.models.dto.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "characters")
@XmlAccessorType(XmlAccessType.FIELD)
public class CharacterRootImportDto {

    @XmlElement(name = "character")
    private List<CharacterImportDto> characterImportDtos;

    public CharacterRootImportDto() {
    }

    public List<CharacterImportDto> getCharacterImportDtos() {
        return characterImportDtos;
    }

    public void setCharacterImportDtos(List<CharacterImportDto> characterImportDtos) {
        this.characterImportDtos = characterImportDtos;
    }
}
