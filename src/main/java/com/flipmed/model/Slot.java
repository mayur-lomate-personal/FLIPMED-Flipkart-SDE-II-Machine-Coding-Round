package com.flipmed.model;

import java.util.Objects;

public class Slot {
    private final int start;
    private final int end;

    private final Doctor doctor;

    public Slot(String slotTime, Doctor doctor) {
        try {
            this.start = Integer.parseInt(slotTime.substring(0, slotTime.indexOf(':')));
            this.end = Integer.parseInt(slotTime.substring(slotTime.indexOf('-')+1, slotTime.lastIndexOf(':')));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid Slot Time");
        }

        if(start>=end || end-start > 1 || doctor == null) {
            throw new IllegalArgumentException("Invalid Slot Time");
        }

        this.doctor = doctor;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Dr." + this.doctor.getName() +" : "+ this.start + ":00-" + this.end + ":00" ;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    @Override
    public boolean equals(Object slot) {
        if(slot instanceof Slot) {
            if(((Slot)slot).getStart() == this.start && ((Slot)slot).getEnd() == this.end && ((Slot)slot).getDoctor() == this.doctor) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, doctor);
    }
}
