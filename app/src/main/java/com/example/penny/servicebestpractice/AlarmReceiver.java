package com.example.penny.servicebestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**构建一个Intent对象去启动LongRunningService这个服务
 * Created by penny on 2016/10/28.
 */

public class AlarmReceiver extends BroadcastReceiver {
   @Override
    public void onReceive(Context context, Intent intent){
       Intent i = new Intent(context,LongRunningService.class);
       context.startService(i);

   }
}
