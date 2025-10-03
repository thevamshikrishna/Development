package com.atyeti.transaction.domain;

public class Nurse extends Employee{
    public Nurse(int id, String name, String department, boolean working) {
        super(id, name, department, working);
        System.out.println(    "Nurse in action ....");
    }

    @Override
    public void performDuties() {
        checkVitalSigns();
        drawBlood();
        cleanPatientArea();
    }

    private void cleanPatientArea() {
        System.out.println("Cleaning Patient Area");
    }

    private void drawBlood() {
        System.out.println("Draw Blood");
    }

    private void checkVitalSigns() {
        System.out.println("Checking Vital Signs");
    }

}
