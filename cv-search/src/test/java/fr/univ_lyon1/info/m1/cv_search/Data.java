package fr.univ_lyon1.info.m1.cv_search;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.Experience;
import fr.univ_lyon1.info.m1.cv_search.model.skill.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.StrategyContainer;

import java.util.ArrayList;

public class Data {

    public static StrategyContainer strategyContainer() {
        return new StrategyContainer();
    }

    public static Mockingbird mockingbird() {
        return new Mockingbird();
    }

    public static ApplicantList buildApplList() {

        Applicant a1 = new Applicant();

        a1.setName("Jean Marc");
        a1.setSkill("c", 65);
        a1.setSkill("c++", 50);
        a1.setSkill("go", 55);

        ArrayList<Experience> exp = new ArrayList<>();
        ArrayList<Skill> sks = new ArrayList<>();

        sks.add(new Skill("c"));
        sks.add(new Skill("c++"));
        exp.add(new Experience("Google", 2013, 2015, sks));

        a1.setExperiences(exp);

        Applicant a2 = new Applicant();

        a2.setName("Jack");
        a2.setSkill("java", 90);
        a2.setSkill("c", 40);
        a2.setSkill("c++", 45);
        a2.setSkill("python", 70);
        a2.setSkill("go", 60);

        ArrayList<Experience> exp2 = new ArrayList<>();
        ArrayList<Skill> sks2 = new ArrayList<>();

        sks2.add(new Skill("java"));
        exp2.add(new Experience("UCBL", 2009, 2019, sks2));

        ArrayList<Skill> sk3 = new ArrayList<>();
        sk3.add(new Skill("python"));
        exp2.add(new Experience("Django", 2000, 2008, sk3));

        a2.setExperiences(exp2);

        ApplicantList appl = new ApplicantList();
        appl.add(a1);
        appl.add(a2);

        return appl;
    }
}
