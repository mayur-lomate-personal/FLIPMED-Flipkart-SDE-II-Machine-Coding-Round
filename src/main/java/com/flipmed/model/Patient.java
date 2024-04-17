package com.flipmed.model;

import com.flipmed.util.CompareTimeStrategy;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class Patient {
    private final String name;
    private TreeSet<Slot> slots;

    public Patient(String name) {
        if(name == null || name.equals(" ")) {
            throw new IllegalArgumentException("Invalid Patient Name");
        }
        this.name = name;
        this.slots = new TreeSet<>(new CompareTimeStrategy());
    }

    public String getName() {
        return name;
    }

    public SortedSet<Slot> getSlots() {
        return Collections.unmodifiableSortedSet(slots);
    }

    public void addSlot(Slot slot) {
        if(slot == null || slots.contains(slot)) {
            throw new IllegalArgumentException("Slot Already present or Slot obj is null");
        }
        slots.add(slot);
    }
}
