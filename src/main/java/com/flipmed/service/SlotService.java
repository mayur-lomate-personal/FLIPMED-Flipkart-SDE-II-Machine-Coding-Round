package com.flipmed.service;

import com.flipmed.model.Doctor;
import com.flipmed.model.DoctorSpeciality;
import com.flipmed.model.Patient;
import com.flipmed.model.Slot;
import com.flipmed.util.CompareTimeAndDoctorStrategy;
import com.flipmed.util.CompareTimeStrategy;

import java.util.*;

public class SlotService {

    private HashMap<DoctorSpeciality, TreeSet<Slot>> slots = new HashMap<>();

    public SlotService(Comparator<Slot> slotComparator) {
        for(DoctorSpeciality speciality : DoctorSpeciality.values()) {
            slots.put(speciality, new TreeSet<>(slotComparator));
        }
    }

    public final java.util.List<Slot> getSlotsBySpeciality(String speciality) {
        DoctorSpeciality doctorSpeciality = DoctorSpeciality.valueOf(speciality);
        if(speciality == null) {
            throw new IllegalArgumentException("Invalid Doctor Speciality");
        }

        return Collections.unmodifiableList(new ArrayList<>(slots.get(doctorSpeciality)));
    }

    public final void addDoctorSlot(String slot, Doctor doctor) {
        Slot newSlot = new Slot(slot, doctor);
        doctor.addSlot(newSlot);
        this.slots.get(doctor.getSpeciality()).add(newSlot);
    }

    public void addPatientSlot(Slot slot, Patient patient) {
        for(Slot patientSlot : patient.getSlots()) {
            if(patientSlot.getStart() == slot.getStart() && patientSlot.getEnd() == slot.getEnd()) {
                throw new IllegalArgumentException("Slot Already Occupied");
            }
        }
        if(!slots.get(slot.getDoctor().getSpeciality()).contains(slot)) {
            throw new IllegalArgumentException("Slot doesn't Exist");
        }
        slots.get(slot.getDoctor().getSpeciality()).remove(slot);
        patient.addSlot(slot);
    }
}
