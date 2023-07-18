package org.example.iusevisitor;

public class QtdHoursAndPayReport implements EmployeeVisitor{
    public void visit(HourlyEmployee he){
        // generate the line of the report
    }
    public void visit(SalariedEmployee se){} // do nothing
}
