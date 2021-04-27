package id.generatator.base;

import java.util.List;

public interface IDGenerator {

    // Get next id number
    public Long nextId();

    // Get a range of sequential id numbers
    public List<Long> getIdRange(int count);
}
