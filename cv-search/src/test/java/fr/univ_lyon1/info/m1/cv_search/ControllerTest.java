package fr.univ_lyon1.info.m1.cv_search;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.request.SearchRequest;
import org.junit.Test;

public class ControllerTest {

    @Test
    public void testSearch() {
        Mockingbird mock = Data.mockingbird();

        mock.model().addSkill("c");

        // Strategy = GtFifty
        mock.model().addStrategie("99", mock.model().getStrategieContainer().getAllStrategy().get(0).getName());

        mock.model().setApplicantList(Data.buildApplList());

        mock.controller().search();

        SearchRequest res = mock.model().getLastRequest();
        ApplicantList al = res.getResult();

        assert (al.size() != 0);
        assert (al.get(0).getName().equals("Jean Marc"));

    }

    @Test
    public void testRemoveSkill() {
        Mockingbird mock = Data.mockingbird();
        mock.controller().addSkill("c");

        assert (mock.model().getSkillList().size() == 1);
        mock.controller().removeSkill("c");

        assert (mock.model().getSkillList().size() == 0);

    }

    @Test
    public void testAddSkill() {
        Mockingbird mock = Data.mockingbird();
        mock.controller().addSkill("");

        assert (mock.model().getSkillList().size() == 0);

        mock.controller().addSkill("c");
        assert (mock.model().getSkillList().get(0).getName().equals("c"));
    }


}
