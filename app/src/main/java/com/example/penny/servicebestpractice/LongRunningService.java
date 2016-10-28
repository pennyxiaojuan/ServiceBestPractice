package com.example.penny.servicebestpractice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;

/**
 * Created by penny on 2016/10/28.
 */

public class LongRunningService extends Service{
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        //在onStartCommmand的方法里开启了一个子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("LongRunningService","executed at"+ new Date().toString());
            }
        }).start();
        //获取了AlarmManager实例，定义触发时间，使用PendingIntent指定处理定时任务的的广播接收器
        //为AlarmReceiver,最后调用set方法完成设定
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 60*60*1000;//这是一个小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() +anHour;
        Intent i = new Intent(this,AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent,flags,startId);

         }
}
