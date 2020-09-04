package fr.univ_lyon1.info.m1.cv_search.model.request;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.ClassicOption;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.StrategyContainer;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.StrategyOption;

import java.util.ArrayList;

public class SearchRequestBuilder extends AbstractRequestBuilder {
    private StrategyContainer container;

    public SearchRequestBuilder(StrategyContainer container) {
        this.container = container;
    }

    /**
     * Build a SearchRequest with all option given
     *
     * @param stratName strategy to apply
     * @param appl base applicantList
     * @param options options for the request
     *
     * @since 3.0
     */
    public ArrayList<AbstractRequest> build(String[] stratName,
                                            ApplicantList appl,
                                            StrategyOption options) {
        ArrayList<AbstractRequest> logHistory = new ArrayList<>();
        SearchRequest sr = null;

        for (int i = stratName.length - 1; i >= 0; i--) {
            SearchRequest tmp = new SearchRequest(this.container.getStrategy(stratName[i]));
            if (tmp.getToApply() == null) {
                return null;
            }
            logHistory.add(tmp);
            tmp.setNext(sr);
            sr = tmp;
        }

        sr.setBaseList(appl);
        sr.setOptions((ClassicOption) options);

        return logHistory;
    }

}
