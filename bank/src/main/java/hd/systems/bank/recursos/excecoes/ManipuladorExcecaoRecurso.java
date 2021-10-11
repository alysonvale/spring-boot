package hd.systems.bank.recursos.excecoes;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import hd.systems.bank.entidades.excecao.EntitiesException;
import hd.systems.bank.services.excecoes.AcessoExcecao;
import hd.systems.bank.services.excecoes.LoginExcecao;
import hd.systems.bank.services.excecoes.RecursoNaoEncontradoExcecao;
import hd.systems.bank.services.excecoes.TempoExpiradoException;
import hd.systems.bank.services.excecoes.TransferenciaExcecao;


@ControllerAdvice
public class ManipuladorExcecaoRecurso {

    @ExceptionHandler(RecursoNaoEncontradoExcecao.class)
    public ResponseEntity<ErroPadrao> recursoNaoEncontrado(RecursoNaoEncontradoExcecao e, HttpServletRequest request ){
        String error = "conta, usuário ou senha inválidos(s): ";
        HttpStatus status = HttpStatus.valueOf(300);
        ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(),  request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(AcessoExcecao.class)
    public ResponseEntity<ErroPadrao> AcessoInvalido(AcessoExcecao e, HttpServletRequest request ){
        String error = "código de acesso inválido:";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(),  request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(TransferenciaExcecao.class)
    public ResponseEntity<Object> ErroTransferencia(TransferenciaExcecao e, HttpServletRequest request ){
        HttpStatus status;
        status = HttpStatus.MULTIPLE_CHOICES;
        String error = "Falha na transferencia";
        ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(),  request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(TempoExpiradoException.class)
    public ResponseEntity<ErroPadrao> TempoEspirado(TempoExpiradoException e, HttpServletRequest request){
        String error = "Tempo Esgotado";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(),  request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(LoginExcecao.class)
    public ResponseEntity<ErroPadrao> LoginExcecao(LoginExcecao e, HttpServletRequest request ){
        String error = "Não existe com esse id:";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(),  request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EntitiesException.class)
    public ResponseEntity<ErroPadrao> EntitiesException(EntitiesException e, HttpServletRequest request ){
        String error = e.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(),  request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> ErroAplicação(RuntimeException e, HttpServletRequest request ){
        String error = "Erro na aplicação";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(),  request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
