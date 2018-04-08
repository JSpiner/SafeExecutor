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

    @Test(expected = RuntimeException.class)
    public void constructorErrorTest(){
        new SafeExecutor();
    }
}