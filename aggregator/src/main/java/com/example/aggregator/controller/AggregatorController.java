package com.example.aggregator.controller;

import com.example.aggregator.model.Entry;
import com.example.aggregator.service.AggregatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AggregatorController {

    private static final Logger logger = LoggerFactory.getLogger(AggregatorController.class.getName());

    private final AggregatorService aggregatorService;

    public AggregatorController(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    @GetMapping("/")
    public List<Entry> index() {
        return List.of(aggregatorService.getDefinitionFor("hello"), aggregatorService.getDefinitionFor("world"));
    }

    @GetMapping("getDefinitionFor/{word}")
    public Entry getDefinitionFor(@PathVariable String word) {
        StopWatch sw = new StopWatch();

        sw.start();
        Entry entry = aggregatorService.getDefinitionFor(word);
        sw.stop();

        long nanoSeconds = sw.getTotalTimeNanos();
        String message = new StringBuilder().append("Retrieved entry for [").append(word).append("] in ").append(nanoSeconds / 1000000.0).append("ms").toString();
        logger.info(message);

        return entry;
    }

    @GetMapping("getWordsThatContainSuccessiveLettersAndStartsWith/{chars}")
    public List<Entry> getWordsThatContainSuccessiveLettersAndStartsWith(@PathVariable String chars) {
        StopWatch sw = new StopWatch();

        sw.start();
        List<Entry> entries = aggregatorService.getWordsThatContainSuccessiveLettersAndStartsWith(chars);
        sw.stop();

        long nanoSeconds = sw.getTotalTimeNanos();
        String message = new StringBuilder().append("Retrieved entries for [").append(chars).append("] in ").append(nanoSeconds / 1000000.0).append("ms").toString();
        logger.info(message);

        return entries;
    }

    @GetMapping("getWordsThatContain/{chars}")
    public List<Entry> getWordsThatContain(@PathVariable String chars) {
        StopWatch sw = new StopWatch();

        sw.start();
        List<Entry> entries = aggregatorService.getWordsThatContain(chars);
        sw.stop();

        long nanoSeconds = sw.getTotalTimeNanos();
        String message = new StringBuilder().append("Retrieved entries for [").append(chars).append("] in ").append(nanoSeconds / 1000000.0).append("ms").toString();
        logger.info(message);

        return entries;
    }

    @GetMapping("getAllPalindromes")
    public List<Entry> getAllPalindromes() {
        StopWatch sw = new StopWatch();

        sw.start();
        List<Entry> entries = aggregatorService.getAllPalindromes();
        sw.stop();

        long nanoSeconds = sw.getTotalTimeNanos();
        String message = new StringBuilder().append("Retrieved entries in ").append(nanoSeconds / 1000000.0).append("ms").toString();
        logger.info(message);

        return entries;
    }
}
