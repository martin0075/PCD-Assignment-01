package it.unibo.ds.presentation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

//un oggetto richiesta diventarà un oggetto gson con un campo method,
// che sarà una stringa e argument che sarà un oggetto gson
public class Request<T> {

    @Expose
    @SerializedName("method")
    private String method;

    @Expose
    @SerializedName("argument")//qual'è il nome del campo gson
    private T argument;

    public Request() {
    }

    public Request(String method, T argument) {
        this.method = method;
        this.argument = argument;
    }

    public String getMethod() {
        return method;
    }

    public T getArgument() {
        return argument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request<?> request = (Request<?>) o;
        return Objects.equals(method, request.method) && Objects.equals(argument, request.argument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, argument);
    }
}

