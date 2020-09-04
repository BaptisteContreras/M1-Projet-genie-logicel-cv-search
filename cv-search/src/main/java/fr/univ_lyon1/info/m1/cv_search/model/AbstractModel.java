package fr.univ_lyon1.info.m1.cv_search.model;

import fr.univ_lyon1.info.m1.cv_search.controller.AbstractController;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.StrategyContainer;

import java.util.Observable;

public abstract class AbstractModel extends Observable {
    protected AbstractController controller;
    protected StrategyContainer strategieContainer;

    public AbstractModel() {
        strategieContainer = new StrategyContainer();
    }

    public AbstractController getController() {
        return controller;
    }

    public void setController(AbstractController controller) {
        this.controller = controller;
    }

    public StrategyContainer getStrategieContainer() {
        return strategieContainer;
    }

}
