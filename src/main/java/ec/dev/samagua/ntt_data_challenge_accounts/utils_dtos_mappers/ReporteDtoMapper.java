package ec.dev.samagua.ntt_data_challenge_accounts.utils_dtos_mappers;

import ec.dev.samagua.ntt_data_challenge_accounts.utils_dtos.ReporteDto;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_models.Reporte;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReporteDtoMapper {
    ReporteDto entityToDto(Reporte reporte);
    Reporte dtoToEntity(ReporteDto reporteDto);
}
