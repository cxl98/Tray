package com.easeArch.common.res;

import java.io.Serializable;

public class TrayResponse implements Serializable {
    private static final long serialVersionUID=54321L;
    private String id;
    private String error;
    private Object value;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TrayResponse{" +
                "id='" + id + '\'' +
                ", error='" + error + '\'' +
                ", value=" + value +
                '}';
    }
}
