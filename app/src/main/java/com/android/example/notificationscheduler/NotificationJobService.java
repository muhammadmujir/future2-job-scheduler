/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.example.notificationscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;


/**
 * The Service that JobScheduler runs once the conditions are met.
 * In this case it posts a notification.
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NotificationJobService extends JobService {

    private JobParameters mJobParameters;
    private boolean isJobFinished = false;
    /**
     * Called by the system once it determines it is time to run the job.
     *
     * @param jobParameters Contains the information about the job.
     * @return Boolean indicating whether or not the job was offloaded to a
     * separate thread.
     * In this case, it is false since the notification can be posted on
     * the main thread.
     */
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        isJobFinished = false;
        mJobParameters = jobParameters;
        new AsyncTaskImpl().execute(this);
        return true;
    }

    /**
     * Called by the system when the job is running but the conditions are no
     * longer met.
     * In this example it is never called since the job is not offloaded to a
     * different thread.
     *
     * @param jobParameters Contains the information about the job.
     * @return Boolean indicating whether the job needs rescheduling.
     */
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if (isJobFinished)
            return false;
//        Toast.makeText().show();
        return true;
    }

    public void notifyJobFinished(){
        jobFinished(mJobParameters, true);
        isJobFinished = true;
    }
}
