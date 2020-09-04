package fr.univ_lyon1.info.m1.cv_search.model.applicant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApplicantList implements Iterable<Applicant>, Serializable {
    private List<Applicant> list = new ArrayList<Applicant>();

    public ApplicantList() {
    }

    public ApplicantList(List<Applicant> l) {
        this.list = l;
    }

    public void add(Applicant a) {
        list.add(a);
    }

    public int size() {
        return list.size();
    }

    public Applicant get(int i) {
        return list.get(i);
    }

    @Override
    public Iterator<Applicant> iterator() {
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
    public void setList(ApplicantList list) {
        this.list = list.list;
    }

    public List<Applicant> toList() {
        return this.list;
    }

}
