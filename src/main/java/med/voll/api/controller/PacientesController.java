package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medicos.DadosCadastroMedicos;
import med.voll.api.pacientes.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity <Page<DadosListagemPaciente>> listar (@PageableDefault (size = 5, sort = {"nome"}) Pageable paginacao) {
        var paciente= repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente:: new);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarCadastro(@RequestBody @Valid DadosAtualizacaoPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPaciente(@PathVariable  Long id){
        var paciente = repository.getReferenceById(id);
        paciente.excluirCadastro();
        return ResponseEntity.noContent().build();
    }
}
