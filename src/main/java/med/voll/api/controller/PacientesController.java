package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medicos.DadosCadastroMedicos;
import med.voll.api.pacientes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacientesController {
    @Autowired
    private PacienteRepository repository;
    @PostMapping
    public void cadastrarNovoPaciente(@RequestBody @Valid DadosCadastroPacientes dados){
        repository.save(new Pacientes(dados));
    }
    @GetMapping
    public Page<DadosListagemPaciente> listar (@PageableDefault (size = 5, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemPaciente:: new);
    }

    @PutMapping
    @Transactional
    public void atualizarCadastro(@RequestBody @Valid DadosAtualizacaoPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

    }
}
