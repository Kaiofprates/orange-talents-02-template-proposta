package br.zup.proposta.proposta.Cartao.Model.Repository;

import br.zup.proposta.proposta.Cartao.Model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao,String> {
}
