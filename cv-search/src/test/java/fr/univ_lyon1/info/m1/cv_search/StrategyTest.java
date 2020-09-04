package fr.univ_lyon1.info.m1.cv_search;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.companie.Companie;
import fr.univ_lyon1.info.m1.cv_search.model.companie.CompanieList;
import fr.univ_lyon1.info.m1.cv_search.model.skill.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.skill.SkillList;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.*;
import org.junit.Test;

public class StrategyTest {

    @Test
    public void testGtAverageFiftyStrategy() {

        GtAverageFiftyStrategy strategy = new GtAverageFiftyStrategy();
        SkillList sk = new SkillList();
        sk.add(new Skill("c"));
        sk.add(new Skill("c++"));

        ClassicOption opt = new ClassicOption(sk, new CompanieList());

        ApplicantList res = strategy.filter(Data.buildApplList(), opt);

        assert (res.size() == 1);
        assert (res.get(0).getName().equals("Jean Marc"));

    }

    @Test
    public void testGtFiftyStrategy() {
        GtFiftyStrategy strategy = new GtFiftyStrategy();
        SkillList sk = new SkillList();
        sk.add(new Skill("java"));

        ClassicOption opt = new ClassicOption(sk, new CompanieList());

        ApplicantList res = strategy.filter(Data.buildApplList(), opt);

        assert (res.size() == 1);
        assert (res.get(0).getName().equals("Jack"));
    }

    @Test
    public void testGtSixtyStrategy() {
        GtSixtyStrategy strategy = new GtSixtyStrategy();
        SkillList sk = new SkillList();
        sk.add(new Skill("c"));
        ClassicOption opt = new ClassicOption(sk, new CompanieList());

        ApplicantList res = strategy.filter(Data.buildApplList(), opt);

        assert (res.size() == 1);
        assert (res.get(0).getName().equals("Jean Marc"));

    }

    @Test
    public void testOrderedByExperience() {
        SortByExperience strategy = new SortByExperience();

        ApplicantList res = strategy.filter(Data.buildApplList(), new ClassicOption(new SkillList(), new CompanieList()));

        assert (res.size() == 2);
        assert (res.get(0).getName().equals("Jack"));
        assert (res.get(1).getName().equals("Jean Marc"));

    }

    @Test
    public void testOrderedByAverage() {
        SortByAverage strategy = new SortByAverage();

        SkillList sk = new SkillList();
        sk.add(new Skill("python"));
        sk.add(new Skill("c"));
        sk.add(new Skill("c++"));
        ClassicOption opt = new ClassicOption(sk, new CompanieList());

        ApplicantList res = strategy.filter(Data.buildApplList(), opt);

        assert (res.size() == 2);
        assert (res.get(0).getName().equals("Jean Marc"));
        assert (res.get(1).getName().equals("Jack"));

    }

    @Test
    public void testCompanieStrategy() {
        CompanieStrategy strategy = new CompanieStrategy();

        CompanieList cl = new CompanieList();
        cl.add(new Companie("Google"));
        ClassicOption opt = new ClassicOption(new SkillList(), cl);

        ApplicantList res = strategy.filter(Data.buildApplList(), opt);

        assert (res.size() == 1);
        assert (res.get(0).getName().equals("Jean Marc"));

    }

    @Test
    public void testStrategyOpti() {
        // Dans ce test Jean Marc a les 3 skills demandé et seulement 2 ans d'expérience dans
        // 2 d'entre eux. Jack a beaucoup plus de skill que Jean Marc, il a aussi plus d'expérience
        // pro MAIS seulement dans un skill demandé
        // L'algorithme va attribuer un score de 557.0 à Jack et 570.0 à Jean Marc.
        // On voit que l'expérience pro à énormément boosté le score comparé au skill auxiliaire.
        // On peut aussi remarquer que les scores sont proches. Nous voulons que les
        // skills non demandé puissent aussi compter dans le score, mais avec un gros malus pour ne
        // pas trop influencer le résultat si un candidat à de nombreux skills non demandé.
        // Ce qui est dommage c'est que cette approche pénalise les personnes très diversifié et
        // avantage les candidats étant très spécialisé.
        OptimizedStrategy strategy = new OptimizedStrategy();

        SkillList sk = new SkillList();
        sk.add(new Skill("go"));
        sk.add(new Skill("c"));
        sk.add(new Skill("c++"));
        ClassicOption opt = new ClassicOption(sk, new CompanieList());

        ApplicantList res = strategy.filter(Data.buildApplList(), opt);

        assert (res.size() == 1);
        assert (res.get(0).getName().equals("Jean Marc"));

    }

}
