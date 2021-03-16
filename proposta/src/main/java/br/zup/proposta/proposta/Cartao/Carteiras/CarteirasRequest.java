package br.zup.proposta.proposta.Cartao.Carteiras;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteirasRequest {
    @NotBlank
    @Email
    private String email;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private CarteirasDisponiveis carteira;

    @Deprecated
    public CarteirasRequest(){};

    public CarteirasRequest(@NotBlank @Email String email, @NotBlank CarteirasDisponiveis carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public CarteirasDisponiveis getCarteira() {
        return carteira;
    }

    @Override
    public String toString() {
        return "CarteirasRequest{" +
                "email='" + email + '\'' +
                ", carteira='" + carteira + '\'' +
                '}';
    }
}
