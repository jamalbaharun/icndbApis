package id.jmlcode.sample.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jamal on 1/8/2018.
 */

public class Child implements Parcelable {
    private String address;
    private String phone;

    public Child() {
        super();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Child createFromParcel(Parcel in) {
            return new Child(in);
        }

        public Child[] newArray(int size) {
            return new Child[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(phone);
    }

    public Child(Parcel in) {
        address = in.readString();
        phone = in.readString();

    }
}
