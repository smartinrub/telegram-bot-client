package com.sergiomartinrubio.model;

import java.util.Objects;

public class ResponseMessageWrapper {
    private boolean ok;
    private ResponseMessage result;

    public ResponseMessageWrapper() {
    }

    public ResponseMessageWrapper(boolean ok, ResponseMessage result) {
        this.ok = ok;
        this.result = result;
    }

    public boolean isOk() {
        return ok;
    }

    public ResponseMessage getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseMessageWrapper that = (ResponseMessageWrapper) o;
        return ok == that.ok && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ok, result);
    }

    @Override
    public String toString() {
        return "ResponseMessageWrapper{" +
                "ok=" + ok +
                ", result=" + result +
                '}';
    }
}
