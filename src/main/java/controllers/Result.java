package controllers;

import criteria.Critirea;

/**
 * Created by alexey on 03.10.16.
 */
public class Result {

    private final Critirea critirea;
    private final Object result;

    public Result(Critirea s, Object result) {
        this.result = result;
        this.critirea = s;
    }

    public Critirea getCritirea() {
        return critirea;
    }

    public Object getResult() {
        return result;
    }
}
