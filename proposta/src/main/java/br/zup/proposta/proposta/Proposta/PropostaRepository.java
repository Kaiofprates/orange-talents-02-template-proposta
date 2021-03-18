package br.zup.proposta.proposta.Proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta,Long> {
    Optional<Proposta> findByDocumento(String documento);
    List<Proposta> findByStatusAndCartao(Estado estado,String cartao);
}
