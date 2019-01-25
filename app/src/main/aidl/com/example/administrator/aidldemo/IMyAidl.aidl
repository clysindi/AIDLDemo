package com.example.administrator.aidldemo;

//创建接口 aidl 文件，IMyAidl.aidl
// Declare any non-default types here with import statements
import com.example.administrator.aidldemo.bean.Person;
//可以理解为通信媒介
interface IMyAidl {
    /**
     * 除了基本数据类型，其他类型的参数都需要标上方向类型：in(输入), out(输出), inout(输入输出)
     */

    void addPerson(in Person person);//添加Person

    List<Person> getPersonList();//获取Person 列表
}
