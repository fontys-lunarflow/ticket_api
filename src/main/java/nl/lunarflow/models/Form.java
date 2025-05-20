package nl.lunarflow.models;

import jakarta.annotation.Nullable;

public class Form<T> {
    public boolean error;

    @Nullable
    public String mesg;

    @Nullable
    public T data;

    public Form() {

    }
}