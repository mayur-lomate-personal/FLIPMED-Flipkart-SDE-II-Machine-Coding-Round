package com.flipmed.util;

import com.flipmed.model.Slot;

import java.util.Comparator;

public class CompareTimeStrategy implements Comparator<Slot> {
    @Override
    public int compare(Slot slot1, Slot slot2) {
        return slot1.getStart()-slot2.getStart();
    }
}
