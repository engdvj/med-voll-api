package med.voll.api.controller;

import med.voll.api.consulta.Consulta;
import med.voll.api.consulta.ConsultaRepository;
import med.voll.api.consulta.DTONovaConsulta;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping("/medico/{medicoId}/paciente/{pacienteId}")
    @Transactional
    public void cadastrar(@PathVariable Long medicoId, @PathVariable Long pacienteId,
                          @RequestBody @Valid DTONovaConsulta dados) {
        // Busca o médico e o paciente no banco de dados com base nos IDs fornecidos
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado com ID: " + medicoId));
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado com ID: " + pacienteId));

        // Cria uma nova consulta com os objetos Medico e Paciente, e os dados de data e ativo do DTO
        Consulta novaConsulta = new Consulta(medico, paciente, dados.data());

        // Salva a nova consulta no repositório
        consultaRepository.save(novaConsulta);
    }
}
/*
    @GetMapping
    public Page<DTOListagemMedico> listar(@PageableDefault(size = 10, sort ={"id"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DTOListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DTOAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarDados(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void desativar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.desativar();
    }
    */

