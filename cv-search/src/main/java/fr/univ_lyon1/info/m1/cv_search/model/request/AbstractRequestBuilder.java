package fr.univ_lyon1.info.m1.cv_search.model.request;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.StrategyOption;

import java.util.ArrayList;

public abstract class AbstractRequestBuilder {

    /**
     * Build the request with given args.
     *
     * @param stratName name of strat
     * @param appl applicant
     * @param options options
     * @return ArrayList : AbstractRequest
     */
    public abstract ArrayList<AbstractRequest> build(
            String[] stratName,
            ApplicantList appl,
            StrategyOption options

    );

}
