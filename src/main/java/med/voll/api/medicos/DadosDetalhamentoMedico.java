package med.voll.api.medicos;

import med.voll.api.endereco.Endereco;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone ,Especialidade especialidade, Endereco endereco) {
    public DadosDetalhamentoMedico (Medicos medicos){
        this(medicos.getId(), medicos.getNome(), medicos.getEmail(), medicos.getCrm(), medicos.getTelefone(),
                medicos.getEspecialidade(), medicos.getEndereco());
    }
}
