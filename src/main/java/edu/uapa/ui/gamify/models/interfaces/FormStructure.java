package edu.uapa.ui.gamify.models.interfaces;

public interface FormStructure<T> {

    void restore(T data);

    void visualize();

    boolean validField();

    T collectData(T model);

    void security();
}
