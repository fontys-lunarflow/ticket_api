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

    public Form(boolean err, String msg, T obj) {
        this.error = err;
        this.mesg = msg;
        this.data = obj;
    }
}