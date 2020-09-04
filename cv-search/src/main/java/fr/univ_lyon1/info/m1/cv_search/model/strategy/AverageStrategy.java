package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.skill.SkillList;

public abstract class AverageStrategy extends Strategy implements StrategyType1 {

    public AverageStrategy(String name) {
        super(name);
    }

    /**
     * Calcul the average.
     *
     * @param total bla
     * @param nbElement bla
     * @return double
     */
    protected double average(double total, int nbElement) {
        return total / nbElement;
    }

    /**
     * Calcul the skill's average for a given applicant.
     *
     * @param a applicant
     * @param skills bla
     * @return double
     */
    protected double averageApplicantSkill(Applicant a, SkillList skills) {
        int markForSkill = 0;
        int total = 0;
        boolean haveAllSkill = true;

        for (int i = 0; i < skills.size(); i++) {
            markForSkill = a.getSkill(skills.get(i).getName());
            if (markForSkill == 0) {
                haveAllSkill = false;
                break;
            } else {
                total = total + markForSkill;
            }
        }

        if (haveAllSkill) {
            return average(total, skills.size());
        } else {
            return -1;
        }
    }

}
