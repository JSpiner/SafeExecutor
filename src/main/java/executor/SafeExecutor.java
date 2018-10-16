package executor;

import java.util.ArrayList;

public final class SafeExecutor {

    public SafeExecutor(){
        throw new RuntimeException("create SafeExecutor() is not allowed. use SafeExecutor.build();");
    }

    public static SafeExecutorBuilder build() {
        return new SafeExecutorBuilder();
    }

    public static final class SafeExecutorBuilder {

        private ArrayList<Runnable> executableList;
        private ErrorListener errorListener;
        private boolean ignore;

        private SafeExecutorBuilder() {
            executableList = new ArrayList<>();
        }

        public SafeExecutorBuilder add(Runnable executable) {
            executableList.add(executable);
            return this;
        }

        public SafeExecutorBuilder onError(ErrorListener errorListener) {
            this.ignore = false;
            this.errorListener = errorListener;
            return this;
        }

        public SafeExecutorBuilder ignore() {
            this.ignore = true;
            return this;
        }

        public void run() {
            for (Runnable executable : executableList) {
                try {
                    executable.run();
                } catch (Exception error) {
                    if (!ignore) {
                        executeError(error);
                    }
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
