package com.example.administrator.aidldemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.administrator.aidldemo.IMyAidl;
import com.example.administrator.aidldemo.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created Service Process
 * 在其中实现了 AIDL 接口中定义的方法:
 */

public class MyAidlService extends Service {
    private final String TAG = this.getClass().getSimpleName();
    private ArrayList<Person> mPersons;

    /**
     * 创建生成的本地 Binder 对象，实现 AIDL 制定的方法
     *
     * //Person写下自己的电话
     */
    private IBinder mIBinder = new IMyAidl.Stub() {
        @Override
        public void addPerson(Person person) throws RemoteException {
            mPersons.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return mPersons;
        }
    };

    /**
     * 客户端与服务端绑定时的回调，返回 mIBinder 后客户端就可以通过它远程调用服务端的方法，即实现了通讯
     *
     * @param intent
     * @return
     * SM写下访问
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mPersons = new ArrayList<>();
        // LogUtils.d(TAG, "MyAidlService onBind");
        return mIBinder;
    }
}
