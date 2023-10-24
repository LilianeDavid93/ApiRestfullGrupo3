package com.apifinal.Grupo3.exceptions;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	ProblemDetail handleNoSuchElementException(NoSuchElementException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());

		problemDetail.setTitle("Recurso Não Encontrado");
		problemDetail.setType(URI.create("https://api.ecommerce.com/errors/not-found"));
		return problemDetail;
	}

	@ExceptionHandler(CategoriaNotFoundException.class)
	ProblemDetail handleAlunoNotFoundException(CategoriaNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Categoria Não Encontrado");
		problemDetail.setType(URI.create("https://api.ecommerce.com/errors/not-found"));
		return problemDetail;
	}

	@ExceptionHandler(ClienteNotFoundException.class)
	ProblemDetail handleAlunoNotFoundException(ClienteNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Cliente Não Encontrado");
		problemDetail.setType(URI.create("https://api.ecommerce.com/errors/not-found"));
		return problemDetail;
	}

	@ExceptionHandler(EnderecoNotFoundException.class)
	ProblemDetail handleAlunoNotFoundException(EnderecoNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Endereco Não Encontrado");
		problemDetail.setType(URI.create("https://api.ecommerce.com/errors/not-found"));
		return problemDetail;
	}

	@ExceptionHandler(ItemPedidoNotFoundException.class)
	ProblemDetail handleAlunoNotFoundException(ItemPedidoNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Item pedido Não Encontrado");
		problemDetail.setType(URI.create("https://api.ecommerce.com/errors/not-found"));
		return problemDetail;
	}

	@ExceptionHandler(PedidoNotFoundException.class)
	ProblemDetail handleAlunoNotFoundException(PedidoNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Pedido Não Encontrado");
		problemDetail.setType(URI.create("https://api.ecommerce.com/errors/not-found"));
		return problemDetail;
	}

	@ExceptionHandler(ProdutoNotFoundException.class)
	ProblemDetail handleAlunoNotFoundException(ProdutoNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Produto Não Encontrado");
		problemDetail.setType(URI.create("https://api.biblioteca.com/errors/not-found"));
		return problemDetail;
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {
		ResponseEntity<Object> response = super.handleExceptionInternal(ex, body, headers, statusCode, request);

		if (response.getBody() instanceof ProblemDetail problemDetailBody) {
			problemDetailBody.setProperty("message", ex.getMessage());
			if (ex instanceof MethodArgumentNotValidException subEx) {
				BindingResult result = subEx.getBindingResult();
				problemDetailBody.setType(URI.create("http://api.biblioteca.com/erros/argument-not-valid"));
				;
				problemDetailBody.setTitle("Erro na requisição");
				problemDetailBody.setDetail("Ocorreu um erro ao processar a Requisição");
				problemDetailBody.setProperty("message", "Falha na Validação do Objeto '" + result.getObjectName()
						+ "'. " + "Quantidade de Erros: " + result.getErrorCount());
				List<FieldError> fldErros = result.getFieldErrors();
				List<String> erros = new ArrayList<>();

				for (FieldError obj : fldErros) {
					erros.add("Campo: " + obj.getField() + " - Erro: " + obj.getDefaultMessage());
				}
				problemDetailBody.setProperty("Erros Encontrados", erros.toString());
			}
		}
		return response;
	}
}
