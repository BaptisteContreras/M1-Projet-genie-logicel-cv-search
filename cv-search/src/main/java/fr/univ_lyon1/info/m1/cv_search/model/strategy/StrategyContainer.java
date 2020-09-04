package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StrategyContainer {
    private Map<String, Strategy> strategies;


    public StrategyContainer() {
        StrategyBuilder strategyBuilder = new StrategyBuilder();
        strategies = strategyBuilder.buildAll(null);
    }


    public Strategy getStrategy(String strategy) {
        return this.strategies.getOrDefault(strategy, null);
    }

    public List<Strategy> getAllStrategy() {
        return new ArrayList<Strategy>(strategies.values());
    }

    /*
    public List<StrategyType> getStrategyOfType(StrategyType){
        Strategy f = new GtAverageFiftyStrategy();
        Class<StrategyType> = StrategyType1::getClass;
        boolean d = f instanceof StrategyType1;
        return strategies.values().stream().filter((Strategy x, Class<StrategyType> j )-> x  == f)
    };
    */

    /**
     * Get all strategy that implement StrategyType1
     *
     * @since 3.0
     * @return List: Strategy
     */
    public List<Strategy> getStrategyType1() {
        return strategies
                .values().stream().filter(x -> x instanceof StrategyType1)
                .collect(Collectors.toList()
                );
    }

    /**
     * Get all strategy that implement StrategyType2
     *
     * @since 3.0
     * @return List: Strategy
     */
    public List<Strategy> getStrategyType2() {
        return strategies
                .values().stream().filter(x -> x instanceof StrategyType2)
                .collect(Collectors.toList()
                );
    }

    /**
     * Return the name of all strategies loaded.
     *
     * @return String[]
     */
    public String[] getStrategiesNames() {
        return this.strategies.keySet().toArray(new String[this.strategies.size()]);
    }
}
