package br.zup.proposta.proposta.Proposta;

import br.zup.proposta.proposta.Validacao.Annotations.CPFOrCNPJ;
import br.zup.proposta.proposta.Validacao.Exceptions.DuplicateDocumentoException;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Optional;

public class PropostaRequest {

    @NotBlank
    @CPFOrCNPJ
    private String documento;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @Valid
    @NotNull
    private Endereco endereco;
    @NotNull
    @Positive
    private BigDecimal salario;

    /**
     * @Deprecated
     */
    public PropostaRequest(){}

    public PropostaRequest(@NotBlank String documento,
                           @NotBlank @Email String email,
                           @NotBlank String nome,
                           @Valid @NotNull Endereco endereco,
                           @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel(PropostaRepository repository) throws DuplicateDocumentoException {
        Optional<Proposta> proposta  = repository.findByDocumento(documento);

        if(proposta.isPresent()){
            throw  new DuplicateDocumentoException("Não foi possível completar seu cadastro");
        }

        //Assert.isTrue(!proposta.isPresent(),"Não foi possível completar sua proposta");
        return new Proposta(documento,email,nome,endereco,salario);
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}
