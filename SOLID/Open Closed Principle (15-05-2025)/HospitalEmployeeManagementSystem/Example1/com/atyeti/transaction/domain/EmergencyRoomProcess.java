package com.atyeti.transaction.domain;


public class EmergencyRoomProcess {
    public static void main(String[] args) {
        HospitalManagement ERDirector=new HospitalManagement();
        Employee  peggy=new Nurse(1,"Peggy","emergency",true);

        ERDirector.callupon(peggy);

        Employee suzan=new Doctor(2,"Suzan","emergency",true);

        ERDirector.callupon(suzan);
    }
}
