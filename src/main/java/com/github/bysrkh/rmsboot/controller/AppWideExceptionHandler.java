package com.github.bysrkh.rmsboot.controller;

import com.github.bysrkh.rmsboot.util.error.DuplicateEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class AppWideExceptionHandler {
    private MessageSource messageSource;

    @ExceptionHandler(DuplicateEntityException.class)
    public String showDuplicateEntityException(DuplicateEntityException exc, Model model, Locale locale) {

        model.addAttribute(
                "error",
                messageSource.getMessage(
                "error.entity.duplicate",
                    new Object[]{exc.getProperties()[0],exc.getProperties()[1]},
                    locale
        ));

        return "error/errorPage";
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
