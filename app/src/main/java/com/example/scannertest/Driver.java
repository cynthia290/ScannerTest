package com.example.scannertest;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigInteger;

public class Driver implements Parcelable {

    Integer id;
    String name;
    String license_number;

    public  Driver(Integer id, String name, String license_number){
        this.id = id;
        this.name = name;
        this.license_number = license_number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel d, int i) {
        d.writeInt(id);
        d.writeString(name);
        d.writeString(license_number);

    }

    public static final Parcelable.Creator<Driver> CREATOR = new Parcelable.Creator<Driver>() {
        public Driver createFromParcel(Parcel parcel) {
            return new Driver(parcel);
        }

        public Driver[] newArray(int size) {
            return new Driver[size];
        }
    };

    private Driver(Parcel d) {
        id = d.readInt();
        name = d.readString();
        license_number = d.readString();
    }
}
