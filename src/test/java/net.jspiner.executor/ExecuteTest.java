import org.junit.Assert;
import org.junit.Test;

public class ExecuteTest {

    private int runCount;
    private int errorCount;

    @Test
    public void addExcutableNotErrorTest1() {
        SafeExecutor.build()
                .add(() -> {
                })
                .run();
    }

    @Test
    public void addExcutableNotErrorTest2() {
        SafeExecutor.build()
                .add(() -> {
                })
                .add(() -> {
                })
                .add(() -> {
                })
                .add(() -> {
                })
                .add(() -> {
                })
                .add(() -> {
                })
                .run();
    }

    @Test
    public void runMethodTest1() {
        runCount = 0;

        SafeExecutor.build()
                .add(() -> runCount++)
                .add(() -> runCount++)
                .run();

        Assert.assertEquals(
                2,
                runCount
        );
    }

    @Test
    public void runMethodTest2() {
        runCount = 0;

        SafeExecutor.build()
                .add(() -> runCount++)
                .add(() -> runCount++)
                .add(() -> runCount++)
                .add(() -> runCount++)
                .add(() -> runCount++)
                .add(() -> runCount++)
                .run();

        Assert.assertEquals(
                6,
                runCount
        );
    }

    @Test(expected = NullPointerException.class)
    public void errorNotImplementedTest1() {
        SafeExecutor.build()
                .add(() -> {
                    throw new RuntimeException("test error");
                })
                .run();
    }

    @Test(expected = NullPointerException.class)
    public void errorNotImplementedTest2() {
        SafeExecutor.build()
                .add(() -> {
                })
                .add(() -> {
                    throw new RuntimeException("test error");
                })
                .add(() -> {
                })
                .add(() -> {
                })
                .run();
    }

    @Test
    public void errorHandlingTest1() {
        errorCount = 0;
        runCount = 0;

        SafeExecutor.build()
                .add(() -> {
                    throw new RuntimeException("test error");
                })
                .onError(throwable -> errorCount++)
                .run();

        Assert.assertEquals(
                1,
                errorCount
        );
    }

    @Test
    public void errorHandlingTest2() {
        errorCount = 0;
        runCount = 0;

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
                .onError(throwable -> errorCount++)
                .run();

        Assert.assertEquals(
                4,
                errorCount
        );
    }

    @Test
    public void errorHandlingTest3() {
        errorCount = 0;
        runCount = 0;

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
                .onError(throwable -> errorCount++)
                .run();

        Assert.assertEquals(
                4,
                errorCount
        );
    }


}
