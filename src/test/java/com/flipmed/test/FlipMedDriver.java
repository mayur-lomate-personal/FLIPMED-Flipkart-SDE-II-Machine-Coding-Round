package com.flipmed.test;

import com.flipmed.model.Doctor;
import com.flipmed.model.DoctorSpeciality;
import com.flipmed.model.Patient;
import com.flipmed.model.Slot;
import com.flipmed.service.SlotService;
import com.flipmed.util.CompareTimeAndDoctorStrategy;
import com.flipmed.util.CompareTimeStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;

public class FlipMedDriver {
    public static void main(String[] args) throws IOException {

        HashMap<String, Doctor> doctors = new HashMap<>();
        HashMap<String, Patient> patients = new HashMap<>();
        SlotService slotService = new SlotService(new CompareTimeAndDoctorStrategy());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int option;
        while(true) {
            System.out.println("1. registerDoc");
            System.out.println("2. registerPatient");
            System.out.println("3. addDoctorSlot");
            System.out.println("4. addPatientSlot");
            System.out.println("5. Exit");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException ex) {
                System.out.println("Invlaid Input");
                continue;
            }
            if(option == 5) {
                break;
            } else if (option == 1) {
                System.out.print("Enter Doctor Name : ");
                String name = br.readLine();
                if(doctors.containsKey(name)) {
                    System.out.println("Doctor already Present");
                }
                System.out.println("1. Cardiologist");
                System.out.println("2. Dermatologist");
                System.out.println("3. Orthopedic");
                System.out.println("4. General Physician");
                System.out.print("Enter Doctor Speciality : ");
                try {
                    option = Integer.parseInt(br.readLine());
                } catch (NumberFormatException ex) {
                    System.out.println("Invlaid Input");
                    continue;
                }
                if(option > 4 || option < 0) {
                    System.out.println("Invlaid Input");
                    continue;
                }
                String doctorSpeciality;
                if(option == 1) {
                    doctorSpeciality = DoctorSpeciality.Cardiologist.name();
                } else if (option == 2) {
                    doctorSpeciality = DoctorSpeciality.Dermatologist.name();
                } else if(option == 3) {
                    doctorSpeciality = DoctorSpeciality.Orthopedic.name();
                } else {
                    doctorSpeciality = DoctorSpeciality.General_Physician.name();
                }
                doctors.put(name, new Doctor(name, doctorSpeciality, new CompareTimeStrategy()));
            } else if (option == 2) {
                System.out.print("Enter Patient Name : ");
                String name = br.readLine();
                if(patients.containsKey(name)) {
                    System.out.println("Patient already Present");
                }
                patients.put(name, new Patient(name));
            } else if (option == 3) {
                System.out.print("Enter Doctor Name : ");
                String name = br.readLine();
                if(!doctors.containsKey(name)) {
                    System.out.println("Doctor not Present");
                    continue;
                }
                System.out.print("Enter Doctor Slot : ");
                String slot = br.readLine();
                slotService.addDoctorSlot(slot, doctors.get(name));
            } else {
                System.out.print("Enter Patient Name : ");
                String name = br.readLine();
                if(!patients.containsKey(name)) {
                    System.out.println("Patient not Present");
                    continue;
                }
                System.out.println("1. Cardiologist");
                System.out.println("2. Dermatologist");
                System.out.println("3. Orthopedic");
                System.out.println("4. General Physician");
                System.out.print("Enter Doctor Speciality : ");
                try {
                    option = Integer.parseInt(br.readLine());
                } catch (NumberFormatException ex) {
                    System.out.println("Invlaid Input");
                    continue;
                }
                if(option > 4 || option < 0) {
                    System.out.println("Invlaid Input");
                    continue;
                }
                String doctorSpeciality;
                if(option == 1) {
                    doctorSpeciality = DoctorSpeciality.Cardiologist.name();
                } else if (option == 2) {
                    doctorSpeciality = DoctorSpeciality.Dermatologist.name();
                } else if(option == 3) {
                    doctorSpeciality = DoctorSpeciality.Orthopedic.name();
                } else {
                    doctorSpeciality = DoctorSpeciality.General_Physician.name();
                }
                List<Slot> slots = slotService.getSlotsBySpeciality(doctorSpeciality);
                if(slots.isEmpty()) {
                    System.out.println("No Slots Available");
                    continue;
                }
                int i=1;
                for (Slot cSlot : slots) {
                    System.out.println(i + ". " + cSlot);
                    i++;
                }
                System.out.print("Enter Slot Number : ");
                try {
                    option = Integer.parseInt(br.readLine());
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input");
                    continue;
                }
                slotService.addPatientSlot(slots.get(option-1), patients.get(name));
            }
        }
    }
}
