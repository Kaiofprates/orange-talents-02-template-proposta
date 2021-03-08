package br.zup.proposta.proposta.Validacao.Advice;

import br.zup.proposta.proposta.Validacao.Exeptions.DuplicateDocumentoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FormErrorDto> handle(MethodArgumentNotValidException exception) {
        List<FormErrorDto> dto = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            int status = HttpStatus.BAD_REQUEST.value();
            LocalDateTime time = LocalDateTime.now();
            FormErrorDto erro = new FormErrorDto(e.getField(), mensagem,status,time);
            dto.add(erro);
        });
        return dto;
    }

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(DuplicateDocumentoException.class)
    public FormErrorDto duplicateDocument(DuplicateDocumentoException exception) {
        LocalDateTime time = LocalDateTime.now();
        FormErrorDto dto = new FormErrorDto("CPF / CNPJ","Não foi possível concluir a sua proposta",422,time);
        return dto;
    }






}