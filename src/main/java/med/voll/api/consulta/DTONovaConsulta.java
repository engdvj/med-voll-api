package med.voll.api.consulta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DTONovaConsulta(
        @NotBlank
        @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}$",
                message = "Data deve estar no formato dd-MM-yyyy HH:mm")
        String data,
        Boolean ativo
) {}