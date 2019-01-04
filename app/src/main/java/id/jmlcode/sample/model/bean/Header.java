package id.jmlcode.sample.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Jamal on 1/8/2018.
 */

public class Header implements Parcelable {

    private Integer id;
    private String name;
    private ArrayList childList;

    public Header() {
        super();
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

    public ArrayList getChildList() {
        return childList;
    }

    public void setChildList(ArrayList childList) {
        this.childList = childList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Header createFromParcel(Parcel in) {
            return new Header(in);
        }

        public Header[] newArray(int size) {
            return new Header[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeList(childList);
    }

    public Header(Parcel in) {
        childList = new ArrayList<>();
        id = in.readInt();
        name = in.readString();
        childList = in.readArrayList(Child.class.getClassLoader());
    }
}
