package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.Experience;
import fr.univ_lyon1.info.m1.cv_search.model.companie.CompanieList;

public class CompanieStrategy extends Strategy implements StrategyType2 {
    public CompanieStrategy() {
        super("Companie name");
    }

    @Override
    public ApplicantList filter(ApplicantList appList, StrategyOption options) {
        CompanieList companieList = ((ClassicOption) options).getCompanies();
        if (companieList == null || companieList.size() == 0) {
            return appList;
        }
        ApplicantList result = new ApplicantList();
        for (Applicant a : appList) {
            for (Experience e : a.getExperiences()) {
                if (companieList.contains(e.getName())) {
                    result.add(a);
                    break;
                }
            }
        }
        return result;
    }
}
