package com.atyeti.transaction.reporting;


import com.atyeti.transaction.domain.Employee;

public class EmployeeReportFormatter extends ReportFormatter {

    public EmployeeReportFormatter(Employee employee, FormateType formateType) {
        super(employee, formateType);
    }

    public String getFormattedEmployee(){
        return getFormattedValue();
    }

}
