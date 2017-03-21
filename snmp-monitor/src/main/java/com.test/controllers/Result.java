package com.test.controllers;

import com.test.criteria.Task;

/**
 * Created by alexey on 03.10.16.
 */
public class Result {

    private final Task task;
    private final Object result;

    public Result(Task s, Object result) {
        this.result = result;
        this.task = s;
    }

    public Task getTask() {
        return task;
    }

    public Object getResult() {
        return result;
    }
}
