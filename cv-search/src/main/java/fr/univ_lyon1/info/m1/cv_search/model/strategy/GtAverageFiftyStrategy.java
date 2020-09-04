package fr.univ_lyon1.info.m1.cv_search.model.strategy;


import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.skill.SkillList;

public class GtAverageFiftyStrategy extends AverageStrategy
        implements StrategyType1, StrategyType2 {

    public GtAverageFiftyStrategy() {
        super("GtAverageFifty");
    }


    @Override
    public ApplicantList filter(ApplicantList appList, StrategyOption options) {
        SkillList skillsName = ((ClassicOption) options).getSkillList();
        ApplicantList good = new ApplicantList();

        for (Applicant appl : appList) {
            if (averageApplicantSkill(appl, skillsName) > 50) {
                good.add(appl);
            }
        }
        return good;

    }
}
