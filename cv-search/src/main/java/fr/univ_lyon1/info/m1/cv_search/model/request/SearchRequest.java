package fr.univ_lyon1.info.m1.cv_search.model.request;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.ClassicOption;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.Strategy;

public class SearchRequest extends AbstractRequest {

    private Strategy toApply;
    private SearchRequest next;

    private ApplicantList baseList;

    private ClassicOption options;
    private ApplicantList result;


    public SearchRequest(Strategy toApply) {
        this.toApply = toApply;
    }

    public void setNext(SearchRequest next) {
        this.next = next;
    }


    public ClassicOption getOptions() {
        return options;
    }

    public void setOptions(ClassicOption options) {
        this.options = options;
    }

    public void setBaseList(ApplicantList baseList) {
        this.baseList = baseList;
        this.result = baseList;
    }


    /**
     * Call the next request to filter the current result list
     *
     * @since 3.0
     */
    public SearchRequest callNext() {
        this.result = this.toApply.filter(this.baseList, this.options);

        if (next == null) {
            return this;

        } else {
            this.next.setBaseList(this.result);
            this.next.setOptions(this.options);
            return this.next.callNext();
        }
    }

    public Strategy getToApply() {
        return toApply;
    }

    public ApplicantList getResult() {
        return result;
    }
}
