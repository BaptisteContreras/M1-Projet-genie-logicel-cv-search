package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;


public class GtSixtyStrategy extends GtStrategy {
    public GtSixtyStrategy() {
        super("GtSixty");
    }

    @Override
    public ApplicantList filter(ApplicantList appList, StrategyOption options) {
        return applyGt(appList, options, 60);
    }
}
