/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import java.util.Scanner;

/**
 *
 * @author Nicolas
 */
public class Main {
    
    // Enum for menu options — studied in class
    private static enum MenuOption {
        SORT, SEARCH, ADD, HIERARCHY, EXIT
    }
    
    // Data storage
    private static Employee[] employees = new Employee[100];
    private static int employeeCount = 0;
    private static BinaryTree schoolTree = new BinaryTree();
    
    // Pre-defined school managers
    private static SchoolManager[] managers = {
        new SchoolManager("Dr. Smith", Department.MATH, "Principal"),
        new SchoolManager("Ms. Johnson", Department.SCIENCE, "VicePrincipal"),
        new SchoolManager("Mr. Williams", Department.ARTS, "HeadOfDepartment")
    };
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        prePopulateEmployees();
        
        MenuOption option;
        do {
            option = displayMenuAndGetChoice(scanner);
            switch (option) {
                case SORT:     handleSort(); break;
                case SEARCH:   handleSearch(scanner); break;
                case ADD:      handleAdd(scanner); break;
                case HIERARCHY: handleHierarchy(); break;
                case EXIT:     System.out.println("System closed."); break;
            }
        } while (option != MenuOption.EXIT);
        
        scanner.close();
    }
    
    /**
     * Loads 22 employees into the system.
     * Cycles through Teacher, Student, Staff roles and
     * three departments (MATH, SCIENCE, ARTS).
     * Each employee is added to both the array and the binary tree.
     */
    private static void prePopulateEmployees() {
        String[] names = {"John", "Mary", "Peter", "Anna", "Mike", "Sarah",
                          "David", "Emma", "James", "Lisa", "Robert", "Karen",
                          "Michael", "Nancy", "William", "Sandra", "Joseph", "Betty",
                          "Charles", "Helen", "Thomas", "Donna"};
        String[] roles = {"Teacher", "Student", "Staff"};
        Department[] depts = {Department.MATH, Department.SCIENCE, Department.ARTS};
        
        for (int i = 0; i < names.length; i++) {
            String role = roles[i % 3];
            String dept = depts[i % 3].toString();
            Employee emp = new Employee(names[i], role, dept);
            employees[employeeCount] = emp;
            employeeCount++;
            schoolTree.insert(emp);
        }
        System.out.println("Loaded " + names.length + " employees into the school system.");
    }
    
    /**
     * Shows the menu, reads user input, and returns the selected option.
     * Uses try-catch for NumberFormatException validation.
     * Recursively calls itself on invalid input (validates without crashing).
     */
    private static MenuOption displayMenuAndGetChoice(Scanner scanner) {
        System.out.println("\n===== SCHOOL SYSTEM MENU =====");
        System.out.println("1 - Sort Employees");
        System.out.println("2 - Search Employee");
        System.out.println("3 - Add Employee");
        System.out.println("4 - Display Hierarchy");
        System.out.println("5 - Exit");
        System.out.print("Choose (1-5): ");
        
        String input = scanner.nextLine();
        try {
            int choice = Integer.parseInt(input.trim());
            switch (choice) {
                case 1: return MenuOption.SORT;
                case 2: return MenuOption.SEARCH;
                case 3: return MenuOption.ADD;
                case 4: return MenuOption.HIERARCHY;
                case 5: return MenuOption.EXIT;
                default:
                    System.out.println("ERROR: Choose 1-5 only.");
                    return displayMenuAndGetChoice(scanner);
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Enter a number, not text.");
            return displayMenuAndGetChoice(scanner);
        }
    }
    
    /**
     * Sorts employees by name using recursive QuickSort.
     * Creates a copy of the array so the original stays unchanged.
     */
    private static void handleSort() {
        if (employeeCount == 0) {
            System.out.println("No employees to sort.");
            return;
        }
        // Copy array to keep original order
        Employee[] sorted = new Employee[employeeCount];
        for (int i = 0; i < employeeCount; i++) {
            sorted[i] = employees[i];
        }
        // Sort using recursive QuickSort
        SortingSearching.quickSort(sorted, 0, employeeCount - 1);
        
        System.out.println("\n===== SORTED EMPLOYEES (by name) =====");
        for (int i = 0; i < employeeCount; i++) {
            System.out.println((i + 1) + ". " + sorted[i]);
        }
    }
    
    /**
     * Searches for an employee by name using recursive BinarySearch.
     * The array must be sorted first, so we sort a copy before searching.
     */
    private static void handleSearch(Scanner scanner) {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine().trim();
        
        // Validate: name cannot be empty
        if (name.isEmpty()) {
            System.out.println("ERROR: Name cannot be empty.");
            return;
        }
        if (employeeCount == 0) {
            System.out.println("No employees in system.");
            return;
        }
        
        // Copy and sort the array (binary search requires sorted data)
        Employee[] sorted = new Employee[employeeCount];
        for (int i = 0; i < employeeCount; i++) {
            sorted[i] = employees[i];
        }
        SortingSearching.quickSort(sorted, 0, employeeCount - 1);
        
        // Search using recursive BinarySearch
        int index = SortingSearching.binarySearch(sorted, 0, employeeCount - 1, name);
        
        if (index == -1) {
            System.out.println("Employee \"" + name + "\" not found.");
        } else {
            System.out.println("Found: " + sorted[index]);
        }
    }
    
    /**
     * Adds a new employee to the system.
     * Validates: name not empty, role choice 1-3.
     * Creates employee and adds to both array and binary tree.
     */
    private static void handleAdd(Scanner scanner) {
        // Read and validate name
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("ERROR: Name cannot be empty.");
            return;
        }
        if (employeeCount >= 100) {
            System.out.println("ERROR: System is full (max 100).");
            return;
        }
        
        // Read and validate role
        System.out.println("Select role:");
        System.out.println("1 - Teacher");
        System.out.println("2 - Student");
        System.out.println("3 - Staff");
        System.out.print("Choose (1-3): ");
        
        String roleInput = scanner.nextLine().trim();
        int roleChoice;
        try {
            roleChoice = Integer.parseInt(roleInput);
            if (roleChoice < 1 || roleChoice > 3) {
                System.out.println("ERROR: Choose 1-3 only.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Enter a number.");
            return;
        }
        
        // Map choice to role string
        String role;
        if (roleChoice == 1) {
            role = "Teacher";
        } else if (roleChoice == 2) {
            role = "Student";
        } else {
            role = "Staff";
        }
        
        // Assign to MATH department by default (for simplicity)
        Employee emp = new Employee(name, role, "MATH");
        employees[employeeCount] = emp;
        employeeCount++;
        schoolTree.insert(emp);
        System.out.println("Employee added: " + emp);
    }
    
    /**
     * Displays the employee hierarchy tree in level-order (BFS).
     * Shows how employees are organized in the binary tree.
     */
    private static void handleHierarchy() {
        System.out.println("\n===== EMPLOYEE HIERARCHY (Level-Order) =====");
        schoolTree.levelOrderTraversal();
    }
}
