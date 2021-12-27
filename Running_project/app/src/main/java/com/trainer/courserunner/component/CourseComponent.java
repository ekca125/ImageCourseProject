package com.trainer.courserunner.component;

import android.os.AsyncTask;

import java.util.function.Consumer;

/*
    DB관련작업을 수행한 후에 주 쓰레드로 넘어가서 UI를 변경해야 한다.
    작업쓰레드 -> 주쓰레드
*/
public abstract class CourseComponent {
    Consumer<Object> finishEventConsumer;

    public CourseComponent() {
        finishEventConsumer = o -> {
        };
    }

    protected abstract Object runInWorkThread();

    protected void runInUiThread(Object object) {
        finishEventConsumer.accept(object);
    }

    public void runComponent() {
        new CourseComponentAsyncTask().execute();
    }

    public Consumer<Object> getFinishEventConsumer() {
        return finishEventConsumer;
    }

    public void setFinishEventConsumer(Consumer<Object> finishEventConsumer) {
        this.finishEventConsumer = finishEventConsumer;
    }

    private class CourseComponentAsyncTask extends AsyncTask<Void, Void, Object> {
        @Override
        protected Object doInBackground(Void... voids) {
            return runInWorkThread();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            runInUiThread(o);
        }
    }
}
