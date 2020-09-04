package fr.univ_lyon1.info.m1.cv_search.model.driver;

import java.util.ArrayList;
import java.util.Map;

public abstract class Driver {


    /**
     * Read an object on the appropriate support and format data
     *
     * @param arg
     *         arg to read data
     *
     * @since 3.0
     * @return raw data
     */
    public abstract Map<String, Object> read(Object arg);

    /**
     * Read  objects on the appropriate support and format data
     *
     *
     * @since 3.0
     * @return data
     */
    public abstract ArrayList<Map<String, Object>> readAll();

    /**
     * Write given object to the appropriate support
     *
     * @param obj
     *         data to write
     *
     * @since 3.0
     * @return success
     */
    public abstract boolean write(Map<String, Object> obj);

    /**
     * Write several objects on support
     *
     * @param listObj
     *         objects to write
     *
     * @since 3.0
     * @return success
     */
    public abstract boolean writeList(ArrayList<Map<String, Object>> listObj);

}
