import java.util.ArrayList;

public class SafeExecutor {

    public SafeExecutor(){
        throw new RuntimeException("create SafeExecutor() is not allowed. use SafeExecutor.build();");
    }

    public static SafeExecutorBuilder build() {
        return new SafeExecutorBuilder();
    }

    public static class SafeExecutorBuilder {

        private ArrayList<Executable> executableList;
        private ErrorListener errorListener;

        private SafeExecutorBuilder() {
            executableList = new ArrayList<>();
        }

        public SafeExecutorBuilder add(Executable executable) {
            executableList.add(executable);
            return this;
        }

        public SafeExecutorBuilder onError(ErrorListener errorListener) {
            this.errorListener = errorListener;
            return this;
        }

        public void run() {
            for (Executable executable : executableList) {
                try {
                    executable.execute();
                } catch (Exception error) {
                    executeError(error);
                }
            }
        }

        private void executeError(Throwable error) {
            if (errorListener == null) {
                throw new NullPointerException("error listener is not implemented");
            }
            errorListener.onError(error);
        }

    }
}
