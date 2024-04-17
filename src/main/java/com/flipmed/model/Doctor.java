package com.flipmed.model;


import com.flipmed.util.CompareTimeStrategy;

import java.util.*;

public class Doctor {

    private final String name;
    private final DoctorSpeciality speciality;
    private TreeSet<Slot> slots;
    private TreeSet<Slot> bookedSlots;

    public Doctor(String name, String speciality, Comparator<Slot> comparator) {
        this.speciality = DoctorSpeciality.valueOf(speciality);
        if(this.speciality == null || name == null || name.equals("")) {
            throw new IllegalArgumentException("Invalid Speciality");
        }
        this.name = name;
        this.slots = new TreeSet<>(new CompareTimeStrategy());
        this.bookedSlots = new TreeSet<>(new CompareTimeStrategy());
    }

    public DoctorSpeciality getSpeciality() {
        return speciality;
    }

    public void addSlot(final Slot slot) {
        if(slot == null) {
            throw new IllegalArgumentException("Invalid Slot");
        }
        slots.add(slot);
    }

    public SortedSet<Slot> getSlots() {
        return Collections.unmodifiableSortedSet(slots);
    }

    public SortedSet<Slot> getBookedSlots() {
        return Collections.unmodifiableSortedSet(bookedSlots);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor)) return false;
        Doctor doctor = (Doctor) o;
        return name.equals(doctor.name) && speciality == doctor.speciality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, speciality);
    }
}
