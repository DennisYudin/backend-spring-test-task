package com.ineric.entities;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class PassageOptions {
    private Integer depth;
    private String mask;
    private Consumer<List<String>> results;

    public PassageOptions(){

    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public void setResults(Consumer<List<String>> results) {
        this.results = results;
    }


    public Consumer<List<String>> getResults() {
        return results;
    }


    public Integer getDepth() {
        return depth;
    }


    public String getMask() {
        return mask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassageOptions that = (PassageOptions) o;
        return depth.equals(that.depth) &&
                mask.equals(that.mask) &&
                results.equals(that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depth, mask, results);
    }
}
