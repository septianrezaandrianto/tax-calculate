package com.project.taxcalculate.controller.advice;

import com.project.taxcalculate.dto.GeneralResponse;
import com.project.taxcalculate.handler.BadRequestCustomException;
import com.project.taxcalculate.handler.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GlobalExceptionHandler.class)
public class GlobalExceptionHandlerTest {

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;
    @Mock
    private NotFoundException notFoundException;
    @Mock
    private Exception exception;
    @Mock
    private RuntimeException runtimeException;
    @Mock
    private BadRequestCustomException badRequestCustomException;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
        notFoundException = mock(NotFoundException.class);
        exception = mock(Exception.class);
        runtimeException = mock(RuntimeException.class);
        badRequestCustomException = mock(BadRequestCustomException.class);
    }

    @Test
    void testHandleValidationErrors() {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "fieldName", "error message");
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<GeneralResponse<Object>> responseEntity = globalExceptionHandler
                .handleValidationErrors(methodArgumentNotValidException);
        List<String> errors = Objects.requireNonNull(responseEntity.getBody()).getErrorList();

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getResponseCode());
        assertEquals("Bad Request", responseEntity.getBody().getResponseMessage());
        assertEquals(1, errors.size());
        assertEquals("error message", errors.get(0));

        verify(methodArgumentNotValidException, times(1)).getBindingResult();
    }

    @Test
    void testHandleNotFoundException() {
        String errorMessage = "Not Found";
        when(notFoundException.getMessage()).thenReturn(errorMessage);
        ResponseEntity<GeneralResponse<Object>> responseEntity = globalExceptionHandler.handleNotFoundException(notFoundException);

        assertEquals(HttpStatus.NOT_FOUND.value(), Objects.requireNonNull(responseEntity.getBody()).getResponseCode());
        assertEquals(errorMessage, responseEntity.getBody().getResponseMessage());

        List<String> errors = responseEntity.getBody().getErrorList();
        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get(0));
    }

    @Test
    void testHandleGeneralExceptions() {
        String errorMessage = "Internal Server Error";
        when(exception.getMessage()).thenReturn(errorMessage);
        ResponseEntity<GeneralResponse<Object>> responseEntity = globalExceptionHandler.handleGeneralExceptions(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), Objects.requireNonNull(responseEntity.getBody()).getResponseCode());
        assertEquals(errorMessage, responseEntity.getBody().getResponseMessage());

        List<String> errors = responseEntity.getBody().getErrorList();
        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get(0));
    }

    @Test
    void testHandleRuntimeExceptions() {
        String errorMessage = "Internal Server Error";
        when(runtimeException.getMessage()).thenReturn(errorMessage);
        ResponseEntity<GeneralResponse<Object>> responseEntity = globalExceptionHandler.handleRuntimeExceptions(runtimeException);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), Objects.requireNonNull(responseEntity.getBody()).getResponseCode());
        assertEquals(errorMessage, responseEntity.getBody().getResponseMessage());

        List<String> errors = responseEntity.getBody().getErrorList();
        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get(0));
    }

    @Test
    void testHandleBadRequestCustomException() {
        String errorMessage = "Bad Request";
        when(badRequestCustomException.getMessage()).thenReturn(errorMessage);
        ResponseEntity<GeneralResponse<Object>> responseEntity = globalExceptionHandler
                .handleBadRequestCustomException(badRequestCustomException);

        assertEquals(HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(responseEntity.getBody()).getResponseCode());
        assertEquals(errorMessage, responseEntity.getBody().getResponseMessage());

        List<String> errors = responseEntity.getBody().getErrorList();
        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get(0));
    }

}
