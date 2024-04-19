package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DTOCadastroEndereco;

public record DTOAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DTOCadastroEndereco endereco) {
}
