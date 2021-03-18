package br.zup.proposta.proposta.Proposta;

import br.zup.proposta.proposta.Validacao.Annotations.CPFOrCNPJ;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
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

    @Enumerated(value = EnumType.STRING)
    private Estado status = Estado.NAO_ELEGIVEL;

    private String cartao;


    @Deprecated
    public Proposta(){};

    public Proposta(@NotBlank String documento,
                    @NotBlank @Email String email,
                    @NotBlank String nome, @Valid @NotNull Endereco endereco,
                    @NotNull @Positive BigDecimal salario) {

        this.documento =  documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }
    public String getNome() {
        return nome;
    }
    public void setStatus(Estado status) {
        this.status = status;
    }
    public void setCartao(String cartao) {
        this.cartao = cartao;
    }
    public String getCartao() {
        return cartao;
    }

    public Estado getStatus() {
        return status;
    }


    // gets gerado para testes

    public String getEmail() {
        return email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}
