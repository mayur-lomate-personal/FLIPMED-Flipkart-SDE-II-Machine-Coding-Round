package com.flipmed.util;

import com.flipmed.model.Slot;

import java.util.Comparator;

public class CompareTimeAndDoctorStrategy implements Comparator<Slot>  {

    @Override
    public int compare(Slot o1, Slot o2) {
        if(o1.getStart() != o2.getStart()) {
            return o1.getStart()-o2.getStart();
        }
        return o1.getDoctor().getName().compareTo(o2.getDoctor().getName());
    }
}
