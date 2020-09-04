package fr.univ_lyon1.info.m1.cv_search.model.applicant;

import fr.univ_lyon1.info.m1.cv_search.model.driver.Driver;
import fr.univ_lyon1.info.m1.cv_search.model.skill.Skill;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ApplicantBuilder {
    protected Driver driver;

    public ApplicantBuilder(Driver driver) {
        this.driver = driver;
    }


    /**
     * Create applicant form raw data
     *
     * @param applMap
     *         arg to read data
     *
     * @since 3.0
     * @return nw applicant
     */
    private Applicant buildFromMap(Map<String, Object> applMap) {
        Applicant a = new Applicant();

        a.setName((String) applMap.get("name"));

        Map<String, Integer> skills = (Map<String, Integer>) applMap.get("skills");

        for (String skill : skills.keySet()) {
            int value = skills.get(skill);
            a.setSkill(skill, value);
        }

        Map<String, String> experiences = (Map<String, String>) applMap.get("experience");
        ArrayList<Experience> experiencesList = new ArrayList<>();
        for (String name : experiences.keySet()) {
            Object l = experiences.get(name);
            LinkedHashMap<String, Object> ex = (LinkedHashMap<String, Object>) l;

            int start = (Integer) ex.get("start");
            int end = (Integer) ex.get("end");

            Object o = ex.get("keywords");
            ArrayList<String> keywords = (ArrayList<String>) o;
            ArrayList<Skill> skillsInExp = new ArrayList<Skill>();

            for (String sk : keywords) {
                skillsInExp.add(new Skill(sk));
            }
            experiencesList.add(new Experience(name, start, end, skillsInExp));

        }

        a.setExperiences(experiencesList);

        return a;

    }

    /**
     * Build a new applicant
     *
     *
     * @since 3.0
     */
    public Applicant build() {
        Applicant a = new Applicant();

        Map map = this.driver.read(null);

        a.setName((String) map.get("name"));

        return buildFromMap(map);
    }

    /**
     * Build a list of applicant from raw data
     *
     *
     * @since 3.0
     * @return List of applicant
     */
    public ApplicantList buildList() {

        ApplicantList applicants = new ApplicantList();

        for (Map<String, Object> map : this.driver.readAll()) {
            applicants.add(this.buildFromMap(map));
        }

        return applicants;

    }
}
