package com.easeArch.common.req;

import com.cxl.rpc.remoting.net.params.RpcRequest;

import java.io.Serializable;

public class TrayRequest extends RpcRequest implements Serializable {
    private static final long serialVersionUID=543210L;
    private String id;
    private Object value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TrayRequest{" +
                "id='" + id + '\'' +
                ", value=" + value +
                '}';
    }
}
