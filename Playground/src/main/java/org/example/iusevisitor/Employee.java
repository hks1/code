package org.example.iusevisitor;

public abstract class Employee {
    private long id;
    private String name;

    public abstract void accept(EmployeeVisitor v);

    public Employee(long id, String name){
        this.id = id;
        this.name = name;
    }

    public Employee(){}

}

