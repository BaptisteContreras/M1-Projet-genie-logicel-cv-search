package fr.univ_lyon1.info.m1.cv_search.model.request;

import java.io.Serializable;

public abstract class AbstractRequest implements Serializable {

    /**
     * Call the next request in the chain.
     *
     * @return AbstractRequest
     */
    public abstract AbstractRequest callNext();
}
