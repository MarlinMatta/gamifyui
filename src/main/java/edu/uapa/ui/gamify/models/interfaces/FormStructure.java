package edu.uapa.ui.gamify.models.interfaces;

import java.text.ParseException;

public interface FormStructure<T> {

    void restore(T data);

    void visualize();

    boolean validField();

    T collectData(T model) throws ParseException;

    void security();
}
