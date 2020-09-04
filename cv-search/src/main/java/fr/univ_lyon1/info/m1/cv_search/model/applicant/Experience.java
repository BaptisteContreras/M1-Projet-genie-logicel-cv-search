package fr.univ_lyon1.info.m1.cv_search.model.applicant;

import fr.univ_lyon1.info.m1.cv_search.model.skill.Skill;

import java.io.Serializable;
import java.util.ArrayList;

public class Experience implements Serializable {
    private String name;
    private int start;
    private int end;
    private ArrayList<Skill> skillsDuring;

    /**
     * Constructor to create a new Experience
     *
     * @param name
     *         name of the companie
     * @param start
     *         start of the experience
     * @param end
     *         end of the experience
     * @param skills
     *          skills used
     *
     * @since 3.0
     */
    public Experience(String name, int start, int end, ArrayList<Skill> skills) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.skillsDuring = skills;
    }

    public String getName() {
        return name;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }


    public int getDuration() {
        int duration = Integer.valueOf(end) - Integer.valueOf(start);
        return duration > 0 ? duration : 1;
    }


    public ArrayList<Skill> getSkillsDuring() {
        return skillsDuring;
    }

    public int getTime() {
        return this.end - this.start;
    }
}
