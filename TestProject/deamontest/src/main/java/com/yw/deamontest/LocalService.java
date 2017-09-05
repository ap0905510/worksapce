package com.yw.deamontest;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class LocalService extends Service {

    public static final String TAG = "LocalService";
    LocalServiceBinder localBinder;
    LocalServiceConn localServiceConn;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化
        localBinder = new LocalServiceBinder();
        localServiceConn = new LocalServiceConn();
        LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class),
                localServiceConn, Context.BIND_IMPORTANT);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Notification notification;
        Notification.Builder builder = new Notification.Builder(this);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setContentTitle("YW");
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setWhen(System.currentTimeMillis());
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pi);
        notification = builder.build();
        startForeground(startId, notification);
        startTimer();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    // 绑定Service接口
    public class LocalServiceConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            // 进程销毁时释放资源 - 再启动拉起
            LocalService.this.startService(new Intent(LocalService.this, RemoteService.class));
            LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class),
                    localServiceConn, Context.BIND_IMPORTANT);
        }
    }

    public class LocalServiceBinder extends IServiceAidlInterface.Stub {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }

    private Timer timer;
    private TimerTask timerTask;
    private static int counter = 0;

    public void startTimer() {
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 1000, 1000);
    }

    public void stopTimerTask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void initializeTimerTask() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.e(TAG, "int timer +++++" + (counter++));
            }
        };
    }

}
