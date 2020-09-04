package fr.univ_lyon1.info.m1.cv_search.model.request;

import fr.univ_lyon1.info.m1.cv_search.model.driver.SerializerDriver;

import java.util.ArrayList;
import java.util.List;

public class History {

    List<HistoryElement> history;
    HistoryMomento momento;

    public History() {
        history = new ArrayList<>();
        momento = new HistoryMomento();
    }


    public HistoryElement getElem(int el) {
        return history.get(el);
    }

    public void add(List<AbstractRequest> e) {
        history.add(new HistoryElement(e));
    }

    public int size() {
        return history.size();
    }

    public List<HistoryElement> all() {
        return history;
    }


    public void save() {
        momento = new HistoryMomento(history);
        momento.store(new SerializerDriver("./history"));
    }


    /**
     * Load the history from the momento
     *
     * @since 3.0
     */
    public void restore() {
        momento = new HistoryMomento();
        momento.load(new SerializerDriver("./history"));
        history = momento.getHistoryElements();
    }

    public void clear() {
        history.clear();
    }
}
