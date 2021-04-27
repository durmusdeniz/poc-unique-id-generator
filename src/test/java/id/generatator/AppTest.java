package id.generatator;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppTest 
{

    @Test
    public void testSequentiality() {
        IDGeneratorImpl gen = new IDGeneratorImpl();

        Long num1 = gen.nextId();
        Long num2 = gen.nextId();

        Assert.assertTrue(num2 > num1);

    }

    @Test
    public void testSequentialityForRange() {
        IDGeneratorImpl gen = new IDGeneratorImpl();
        List<Long> numbers = gen.getIdRange(50);

        for(int i = 1; i < 50; i++){
            Assert.assertTrue((numbers.get(i-1)+1)  == numbers.get(i));
        }


    }

    @Test
    public void testWithExecutor() {
        IDGeneratorImpl gen = new IDGeneratorImpl();
        IDGeneratorImpl gen2 = new IDGeneratorImpl();
        ExecutorService executor = Executors.newCachedThreadPool();
        HashSet<Long> createdNumbers = new HashSet<>();
        try {
            executor.submit(() -> gen.getIdRange(10)).get().forEach(l -> {
                Assert.assertTrue(!createdNumbers.contains(l));
            });
            executor.submit(() -> gen2.getIdRange(5)).get().forEach(l -> {
                Assert.assertTrue(!createdNumbers.contains(l));
            });
            Assert.assertTrue(!createdNumbers.contains(executor.submit(() -> gen.nextId()).get()));
            executor.submit(() -> gen.getIdRange(7)).get().forEach(l -> {
                Assert.assertTrue(!createdNumbers.contains(l));
            });
            executor.submit(() -> gen.getIdRange(10)).get().forEach(l -> {
                Assert.assertTrue(!createdNumbers.contains(l));
            });
            executor.submit(() -> gen.getIdRange(5)).get().forEach(l -> {
                Assert.assertTrue(!createdNumbers.contains(l));
            });
            Assert.assertTrue(!createdNumbers.contains(executor.submit(() -> gen.nextId()).get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }



}
