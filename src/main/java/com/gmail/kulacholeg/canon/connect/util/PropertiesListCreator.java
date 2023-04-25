package com.gmail.kulacholeg.canon.connect.util;

import com.gmail.kulacholeg.canon.connect.entity.PropertyEntity;

import java.util.ArrayList;
import java.util.List;

public class PropertiesListCreator {

    public static List<PropertyEntity> createList(String ip, String email, String pattern){
        List<PropertyEntity> list = new ArrayList<>();

        list.add(new PropertyEntity("canon_ip", ip));
        list.add(new PropertyEntity("receiver_email", email));
        list.add(new PropertyEntity("request_time_pattern", pattern));

        return list;
    }
}
