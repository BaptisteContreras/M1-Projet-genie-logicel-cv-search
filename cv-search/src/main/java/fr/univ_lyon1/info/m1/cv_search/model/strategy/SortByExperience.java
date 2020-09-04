package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByExperience extends Strategy implements StrategyType2 {


    public SortByExperience() {
        super("Sort by Experience");
    }

    /*
    On Trie les Applicants de celui qui a le plus d'experience a celui qui en a le moins
     */
    @Override
    public ApplicantList filter(ApplicantList appList, StrategyOption options) {

        Comparator<Applicant> comparator = new Comparator<Applicant>() {

            @Override
            public int compare(Applicant applicant, Applicant t1) {
                return Integer.compare(
                        t1.getExperiences().size(),
                        applicant.getExperiences().size()
                );
            }
        };

        List<Applicant> l = appList.toList();
        Collections.sort(l, comparator);

        return new ApplicantList(l);
    }
}
