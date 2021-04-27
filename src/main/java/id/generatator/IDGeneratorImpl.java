package id.generatator;

import id.generatator.base.IDGenerator;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IDGeneratorImpl implements IDGenerator {

    //Using Atomic variable to deal with concurrency rather than going with synchronized blocks.
    private static AtomicLong SEQ_HEAD = new AtomicLong(0);


    @Override
    public Long nextId() {
        return SEQ_HEAD.incrementAndGet();
    }

    @Override
    public List<Long> getIdRange(int count) {
        long start = SEQ_HEAD.incrementAndGet();
        SEQ_HEAD.addAndGet(count-1);
        //Using Streams to leverage the lazy-loding/creating
        return Stream
                .iterate(start, i -> i + 1)
                .limit(count)
                .collect(Collectors.toList());
    }
}
