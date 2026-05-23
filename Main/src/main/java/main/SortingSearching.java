/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Nicolas
 */
public class SortingSearching {
    
    /**
     * Sorts an Employee array by name (case-insensitive) using QuickSort.
     * QuickSort picks a pivot, partitions the array so that elements
     * smaller than pivot come before it, then recursively sorts each half.
     * 
     * @param arr  Array to sort
     * @param low  Starting index
     * @param high Ending index
     */
    public static void quickSort(Employee[] arr, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot index
            int pi = partition(arr, low, high);
            // Recursively sort elements before and after pivot
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    /**
     * Partitions array around a pivot (last element).
     * Elements with names smaller than pivot go left, larger go right.
     */
    private static int partition(Employee[] arr, int low, int high) {
        Employee pivot = arr[high];  // Choose last element as pivot
        int i = low - 1;             // Index of smaller element
        for (int j = low; j < high; j++) {
            // Compare names case-insensitively
            if (arr[j].getName().compareToIgnoreCase(pivot.getName()) < 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    /** Swaps two elements in the array. */
    private static void swap(Employee[] arr, int i, int j) {
        Employee temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * Recursive binary search on a sorted Employee array by name.
     * Divides the array in half each time and searches the relevant half.
     * Base case: low > high means the element was not found.
     * 
     * @param arr    Sorted array
     * @param low    Left boundary
     * @param high   Right boundary
     * @param target Name to search for (case-insensitive)
     * @return       Index of the employee if found, -1 otherwise
     */
    public static int binarySearch(Employee[] arr, int low, int high, String target) {
        if (low > high) return -1;  // Element not found
        
        int mid = (low + high) / 2;
        int cmp = arr[mid].getName().compareToIgnoreCase(target);
        
        if (cmp == 0) {
            return mid;              // Found the target
        } else if (cmp > 0) {
            return binarySearch(arr, low, mid - 1, target);  // Search left half
        } else {
            return binarySearch(arr, mid + 1, high, target);  // Search right half
        }
    }
}
