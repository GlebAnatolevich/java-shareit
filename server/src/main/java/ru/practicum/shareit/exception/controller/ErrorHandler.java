package ru.practicum.shareit.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.exception.ForbiddenException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.model.ErrorResponse;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    /**
     * Метод для обработки исключения 404
     *
     * @param exception - возникающая ошибка
     * @return возвращаемый ответ
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        log.warn(exception.getMessage());
        return new ErrorResponse(exception.getMessage());
    }

    /**
     * Метод для обработки исключения 403
     *
     * @param exception - возникающая ошибка
     * @return возвращаемый ответ
     */
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleForbiddenException(ForbiddenException exception) {
        log.warn(exception.getMessage());
        return new ErrorResponse(exception.getMessage());
    }

    /**
     * Метод для обработки исключения 500
     *
     * @param exception - возникающая ошибка
     * @return возвращаемый ответ
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)   // 500
    public ErrorResponse handleThrowable(final Exception exception) {
        log.warn("Error", exception);
        return new ErrorResponse(exception.getMessage());
    }
}
