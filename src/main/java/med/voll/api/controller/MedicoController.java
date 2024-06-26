package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DTOCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DTOListagemMedico> listar(@PageableDefault(size = 10, sort ={"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DTOListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DTOAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarDados(dados);
    }

//    @DeleteMapping("/{id}")
//    @Transactional
//    public void excluir(@PathVariable Long id){
//        repository.deleteById(id);
//    }

    @DeleteMapping("/{id}")
    @Transactional
    public void desativar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.desativar();
    }
}
