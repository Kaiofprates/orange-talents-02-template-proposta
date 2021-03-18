package br.zup.proposta.proposta.Metricas;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.ArrayList;

@Component
public class MinhasMetricas {

    private final MeterRegistry meterRegistry;
    public MinhasMetricas(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void contaPropostas(){
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("emissora","Mastercard"));
        tags.add(Tag.of("banco","Itaú"));
        Counter contadorDePropostasCriadas = this.meterRegistry.counter("proposta_criada", tags);
        contadorDePropostasCriadas.increment();

    }




}
