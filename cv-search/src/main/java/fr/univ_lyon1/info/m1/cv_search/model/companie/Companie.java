package fr.univ_lyon1.info.m1.cv_search.model.companie;

import java.io.Serializable;

public class Companie implements Cloneable, Serializable {
    private String name;

    public Companie(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return name.equals(((Companie) o).name);
    }
}
