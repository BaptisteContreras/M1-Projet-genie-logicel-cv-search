package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.skill.SkillList;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SortByAverage extends AverageStrategy implements StrategyType1 {

    public SortByAverage() {
        super("Sort By Average");
    }

    @Override
    public ApplicantList filter(ApplicantList appList, StrategyOption options) {
        SkillList skillsName = ((ClassicOption) options).getSkillList();
        ApplicantList result = new ApplicantList();
        Stream<Applicant> stream = StreamSupport.stream(appList.spliterator(), false);
        stream = stream.sorted((applicant1, applicant2) -> {
            int res = 0;
            double preres = averageApplicantSkill(applicant1, skillsName);
            preres -= averageApplicantSkill(applicant2, skillsName);

            if (preres < 0) {
                res = (int) Math.floor(preres);
            } else {
                res = (int) Math.ceil(preres);

            }
            return res;
        });
        stream.forEachOrdered(result::add);
        return result;
    }
}
