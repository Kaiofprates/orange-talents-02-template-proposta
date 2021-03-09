package br.zup.proposta.proposta.ClientHttp.BuscaProposta;

import java.time.LocalDateTime;

public class Vencimento {
    private String id;
    private int dia;
    private LocalDateTime dataDeCriacao;

    public Vencimento(String id, int dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
