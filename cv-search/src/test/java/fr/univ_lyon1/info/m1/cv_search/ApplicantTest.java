package fr.univ_lyon1.info.m1.cv_search;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.driver.YamlDriver;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApplicantTest {

    @Test
    public void testReadApplicant() {
        // Given
        ApplicantBuilder builder = new ApplicantBuilder(new YamlDriver("./applicant1.yaml"));

        // When
        Applicant a = builder.build();

        // Then
        assertEquals(70, a.getSkill("c++"));
        assertEquals("John Smith", a.getName());
    }

    @Test
    public void testReadManyApplicant() {
        // Given
        ApplicantBuilder builder = new ApplicantBuilder(new YamlDriver(".", true));

        // When
        ApplicantList list = builder.buildList();

        // Then
        boolean johnFound = false;
        for (Applicant a : list) {
            if (a.getName().equals("John Smith")) {
                assertEquals(90, a.getSkill("c"));
                assertEquals(70, a.getSkill("c++"));
                johnFound = true;
            }
        }

        assertTrue(johnFound);
    }
}
