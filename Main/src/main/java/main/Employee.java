/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Nicolas
 */
public class Employee {
    private String name;
    private String role;    // "Teacher", "Student", or "Staff"
    private String dept;    // Department name as String (e.g., "Math", "Science", "Arts")
    
    public Employee(String name, String role, String dept) {
        this.name = name;
        this.role = role;
        this.dept = dept;
    }
    
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getDept() { return dept; }
    
    public void setName(String name) { this.name = name; }
    
    @Override
    public String toString() {
        return name + " (" + role + ", " + dept + ")";
    }
}
