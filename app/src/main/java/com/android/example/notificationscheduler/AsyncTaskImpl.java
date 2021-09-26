package com.android.example.notificationscheduler;

import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class AsyncTaskImpl extends AsyncTask<NotificationJobService, NotificationJobService, NotificationJobService> {

    @Override
    protected NotificationJobService doInBackground(NotificationJobService... notificationJobServices) {
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return notificationJobServices[0];
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onPostExecute(NotificationJobService notificationJobService) {
        notificationJobService.notifyJobFinished();
    }
}
