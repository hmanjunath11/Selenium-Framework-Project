package util;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import models.Config;


public class YamlReader {
    private String filePath;

    public YamlReader(String filePath) {
        this.filePath = filePath;
    }

    public Config readConfig() {
    	Constructor constructor = new Constructor(Config.class);
		Yaml yaml = new Yaml( constructor );
        return yaml.loadAs(FileReader.read(filePath), Config.class);
    }
}