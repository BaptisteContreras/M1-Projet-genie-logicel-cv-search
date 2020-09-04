package fr.univ_lyon1.info.m1.cv_search.model.applicant;

import fr.univ_lyon1.info.m1.cv_search.model.skill.Skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;

public class Applicant implements Serializable {
    private Map<String, Integer> skills = new HashMap<String, Integer>();
    private ArrayList<Experience> experiences;
    private String name;


    /**
     * Create a new applicant
     *
     * @param skills
     *         skills of the applicant
     * @param experiences
     *         profesionnal experience of the applicant
     * @param name
     *         name of the applicant
     *
     * @since 3.0
     */
    public Applicant(Map<String, Integer> skills, ArrayList<Experience> experiences, String name) {
        this.skills = skills;
        this.experiences = experiences;
        this.name = name;
    }

    public Applicant() {
    }


    public int getSkill(String string) {
        return skills.getOrDefault(string, 0);
    }


    public void setSkill(String string, int value) {
        skills.put(string, value);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<String> getSkillsNames() {
        return skills.keySet();
    }


    public Map<String, Integer> getSkills() {
        return skills;
    }


    public ArrayList<Experience> getExperiences() {
        return this.experiences;
    }


    public void setExperiences(ArrayList<Experience> experiences) {
        this.experiences = experiences;
    }


    /**
     * Return all experience pro of the applicant for a given skill
     *
     * @param skillName
     *         skill to search
     *
     * @since 3.0
     * @return experience list
     */
    public List<Experience> hasProfesionnalExperienceIn(String skillName) {
        List<Experience> tmp = new ArrayList<Experience>();
        for (Experience e : experiences) {
            for (Skill s : e.getSkillsDuring()) {
                if (s.getName().equals(skillName)) {
                    tmp.add(e);
                }
            }
        }
        return tmp;
    }
}
