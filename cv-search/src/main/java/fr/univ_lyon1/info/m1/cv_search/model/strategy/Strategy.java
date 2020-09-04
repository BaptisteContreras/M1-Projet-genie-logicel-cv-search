package fr.univ_lyon1.info.m1.cv_search.model.strategy;


import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;

import java.io.Serializable;

public abstract class Strategy implements Serializable {
    public String stratName;

    public Strategy(String name) {
        this.stratName = name;
    }

    /**
     * Filter an ApplicantList with some options.
     *
     * @param appList bla
     * @param options bla
     * @return ApplicantList
     */
    public abstract ApplicantList filter(ApplicantList appList, StrategyOption options);

    public String getName() {
        return this.stratName;
    }
}


