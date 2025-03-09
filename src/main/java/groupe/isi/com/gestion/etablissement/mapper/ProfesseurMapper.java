package groupe.isi.com.gestion.etablissement.mapper;

import groupe.isi.com.gestion.etablissement.dto.ProfesseurDto;
import groupe.isi.com.gestion.etablissement.model.Professeur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfesseurMapper {
    @Mapping(target = "id", ignore = true)
    Professeur toEntity(ProfesseurDto dto);

    ProfesseurDto toDto(Professeur entity);
} 