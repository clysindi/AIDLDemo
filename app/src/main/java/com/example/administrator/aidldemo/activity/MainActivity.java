package com.example.administrator.aidldemo.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.aidldemo.IMyAidl;
import com.example.administrator.aidldemo.R;
import com.example.administrator.aidldemo.bean.Person;
import com.example.administrator.aidldemo.service.MyAidlService;

import java.util.List;
import java.util.Random;

//客户端
public class MainActivity extends AppCompatActivity {

    private IMyAidl myAidl;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = findViewById(R.id.name);

        //2.接着绑定服务
        Intent intent = new Intent(getApplicationContext(), MyAidlService.class);
        //要执行 IPC，必须使用 bindService() 将应用绑定到服务上。
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    //1.实现 ServiceConnection 接口，在其中拿到 AIDL 类
    //在 Activity 中创建一个服务连接对象，在其中调用 IMyAidl.Stub.asInterface() 方法将 Binder 转为 AIDL 类。
    private ServiceConnection mConnection = new ServiceConnection() {//服务器连接
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myAidl = IMyAidl.Stub.asInterface(iBinder);
            //连接后拿到 Binder，转换成 AIDL，在不同进程会返回个代理
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myAidl=null;

        }
    };

    //3.拿到 AIDL 类后，就可以调用 AIDL 类中定义好的操作，进行跨进程请求
    public void addPerson(View view) {
       Random random =new Random();
       Person person =new Person ("nihao"+random.nextInt(10));
       try {
           myAidl.addPerson(person);
           List<Person> personList = myAidl.getPersonList();
           tvName.setText(personList.toString());
       } catch (RemoteException e) {
           e.printStackTrace();
       }
   }

}
