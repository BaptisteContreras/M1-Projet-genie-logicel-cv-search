package fr.univ_lyon1.info.m1.cv_search.model;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.companie.Companie;
import fr.univ_lyon1.info.m1.cv_search.model.companie.CompanieList;
import fr.univ_lyon1.info.m1.cv_search.model.driver.YamlDriver;
import fr.univ_lyon1.info.m1.cv_search.model.request.AbstractRequest;
import fr.univ_lyon1.info.m1.cv_search.model.request.History;
import fr.univ_lyon1.info.m1.cv_search.model.request.SearchRequest;
import fr.univ_lyon1.info.m1.cv_search.model.skill.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.skill.SkillList;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.Strategy;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.StrategyType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CvSearchModel extends AbstractModel {


    private SkillList skillList;
    private ApplicantList applicantList;
    private Map<String, String> strategySelected;
    private SearchRequest lastRequest;
    private CompanieList companieSelected;
    private History history;

    /**
     * default constructor, init model
     *
     * @since 3.0
     */
    public CvSearchModel() {
        super();
        skillList = new SkillList();
        applicantList = new ApplicantBuilder(new YamlDriver(".", true)).buildList();
        companieSelected = new CompanieList();
        strategySelected = new HashMap<String, String>();
        history = new History();
    }

    /**
     * Add a new companie
     *
     * @param c
     *         companie name
     *
     * @since 3.0
     */
    public void addCompanie(String c) {
        if (!companieSelected.contains(c)) {
            companieSelected.add(new Companie(c));
            setChanged();
            notifyObservers(ModelAction.ADD_COMPANIE);
        }
    }


    /**
     * remove a companie
     *
     * @param c
     *         companie name
     *
     * @since 3.0
     */
    public void removeCompanie(String c) {
        boolean exist = companieSelected.contains(c);
        if (exist) {

            companieSelected.remove(c);
            setChanged();
            notifyObservers(ModelAction.REMOVE_COMPANIE);
        }
    }


    public Companie getCompanieSelected() {
        return companieSelected.get(companieSelected.size() - 1);
    }

    public Map<String, String> getStrategySelected() {
        return strategySelected;
    }

    /**
     * select a new strategy
     *
     * @param id
     *         id of the combobox
     * @param n
     *         name of the selected strategy
     *
     * @since 3.0
     */
    public void addStrategie(String id, String n) {
        strategySelected.put(id, n);
    }

    public void notifyComboBoxUpdate() {
        setChanged();
        notifyObservers(ModelAction.UPDATE_SELECT);
    }

    /**
     * remove a  skill
     *
     * @param name
     *         skill name
     *
     * @since 3.0
     */
    public boolean removeSkill(String name) {
        boolean exist = skillList.contains(name);
        if (exist) {
            skillList.remove(name);
            setChanged();
            notifyObservers(ModelAction.REMOVE_SKILL);
            return true;
        } else {
            return false;
        }
    }

    /**
     * add a new skill
     *
     * @param name
     *         skill name
     *
     * @since 3.0
     */
    public boolean addSkill(String name) {
        Skill exist = getSkill(name);
        if (exist == null) {
            Skill n = new Skill(name);
            skillList.add(n);
            setChanged();
            notifyObservers(ModelAction.ADD_SKILL);
            return true;
        } else {
            return false;
        }
    }

    /**
     * get last skill
     *
     * @since 3.0
     */
    public Skill getLastSkill() {
        return skillList.get(skillList.size() - 1);
    }

    /**
     * get a skill by its name
     *
     * @param name
     *         skill name
     *
     * @since 3.0
     */
    public Skill getSkill(String name) {
        for (Skill s : skillList) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    /**
     * get a companie by its name
     *
     * @param name
     *         companie name
     *
     * @since 3.0
     */
    public Companie getCompanie(String name) {
        for (Companie s : companieSelected) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    public Strategy getStrategy(String stratName) {
        return this.strategieContainer.getStrategy(stratName);
    }

    public List<Strategy> getStrategies() {
        return null;
    }

    public String[] initStrategiesName() {
        return this.strategieContainer.getStrategiesNames();
    }

    public List<StrategyType> getStrategiesOfType(StrategyType strategyType) {
        return null;
    }

    public SkillList getSkillList() {
        return skillList;
    }

    public void setSkillList(SkillList skillList) {
        this.skillList = skillList;
    }

    public SearchRequest getLastRequest() {
        return lastRequest;
    }

    public ApplicantList getApplicantList() {
        return applicantList;
    }

    public void setApplicantList(ApplicantList applicantList) {
        this.applicantList = applicantList;
    }

    /**
     * set the result and notify the view
     *
     * @param request
     *         result request
     *
     * @since 3.0
     */
    public void setRequestResult(AbstractRequest request) {
        lastRequest = (SearchRequest) request;
        setChanged();
        notifyObservers(ModelAction.SEARCH);
    }


    /**
     * add a new request to history
     *
     * @param request
     *         request to add to history
     *
     * @since 3.0
     */
    public void addToHistory(List<AbstractRequest> request) {
        history.add(request);
        setChanged();
        notifyObservers(ModelAction.UPDATE_HISTORY);
    }

    /**
     * get all companie
     *
     * @since 3.0
     */
    public CompanieList getCompanies() {
        return companieSelected;
    }

    public History getHistory() {
        return history;
    }

    /**
     * clear skill list
     *
     * @since 3.0
     */
    public void clearSkills() {
        skillList.clear();
        setChanged();
        notifyObservers(ModelAction.CLEAR_SKILL);
    }

    /**
     * ask to the history to save
     *
     * @since 3.0
     */
    public void saveHistory() {
        history.save();
    }

    /**
     * load the history
     *
     * @since 3.0
     */
    public void readHistory() {
        history.restore();
        setChanged();
        notifyObservers(ModelAction.LOAD_HISTORY);
    }

    /**
     * clear companie list
     *
     * @since 3.0
     */
    public void clearCompanies() {
        companieSelected.clear();
        setChanged();
        notifyObservers(ModelAction.CLEAR_COMPANIE);
    }

    /**
     * clear history
     *
     * @since 3.0
     */
    public void clearHistory() {
        history.clear();
        setChanged();
        notifyObservers(ModelAction.UPDATE_HISTORY);
    }

    public List<Strategy> getStrategyType1() {
        return strategieContainer.getStrategyType1();
    }

    public List<Strategy> getStrategyType2() {
        return strategieContainer.getStrategyType2();
    }

    public void clearStrategySelected() {
        strategySelected.clear();
    }

    /**
     * get all strategy of type 2 ( implement type 2)
     *
     * @since 3.0
     */
    public String[] initStrategiesNameType2() {
        List<Strategy> strats = strategieContainer.getStrategyType2();
        String[] toreturn = new String[strats.size()];
        int i = 0;
        for (Strategy s : strats) {
            toreturn[i] = s.getName();
            i++;
        }
        return toreturn;
    }

    /**
     * get all strategy of type 1 ( implement type 1)
     *
     * @since 3.0
     */
    public String[] initStrategiesNameType1() {
        List<Strategy> strats = strategieContainer.getStrategyType1();
        String[] toreturn = new String[strats.size()];
        int i = 0;
        for (Strategy s : strats) {
            toreturn[i] = s.getName();
            i++;
        }
        return toreturn;
    }
}
