package med.voll.api.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DTOCadastroEndereco;

public record DTOAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DTOCadastroEndereco endereco) {
}
