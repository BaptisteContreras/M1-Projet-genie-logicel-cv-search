package fr.univ_lyon1.info.m1.cv_search;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.companie.Companie;
import fr.univ_lyon1.info.m1.cv_search.model.companie.CompanieList;
import fr.univ_lyon1.info.m1.cv_search.model.request.AbstractRequest;
import fr.univ_lyon1.info.m1.cv_search.model.request.SearchRequest;
import fr.univ_lyon1.info.m1.cv_search.model.request.SearchRequestBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.skill.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.skill.SkillList;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.ClassicOption;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.GtFiftyStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.SortByExperience;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.Strategy;
import org.junit.Test;

import java.util.List;

public class RequestTest {

    @Test
    public void testBuildOption() {
        SkillList sk = new SkillList();
        sk.add(new Skill("c"));
        sk.add(new Skill("c++"));

        CompanieList cl = new CompanieList();
        cl.add(new Companie("Google"));

        ClassicOption co = new ClassicOption(sk, cl);

        assert (co.getCompanies().size() == 1);
        assert (co.getSkillList().size() == 2);

    }

    @Test
    public void testBuildOptionSkillEmpty() {
        SkillList sk = new SkillList();

        CompanieList cl = new CompanieList();
        cl.add(new Companie("Google"));

        ClassicOption co = new ClassicOption(sk, cl);

        SearchRequestBuilder srb = new SearchRequestBuilder(Data.strategyContainer());
        List<AbstractRequest> sr = srb.build(new String[]{"GtFifty"}, Data.buildApplList(), co);

        assert (sr.size() == 1);
        assert (((SearchRequest) sr.get(0)).getToApply().stratName.equals("GtFifty"));
    }

    @Test
    public void testBuildOptionCompanieEmpty() {
        SkillList sk = new SkillList();
        sk.add(new Skill("c"));
        sk.add(new Skill("c++"));

        CompanieList cl = new CompanieList();

        ClassicOption co = new ClassicOption(sk, cl);

        SearchRequestBuilder srb = new SearchRequestBuilder(Data.strategyContainer());
        List<AbstractRequest> sr = srb.build(new String[]{"GtFifty"}, Data.buildApplList(), co);

        assert (sr.size() == 1);
        assert (((SearchRequest) sr.get(0)).getToApply().stratName.equals("GtFifty"));
    }

    @Test
    public void testRequestOneStrategy() {
        SkillList sk = new SkillList();
        sk.add(new Skill("c"));
        sk.add(new Skill("c++"));

        CompanieList cl = new CompanieList();

        ClassicOption co = new ClassicOption(sk, cl);

        SearchRequestBuilder srb = new SearchRequestBuilder(Data.strategyContainer());
        List<AbstractRequest> sr = srb.build(new String[]{"GtFifty"}, Data.buildApplList(), co);

        assert (((SearchRequest) sr.get(0)).getToApply().stratName.equals("GtFifty"));

        sr.get(sr.size() - 1).callNext();

        assert (((SearchRequest) sr.get(0)).getResult().size() == 1);

        Strategy tested = new GtFiftyStrategy();
        ApplicantList resTested =  tested.filter(Data.buildApplList(), co);

        assert(resTested.size() == 1);
        for (int i = 0; i < resTested.size(); i++) {
            assert(resTested.get(i).getName().equals(((SearchRequest) sr.get(0)).getResult().get(i).getName()));
        }
    }

    @Test
    public void testRequestTwoStrategy() {
        SkillList sk = new SkillList();
        sk.add(new Skill("go"));

        CompanieList cl = new CompanieList();
        cl.add(new Companie("Django"));

        ClassicOption co = new ClassicOption(sk, cl);

        SearchRequestBuilder srb = new SearchRequestBuilder(Data.strategyContainer());
        List<AbstractRequest> sr = srb.build(new String[]{"Sort by Experience", "GtFifty"}, Data.buildApplList(), co);

        sr.get(sr.size() - 1).callNext();

        assert (((SearchRequest) sr.get(0)).getResult().size() == 2);
        assert (((SearchRequest) sr.get(0)).getResult().get(0).getName().equals("Jack"));
        assert (((SearchRequest) sr.get(0)).getResult().get(1).getName().equals("Jean Marc"));

        Strategy first = new GtFiftyStrategy();
        ApplicantList finalRes = new SortByExperience().filter(first.filter(Data.buildApplList(), co), co);

        assert(finalRes.size() == ((SearchRequest) sr.get(0)).getResult().size());

        for (int i = 0; i < finalRes.size(); i++) {
            assert(finalRes.get(i).getName().equals(((SearchRequest) sr.get(0)).getResult().get(i).getName()));
        }
    }

    @Test
    public void testRequestOneStrategySkillEmpty() {
        SkillList sk = new SkillList();

        CompanieList cl = new CompanieList();
        cl.add(new Companie("Google"));

        ClassicOption co = new ClassicOption(sk, cl);

        SearchRequestBuilder srb = new SearchRequestBuilder(Data.strategyContainer());
        List<AbstractRequest> sr = srb.build(new String[]{"GtFifty"}, Data.buildApplList(), co);

        sr.get(sr.size() - 1).callNext();

        assert (((SearchRequest) sr.get(0)).getResult().size() == 2);
        assert (((SearchRequest) sr.get(0)).getToApply().stratName.equals("GtFifty"));

        Strategy tested = new GtFiftyStrategy();
        ApplicantList resTested =  tested.filter(Data.buildApplList(), co);

        assert(resTested.size() == ((SearchRequest) sr.get(0)).getResult().size());
        assert(resTested.size() == 2);

        for (int i = 0; i < resTested.size(); i++) {
            assert(resTested.get(i).getName().equals(((SearchRequest) sr.get(0)).getResult().get(i).getName()));
        }
    }

    @Test
    public void testRequestOneStrategyCompanieEmpty() {
        SkillList sk = new SkillList();
        sk.add(new Skill("c"));
        sk.add(new Skill("c++"));

        CompanieList cl = new CompanieList();

        ClassicOption co = new ClassicOption(sk, cl);

        SearchRequestBuilder srb = new SearchRequestBuilder(Data.strategyContainer());
        List<AbstractRequest> sr = srb.build(new String[]{"GtFifty"}, Data.buildApplList(), co);

        sr.get(sr.size() - 1).callNext();

        assert (((SearchRequest) sr.get(0)).getResult().size() == 1);
        assert (((SearchRequest) sr.get(0)).getResult().get(0).getName().equals("Jean Marc"));

        Strategy tested = new GtFiftyStrategy();
        ApplicantList resTested =  tested.filter(Data.buildApplList(), co);

        assert(resTested.size() == ((SearchRequest) sr.get(0)).getResult().size());
        assert(resTested.size() == 1);

        for (int i = 0; i < resTested.size(); i++) {
            assert(resTested.get(i).getName().equals(((SearchRequest) sr.get(0)).getResult().get(i).getName()));
        }
    }


}
