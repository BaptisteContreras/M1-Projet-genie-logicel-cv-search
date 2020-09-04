package fr.univ_lyon1.info.m1.cv_search.model.companie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompanieList implements Iterable<Companie>, Serializable {
    private List<Companie> list = new ArrayList<Companie>();

    public CompanieList() {
    }


    /**
     * copy constructor
     *
     * @param list
     *         list to copy
     *
     * @since 3.0
     */
    public CompanieList(CompanieList list) {
        for (Companie s : list) {
            try {
                this.list.add((Companie) s.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    public void add(Companie a) {
        list.add(a);
    }

    public int size() {
        return list.size();
    }

    public Companie get(int i) {
        return this.list.get(i);
    }

    /**
     * check if name is in list
     *
     * @param name
     *         name to search
     *
     * @since 3.0
     */
    public boolean contains(String name) {
        for (Companie s : list) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(Companie s) {
        return this.list.contains(s);
    }

    @Override
    public Iterator<Companie> iterator() {
        return list.iterator();
    }

    /**
     * Remove a companie given by name
     *
     * @param s
     *         name to remove
     *
     * @since 3.0
     */
    public boolean remove(String s) {
        Companie toRemove = null;
        for (Companie c : list) {
            if (c.getName().equals(s)) {
                toRemove = c;
            }
        }
        if (toRemove != null) {
            this.list.remove(toRemove);
        }
        return true;
    }


    public boolean remove(Companie s) {
        this.list.remove(s);
        return true;
    }


    /**
     * Clear the list of applicants.
     */
    public void clear() {
        list.clear();
    }

}

