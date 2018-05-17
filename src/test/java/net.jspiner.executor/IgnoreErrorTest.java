package net.jspiner.executor;

import org.junit.Test;

public class IgnoreErrorTest {

    @Test(expected = RuntimeException.class)
    public void notIgnoreTest() {
        SafeExecutor.build()
                .add(() -> {
                            throw new RuntimeException("Error");
                        }
                ).run();
    }

    @Test
    public void ignoreExceptionTest() {
        SafeExecutor.build()
                .add(() -> {
                            throw new RuntimeException("Error");
                        }
                ).ignore()
                .run();

    }

}
