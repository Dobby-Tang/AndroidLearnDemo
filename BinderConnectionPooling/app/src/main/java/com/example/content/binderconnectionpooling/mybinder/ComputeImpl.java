package com.example.content.binderconnectionpooling.mybinder;

import android.os.RemoteException;
import android.support.v4.text.ICUCompat;

import com.example.content.binderconnectionpooling.ICompute;

/**
 * Created by user on 16/3/30.
 */
public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a+b;
    }
}
