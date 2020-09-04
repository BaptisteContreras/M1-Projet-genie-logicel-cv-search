package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.skill.SkillList;

public abstract class GtStrategy extends Strategy implements StrategyType1 {
    public GtStrategy(String name) {
        super(name);
    }

    /**
     * Check if all skill are greater than a given value.
     *
     * @param appList bla
     * @param options bla
     * @param n greater than n
     * @return ApplicantList
     */
    public ApplicantList applyGt(ApplicantList appList, StrategyOption options, int n) {
        SkillList skillsName = ((ClassicOption) options).getSkillList();
        ApplicantList tmpList = new ApplicantList();
        for (Applicant a : appList) {
            boolean selected = true;
            for (int i = 0; i < skillsName.size(); i++) {
                if (a.getSkill(skillsName.get(i).getName()) < n) {
                    selected = false;
                    break;
                }
            }
            if (selected) {
                tmpList.add(a);
            }

        }
        return tmpList;
    }
}
