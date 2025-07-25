package com.atyeti.transaction.domain;

public class Doctor extends Employee{
    public Doctor(int id, String name, String department, boolean working) {
        super(id, name, department, working);
        System.out.println(    "Docker in action ....");
    }

    @Override
    public void performDuties() {
        prescribeMedicine();
        diagnosePatients();
    }

    // Doctor
    private void diagnosePatients() {
        System.out.println("Diagnosing Patients");
    }

    private void prescribeMedicine() {
        System.out.println("prescribe Medicine");
    }

}
