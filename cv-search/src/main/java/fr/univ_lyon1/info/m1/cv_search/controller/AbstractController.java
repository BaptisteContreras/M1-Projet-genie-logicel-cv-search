package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.model.AbstractModel;

public abstract class AbstractController {

    protected AbstractModel model;


    public AbstractController(AbstractModel model) {
        this.model = model;
        model.setController(this);
    }
}
