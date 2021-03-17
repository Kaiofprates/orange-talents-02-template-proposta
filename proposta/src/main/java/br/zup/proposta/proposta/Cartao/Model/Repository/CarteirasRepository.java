package br.zup.proposta.proposta.Cartao.Model.Repository;

import br.zup.proposta.proposta.Cartao.Model.Carteiras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CarteirasRepository extends JpaRepository<Carteiras,String> {
    Optional<Carteiras> findByIdcartaoAndEmissor(String idcartao, String emissor);
}

