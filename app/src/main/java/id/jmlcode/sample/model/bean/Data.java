package id.jmlcode.sample.model.bean;

import java.util.ArrayList;

/**
 * Created by Jamal on 1/8/2018.
 */

public class Data {
    public static Header getData(){
        Header header = new Header();
        ArrayList<Child> childArrayList = new ArrayList<>();

        for(int i = 0 ; i < 5 ; i++) {
            Child child = new Child();
            child.setAddress("Bandung");
            child.setPhone("08997101542");
            childArrayList.add(child);
        }


        header.setId(1);
        header.setName("Widiyanto");
        header.setChildList(childArrayList);

        return header;
    }
}
