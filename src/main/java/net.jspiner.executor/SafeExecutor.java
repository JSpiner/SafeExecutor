package net.jspiner.executor;

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
        private boolean ignore;

        private SafeExecutorBuilder() {
            executableList = new ArrayList<>();
        }

        public SafeExecutorBuilder add(Executable executable) {
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
            for (Executable executable : executableList) {
                try {
                    executable.execute();
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
