package fr.univ_lyon1.info.m1.cv_search.model.request;

import java.io.Serializable;
import java.util.List;

public class HistoryElement implements Serializable {

    private List<AbstractRequest> el;

    public HistoryElement(List<AbstractRequest> el) {
        this.el = el;
    }

    public int size() {
        return el.size();
    }

    public List<AbstractRequest> getEl() {
        return el;
    }

    public AbstractRequest get(int k) {
        return el.get(k);
    }
}
