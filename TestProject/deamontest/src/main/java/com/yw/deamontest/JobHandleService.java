package com.yw.deamontest;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * Created by yw on 2017/8/27.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobHandleService extends JobService {

    private int jobId = 0x0008;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        scheduleJob(getJobInfo());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        // 用于唤醒进程
        boolean isLocalServiceWorking = AppUtils.isServiceWork(this, "com.yw.deamontest.LocalService");
        boolean isRemoteServiceWorking = AppUtils.isServiceWork(this, "com.yw.deamontest.RemoteService");
        if (!isLocalServiceWorking || !isRemoteServiceWorking) {
            this.startService(new Intent(this, LocalService.class));
            this.startService(new Intent(this, RemoteService.class));
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }

    private void scheduleJob(JobInfo job) {
        JobScheduler js = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        js.schedule(job);
    }

    private JobInfo getJobInfo() {
        JobInfo.Builder builder = new JobInfo.Builder(jobId, new ComponentName(
                this, JobHandleService.class));
        builder.setPersisted(true); // 手机重启
        builder.setPeriodic(100); // 100ms监听一次启动一次
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY); // 任何网络都执行
        builder.setRequiresCharging(false); // 不在乎是否充电
        builder.setRequiresDeviceIdle(false);
        return builder.build();
    }
}
