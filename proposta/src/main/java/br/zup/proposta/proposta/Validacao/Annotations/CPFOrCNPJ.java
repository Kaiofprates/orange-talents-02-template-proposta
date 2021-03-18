package br.zup.proposta.proposta.Validacao.Annotations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@CPF
@CNPJ
@ConstraintComposition(CompositionType.OR)
public @interface CPFOrCNPJ {

    String message() default "CPF/CNPJ inválidos";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};


}
