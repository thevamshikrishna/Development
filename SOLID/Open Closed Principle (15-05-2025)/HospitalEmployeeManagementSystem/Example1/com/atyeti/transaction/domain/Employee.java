package com.atyeti.transaction.domain;

public abstract class Employee {
    private int id;
    private String name;
    private String department;
    private boolean working;

    public Employee(int id, String name, String department, boolean working) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.working = working;
    }

    public abstract void performDuties();

    @Override
    public String toString() {
        return "Employee{" +
                "department='" + department + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", working=" + working +
                '}';
    }
}
