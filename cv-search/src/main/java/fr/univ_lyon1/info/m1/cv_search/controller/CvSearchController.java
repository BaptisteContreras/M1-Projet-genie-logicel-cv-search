package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.model.AbstractModel;
import fr.univ_lyon1.info.m1.cv_search.model.CvSearchModel;
import fr.univ_lyon1.info.m1.cv_search.model.companie.Companie;
import fr.univ_lyon1.info.m1.cv_search.model.companie.CompanieList;
import fr.univ_lyon1.info.m1.cv_search.model.request.AbstractRequest;
import fr.univ_lyon1.info.m1.cv_search.model.request.HistoryElement;
import fr.univ_lyon1.info.m1.cv_search.model.request.SearchRequest;
import fr.univ_lyon1.info.m1.cv_search.model.request.SearchRequestBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.skill.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.skill.SkillList;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.ClassicOption;

import java.util.List;

public class CvSearchController extends AbstractController {
    public CvSearchController(AbstractModel model) {
        super(model);
    }

    public void updateStrategies(String id, String v) {
        ((CvSearchModel) model).addStrategie(id, v);
    }

    /**
     * Add skill in selected list
     *
     * @param skillname name of the skill
     * @since 3.0
     */
    public void addSkill(String skillname) {
        if (skillname.strip().equals("")) {
            return; // Do nothing
        }
        ((CvSearchModel) model).addSkill(skillname);
    }


    /**
     * remove skill from selected list
     *
     * @param skillname name of the skill
     * @since 3.0
     */
    public void removeSkill(String skillname) {
        if (skillname.strip().equals("")) {
            return; // Do nothing
        }
        ((CvSearchModel) model).removeSkill(skillname.strip());
    }

    /**
     * remove companie from selected list
     *
     * @param cName name of the companie
     * @since 3.0
     */
    public void removeCompanie(String cName) {
        if (cName.strip().equals("")) {
            return; // Do nothing
        }
        System.out.println("send evet");
        ((CvSearchModel) model).removeCompanie(cName.strip());
    }

    /**
     * Apply search with given params and store it in the history
     *
     * @since 3.0
     */
    public void search() {
        String[] rawStrategies = getRawStrategies();

        if (rawStrategies.length == 0) {
            return;
        }

        // Init the builder
        SearchRequestBuilder requestBuilder = new SearchRequestBuilder(
                model.getStrategieContainer()
        );
        // Create the option for the request
        ClassicOption options = new ClassicOption(
                new SkillList(((CvSearchModel) model).getSkillList()),
                new CompanieList(((CvSearchModel) model).getCompanies())
        );
        // Build the request
        List<AbstractRequest> request = requestBuilder.build(
                rawStrategies,
                ((CvSearchModel) model).getApplicantList(),
                options
        );

        if (request == null) {
            return;
        }
        // First request is the last in the List
        request.get(request.size() - 1).callNext();
        ((CvSearchModel) model).addToHistory(request);
        ((CvSearchModel) model).setRequestResult(request.get(0));
    }

    /**
     * get raw skill (from selected strategy stored in the model)
     *
     * @return array of  strategy names
     * @since 3.0
     */
    public String[] getRawStrategies() {
        return ((CvSearchModel) model).getStrategySelected().values().toArray(new String[0]);
    }

    /**
     * add companie in selected list
     *
     * @param text name of the companie
     * @since 3.0
     */
    public void addCompanie(String text) {
        if (text.strip().equals("")) {
            return; // Do nothing
        }
        ((CvSearchModel) model).addCompanie(text.strip());
    }

    /**
     * Apply the history and get the result
     *
     * @param idBtn id of the button
     * @since 3.0
     */
    public void applyHistory(String idBtn) {
        int id = Integer.valueOf(idBtn.split("-")[1]);
        id--;

        HistoryElement historyElement = ((CvSearchModel) model).getHistory().getElem(id);
        // Clear previous lists
        ((CvSearchModel) model).clearSkills();
        ((CvSearchModel) model).clearCompanies();
        ((CvSearchModel) model).clearStrategySelected();
        int curentComboId = historyElement.size() > 1 ? 100 : 99;

        // get all strategy applied from the history element
        for (AbstractRequest r : historyElement.getEl()) {
            SearchRequest sr = (SearchRequest) r;
            ((CvSearchModel) model).addStrategie(
                    String.valueOf(curentComboId),
                    sr.getToApply().getName());

            curentComboId--;
        }
        ((CvSearchModel) model).notifyComboBoxUpdate();

        // get all the skills selected
        for (Skill s : ((SearchRequest) historyElement.get(0)).getOptions().getSkillList()) {
            ((CvSearchModel) model).addSkill(s.getName());
        }
        // get all the companie selected
        for (Companie s : ((SearchRequest) historyElement.get(0)).getOptions().getCompanies()) {
            System.out.println(s);
            ((CvSearchModel) model).addCompanie(s.getName());
        }
        // Set the result from the history
        ((CvSearchModel) model).setRequestResult(historyElement.get(0));


    }


    /**
     * save the history
     *
     * @since 3.0
     */
    public void saveHistory() {
        ((CvSearchModel) model).saveHistory();
    }


    /**
     * load the history
     *
     * @since 3.0
     */
    public void loadHistory() {
        ((CvSearchModel) model).readHistory();
    }


    public void clearHistory() {
        ((CvSearchModel) model).clearHistory();
    }
}
