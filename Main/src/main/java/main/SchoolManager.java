/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Nicolas
 */
public class SchoolManager {
    private String name;
    private Department department;
    private String title;  // "Principal", "VicePrincipal", "HeadOfDepartment"
    
    public SchoolManager(String name, Department department, String title) {
        this.name = name;
        this.department = department;
        this.title = title;
    }
    
    public String getName() { return name; }
    public Department getDepartment() { return department; }
    public String getTitle() { return title; }
    
    @Override
    public String toString() {
        return title + " " + name + " (" + department + ")";
    }
}
