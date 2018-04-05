package executor;

import org.junit.Assert;
import org.junit.Test;


public class SafeExecutorBuildTest {

    @Test
    public void build() {
        Object builder = SafeExecutor.build();

        Assert.assertTrue(
                builder instanceof SafeExecutor.SafeExecutorBuilder
        );
    }
}