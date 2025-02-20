package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class DataParsing {

    /**
     * Reads a YAML file and retrieves the value associated with the given key.
     *
     * @param filePath the path to the YAML file.
     * @param key      the key to look up in the YAML file.
     * @return the value as a String.
     */
    public String readYml(String filePath, String key) {
        Yaml yaml = new Yaml();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (in == null) {
                throw new RuntimeException("File not found: " + filePath);
            }
            Map<String, Object> data = yaml.load(in);
            Object value = data.get(key);
            if (value != null) {
                return value.toString();
            } else {
                throw new RuntimeException("Key not found in YAML file: " + key);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading YAML file: " + filePath, e);
        }
    }

    /**
     * Reads a JSON file and returns a List of String values.
     * Assumes the JSON file contains an object with key-value pairs where values are strings.
     *
     * @param filePath the path to the JSON file.
     * @return a List of values extracted from the JSON object.
     */
    public List<String> readJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (in == null) {
                throw new RuntimeException("File not found: " + filePath);
            }
            Map<String, String> data = mapper.readValue(in, new TypeReference<Map<String, String>>() {});
            return new ArrayList<>(data.values());
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file: " + filePath, e);
        }
    }
}
