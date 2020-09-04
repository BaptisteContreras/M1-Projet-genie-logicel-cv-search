package fr.univ_lyon1.info.m1.cv_search.model.driver;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Read in write in Yaml file
 *
 * @since 3.0
 */
public class YamlDriver extends Driver {

    File file;
    File tmpFile;

    public YamlDriver(File file) {
        this.tmpFile = file;
    }

    public YamlDriver(File file, boolean isDir) {
        this.file = file;
    }

    public YamlDriver(String fileName) {
        this.tmpFile = new File(fileName);
    }

    public YamlDriver(String fileName, boolean isDir) {
        this.file = new File(fileName);
    }

    @Override
    public Map<String, Object> read(Object arg) {
        Map<String, Object> map;
        Yaml yaml = new Yaml();

        try {
            map = yaml.load(new FileInputStream(tmpFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Error(e);
        }

        return map;
    }

    @Override
    public ArrayList<Map<String, Object>> readAll() {

        ArrayList<Map<String, Object>> mapList = new ArrayList<>();
        for (File f : file.listFiles()) {
            if (f.isFile() && f.getName().endsWith(".yaml")) {
                tmpFile = f;
                mapList.add(this.read(null));
            }
        }

        return mapList;
    }

    @Override
    public boolean write(Map<String, Object> obj) {
        return false;
    }

    @Override
    public boolean writeList(ArrayList<Map<String, Object>> listObj) {
        return false;
    }

}
