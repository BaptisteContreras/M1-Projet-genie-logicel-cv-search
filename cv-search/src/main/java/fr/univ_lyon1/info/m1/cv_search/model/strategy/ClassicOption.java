package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.companie.CompanieList;
import fr.univ_lyon1.info.m1.cv_search.model.skill.SkillList;

public class ClassicOption extends StrategyOption {

    private SkillList skillList;
    private CompanieList companies;

    public ClassicOption(SkillList skillList, CompanieList companies) {
        this.skillList = skillList;
        this.companies = companies;
    }


    public SkillList getSkillList() {
        return skillList;
    }

    public void setSkillList(SkillList skillList) {
        this.skillList = skillList;
    }

    public CompanieList getCompanies() {
        return companies;
    }

    public void setCompanies(CompanieList companies) {
        this.companies = companies;
    }
}
