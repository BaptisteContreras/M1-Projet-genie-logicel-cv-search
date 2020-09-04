package fr.univ_lyon1.info.m1.cv_search;

import fr.univ_lyon1.info.m1.cv_search.controller.AbstractController;
import fr.univ_lyon1.info.m1.cv_search.controller.CvSearchController;
import fr.univ_lyon1.info.m1.cv_search.model.AbstractModel;
import fr.univ_lyon1.info.m1.cv_search.model.CvSearchModel;
import fr.univ_lyon1.info.m1.cv_search.view.AbstractView;


public class Mockingbird {

    AbstractController controller;
    AbstractModel model;

    public Mockingbird() {
        AbstractView view = mockView();
        model = new CvSearchModel();
        controller = new CvSearchController(model);

    }

    public CvSearchModel model() {
        return (CvSearchModel) this.model;
    }

    public CvSearchController controller() {
        return (CvSearchController) this.controller;
    }


    public AbstractView mockView() {
        return new AbstractView() {
            @Override
            public void initObservers() {

            }

            @Override
            protected void initView() {

            }
        };
    }

}
