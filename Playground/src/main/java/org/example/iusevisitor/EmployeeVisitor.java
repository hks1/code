package org.example.iusevisitor;

interface EmployeeVisitor {
    public  void visit(HourlyEmployee he);
    public void visit(SalariedEmployee se);
}
