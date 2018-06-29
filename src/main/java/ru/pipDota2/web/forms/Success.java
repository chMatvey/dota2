package ru.pipDota2.web.forms;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Success<T> extends Result{
    private final T value;

    public T getValue() {
        return value;
    }
}
