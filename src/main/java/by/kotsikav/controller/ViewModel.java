package by.kotsikav.controller;

import java.util.Map;

/**
 * Created by yura5 on 03.04.2016.
 */
public class ViewModel {
    private Map<String,Object> model;
    private String forward;

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }
}
