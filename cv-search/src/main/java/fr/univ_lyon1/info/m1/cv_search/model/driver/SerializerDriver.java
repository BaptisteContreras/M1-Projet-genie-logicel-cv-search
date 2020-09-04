package fr.univ_lyon1.info.m1.cv_search.model.driver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * Use java serialization
 *
 * @since 3.0
 */
public class SerializerDriver extends Driver {

    String file;


    public SerializerDriver(String fileName) {
        this.file = fileName;
    }


    @Override
    public Map<String, Object> read(Object arg) {
        FileInputStream file = null;
        ObjectInputStream in = null;
        try {
            file = new FileInputStream(this.file);
            in = new ObjectInputStream(file);
            Map<String, Object> toReturn = (Map<String, Object>) in.readObject();
            in.close();
            file.close();
            return toReturn;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;


    }

    @Override
    public ArrayList<Map<String, Object>> readAll() {
        return null;
    }

    @Override
    public boolean write(Map<String, Object> obj) {
        ObjectOutputStream out = null;
        FileOutputStream file = null;

        try {
            file = new FileOutputStream(this.file);
            out = new ObjectOutputStream(file);
            out.writeObject(obj);
            out.close();
            file.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean writeList(ArrayList<Map<String, Object>> listObj) {
        return false;
    }
}
