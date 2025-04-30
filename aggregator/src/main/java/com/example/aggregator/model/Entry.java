package com.example.aggregator.model;

import org.springframework.lang.NonNull;

import java.util.Comparator;
import java.util.Objects;

public class Entry implements Comparable<Entry> {

    private String word;
    private String definition;

    public Entry(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entry entry)) return false;
        return Objects.equals(word, entry.word) && Objects.equals(definition, entry.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, definition);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Entry{");
        sb.append("word='").append(word).append('\'');
        sb.append(", definition='").append(definition).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(@NonNull Entry other) {
        Comparator<Entry> comparator = Comparator.comparing(Entry::getWord).thenComparing(Entry::getDefinition);
        return comparator.compare(this, other);
    }
}
