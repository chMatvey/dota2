package ru.pipDota2.web.forms;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Error extends Result{
    private final String error;

    public String getError() {
        return error;
    }
}
