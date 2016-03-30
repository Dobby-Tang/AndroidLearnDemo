package com.example.content.binderconnectionpooling;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    private static final String TAG = "BinderPoolService";

    private Binder mBinderPool = new BinderPool.BinderPoolImpl();

    @Override
    public IBinder onBind(Intent intent) {
       return mBinderPool;
    }
}
