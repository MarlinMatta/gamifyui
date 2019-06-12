package edu.uapa.ui.gamify.models.interfaces;

public interface FormEstruture<T> {

    void restore();

    void visualize();

    T getData();

    boolean validComponent();

    void collectData();

    void security();

//    T  onSave();
//
//    void onEditView();
//
//    void onCreate();
//
//    T validItemTab();
}
