package com.example.administrator.aidldemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 创建要操作的实体类，实现 Parcelable 接口，以便序列化/反序列化
 * Parcelable 包
 * 实现 Parcelable 接口是为了后序跨进程通信时使用。
 */

public class Person implements Parcelable {
    private String mName;

    public Person(String mName) {
        this.mName = mName;
    }

    protected Person(Parcel in) {
        mName = in.readString();
    }

    //创造者
    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "mName='" + mName + '\'' +
                '}';
    }
}
