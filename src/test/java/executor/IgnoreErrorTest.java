package net.jspiner.executor;

import executor.SafeExecutor;
import org.junit.Test;

public class IgnoreErrorTest {

    @Test(expected = RuntimeException.class)
    public void notIgnoreTest() {
        SafeExecutor.build()
                .add(() -> {
                            throw new RuntimeException("test error");
                        }
                ).run();
    }

    @Test
    public void ignoreExceptionTest() {
        SafeExecutor.build()
                .add(() -> {
                            throw new RuntimeException("test error");
                        }
                ).ignore()
                .run();
    }

    @Test
    public void ignoreNoExceptionTest() {
        SafeExecutor.build()
                .add(() -> doNothing())
                .ignore()
                .run();
    }

    @Test
    public void ignoreMultipleExceptionTest() {
        SafeExecutor.build()
                .add(() -> {
                    throw new RuntimeException("test error");
                })
                .add(() -> {
                    throw new RuntimeException("test error");
                })
                .add(() -> {
                    throw new RuntimeException("test error");
                })
                .add(() -> {
                    throw new RuntimeException("test error");
                })
                .ignore()
                .run();
    }

    private void doNothing() {
        // do nothing for test
    }

}
