package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.driver.Driver;

import java.util.HashMap;
import java.util.Map;

public class StrategyBuilder {


    /**
     * Create all necessary strategy for the application.
     * if no driver specified, create manually all strategies.
     *
     * @param readDriver le driver
     */
    public Map<String, Strategy> buildAll(Driver readDriver) {
        Map<String, Strategy> strategies = new HashMap<String, Strategy>();

        if (readDriver == null) {

            strategies = new HashMap<>();
            Strategy currentstrat = new GtFiftyStrategy();
            strategies.put(currentstrat.getName(), currentstrat);

            currentstrat = new GtSixtyStrategy();
            strategies.put(currentstrat.getName(), currentstrat);

            currentstrat = new GtAverageFiftyStrategy();
            strategies.put(currentstrat.getName(), currentstrat);

            currentstrat = new SortByExperience();
            strategies.put(currentstrat.getName(), currentstrat);

            currentstrat = new OptimizedStrategy();
            strategies.put(currentstrat.getName(), currentstrat);

            currentstrat = new SortByAverage();
            strategies.put(currentstrat.getName(), currentstrat);

            currentstrat = new CompanieStrategy();
            strategies.put(currentstrat.getName(), currentstrat);
        } else {
            Map<String, Object> tmpData = readDriver.read(null);
            for (String k : tmpData.keySet()) {
                strategies.put(k, (Strategy) tmpData.get(k));
            }

        }
        return strategies;
    }
}
