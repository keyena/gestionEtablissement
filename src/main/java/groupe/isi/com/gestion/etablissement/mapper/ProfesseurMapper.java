package groupe.isi.com.gestion.etablissement.mapper;

import groupe.isi.com.gestion.etablissement.dto.ProfesseurDto;
import groupe.isi.com.gestion.etablissement.model.Professeur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfesseurMapper {
    @Mapping(target = "id", ignore = true)
    Professeur toEntity(ProfesseurDto dto);

    ProfesseurDto toDto(Professeur entity);
} 