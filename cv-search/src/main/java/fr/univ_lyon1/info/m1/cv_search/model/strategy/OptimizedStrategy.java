package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.Experience;
import fr.univ_lyon1.info.m1.cv_search.model.skill.SkillList;

import java.util.List;
import java.util.Map;

public class OptimizedStrategy extends Strategy implements StrategyType1, StrategyType2 {
    public OptimizedStrategy() {
        super("optimized strat");
    }

    @Override
    public ApplicantList filter(ApplicantList appList, StrategyOption options) {
        // Our goal with this algorithm is to get the perfect applicant for a given
        // list of required skills. For this, we give a great bonus if a skill of an applicant is a
        // required one and if the applicant has some pro experience whit it.
        // We also apply a malus on the other skills, we really want to focus on the required one
        // BUT we think that sometimes the other skills can make the difference that's why we keep
        // them to calculate the final score. This algorithm can be improved by considering more
        // information from the user.
        SkillList skillsRequired = ((ClassicOption) options).getSkillList();
        double bestScore = -1;
        Applicant bestApplicant = null;
        ApplicantList result = new ApplicantList();
        double score = 0;
        double coefPro = 1.;
        double coefReq = 1.;
        double stepPro = 0.5;
        for (Applicant a : appList) {
            System.out.println("For : " + a.getName());
            score = 0;
            List<Experience> appExp = a.getExperiences();
            Map<String, Integer> skills = a.getSkills();
            for (String s : skills.keySet()) {
                coefPro = 1.;
                coefReq = skillsRequired.contains(s) ? 2 : 0.3;
                // Apply malus if the skill is not required
                // and a great bonus if the skill is required
                // These modification really make the difference
                List<Experience> expPro = a.hasProfesionnalExperienceIn(s);
                if (!expPro.isEmpty()) {
                    for (Experience ex : expPro) {
                        // Add bonus if the skill has been used in a professional experience
                        coefPro += ex.getDuration() * stepPro;
                    }
                }

                System.out.println("CoefPRo " + coefPro);
                System.out.println("CoefReq " + coefReq);
                System.out.println("Skill " + s);
                score += a.getSkill(s) * coefPro * coefReq;

            }

            System.out.println("Score " + score);
            System.out.println("--------------------------------------------");
            if (score > bestScore) {
                bestApplicant = a;
                bestScore = score;
            }

        }
        if (bestApplicant != null) {
            result.add(bestApplicant);
        }
        return result;
    }
}
