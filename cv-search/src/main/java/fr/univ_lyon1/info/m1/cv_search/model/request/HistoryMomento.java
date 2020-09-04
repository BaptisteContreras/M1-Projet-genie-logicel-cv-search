package fr.univ_lyon1.info.m1.cv_search.model.request;

import fr.univ_lyon1.info.m1.cv_search.model.driver.Driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryMomento {
    private List<HistoryElement> historyElements;

    public HistoryMomento(List<HistoryElement> historyElements) {
        this.historyElements = historyElements;
    }

    public HistoryMomento() {
        historyElements = new ArrayList<HistoryElement>();
    }

    /**
     * Store elements with  given driver.
     *
     * @param driver driver
     */
    public void store(Driver driver) {
        // format data for driver
        Map<String, Object> toWrite = new HashMap<String, Object>();
        int i = 0;
        for (HistoryElement h : historyElements) {
            toWrite.put(String.valueOf(i), h);
            i++;
        }
        driver.write(toWrite);
    }

    /**
     * load history elements with given driver.
     *
     * @param driver driver
     */
    public void load(Driver driver) {
        Map<String, Object> raw = driver.read(null);
        if (raw != null) {
            for (String s : raw.keySet()) {
                historyElements.add((HistoryElement) raw.get(s));
            }
        }
    }

    public List<HistoryElement> getHistoryElements() {
        return historyElements;
    }

    public void setHistoryElements(List<HistoryElement> historyElements) {
        this.historyElements = historyElements;
    }
}
