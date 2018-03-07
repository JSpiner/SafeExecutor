package test;

import executor.SafeExecutor;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SafeExecutorBuildTest {

    @Test
    public void build() {
        Object builder = SafeExecutor.build();

        Assert.assertTrue(
                builder instanceof SafeExecutor.SafeExecutorBuilder
        );
    }
}