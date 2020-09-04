package fr.univ_lyon1.info.m1.cv_search.model.skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SkillList implements Iterable<Skill>, Serializable {
    private List<Skill> list = new ArrayList<Skill>();

    public SkillList() {
    }

    /**
     * Constructeur par copie.
     * @param list list of Skill
     */
    public SkillList(SkillList list) {
        for (Skill s : list) {
            try {
                this.list.add((Skill) s.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    public void add(Skill a) {
        list.add(a);
    }

    public int size() {
        return list.size();
    }

    public Skill get(int i) {
        return this.list.get(i);
    }

    public boolean contains(Skill s) {
        return this.list.contains(s);
    }

    /**
     * Contains the skill with name "name".
     * @param name bla
     * @return boolean
     */
    public boolean contains(String name) {
        for (Skill s : list) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(Skill s) {
        this.list.remove(s);
        return true;
    }

    /**
     * Doc.
     * @param s name of strategy
     * @return boolean
     */
    public boolean remove(String s) {
        Skill found = null;
        for (Skill c : list) {
            if (c.getName().equals(s)) {
                found = c;
                break;
            }
        }
        if (found != null) {
            this.list.remove(found);
        }

        return true;
    }

    @Override
    public Iterator<Skill> iterator() {
        return list.iterator();
    }

    /**
     * Clear the list of applicants.
     */
    public void clear() {
        list.clear();
    }

    /**
     * Sets the content of the applicant list.
     */
    public void setList(SkillList list) {
        this.list = list.list;
    }
}

