package org.example.iusevisitor;


public class HourlyEmployee extends Employee{
    public void accept(EmployeeVisitor v){
        v.visit(this);
    }
}
