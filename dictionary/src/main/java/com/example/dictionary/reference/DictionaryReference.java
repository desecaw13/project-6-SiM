package com.example.dictionary.reference;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DictionaryReference {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryReference.class.getName());

    private static Map<String, String> dictionary;

    static {
        try {
            readDictionaryFile();
        } catch (JsonProcessingException e) {
            System.err.println("Error reading dictionary file");
        }
    }

    private DictionaryReference() {
    }

    private static void readDictionaryFile() throws JsonProcessingException {
        StopWatch sw = new StopWatch();
        sw.start();

        InputStream inputStream = DictionaryReference.class.getClassLoader().getResourceAsStream("dictionary.json");
        assert inputStream != null;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String json = bufferedReader.lines().collect(Collectors.joining("\n"));

        ObjectMapper objectMapper = new ObjectMapper();
        dictionary = objectMapper.readValue(json, Map.class);

        sw.stop();
        long totalTime = sw.getTotalTimeMillis();
        String message = new StringBuilder().append("Dictionary created with ").append(dictionary.size()).append(" entries in ").append(totalTime).append("ms").toString();
        logger.info(message);
    }

    public static Map<String, String> getDictionary() {
        return DictionaryReference.dictionary;
    }

}
