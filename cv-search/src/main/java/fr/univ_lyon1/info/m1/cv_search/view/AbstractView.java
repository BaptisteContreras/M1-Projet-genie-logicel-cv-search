package fr.univ_lyon1.info.m1.cv_search.view;

import fr.univ_lyon1.info.m1.cv_search.controller.AbstractController;
import fr.univ_lyon1.info.m1.cv_search.model.AbstractModel;
import javafx.scene.Parent;

public abstract class AbstractView {
    protected AbstractModel model;
    protected AbstractController controller;
    protected Parent root;


    public AbstractModel getModel() {
        return model;
    }

    public void setModel(AbstractModel model) {
        this.model = model;
    }

    public AbstractController getController() {
        return controller;
    }

    public void setController(AbstractController controller) {
        this.controller = controller;
    }

    /**
     * set root as parent of the current scene.
     *
     * @return Parent
     */
    public Parent asParent() {
        return root;
    }

    public abstract void initObservers();

    protected abstract void initView();
}
