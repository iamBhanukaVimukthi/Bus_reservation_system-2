package demo;

import datastructures.CustomAVLTree;
import models.Bus;
import java.util.Arrays;

public class Member4_Demo {

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== MEMBER 4: AVL TREE, INSERTION SORT & COMPLEXITY ANALYSIS ===\n");

        // 1. Testing AVL Tree from datastructures package
        System.out.println("--- AVL Tree Bus Management ---");
        CustomAVLTree<Bus> avlTree = new CustomAVLTree<>();

        // Create Bus instances using (busId, busNumber, capacity, status, type)
        avlTree.insert(new Bus(105, "ND-1111", 40, "Active", "AC Luxury"));
        avlTree.insert(new Bus(102, "CA-2222", 30, "Active", "Semi-Express"));
        avlTree.insert(new Bus(108, "WP-3333", 50, "Maintenance", "Double Decker"));
        avlTree.insert(new Bus(101, "SG-4444", 25, "Active", "Mini Bus"));

        System.out.println("Buses stored in AVL Tree (Inorder Traversal - Sorted by ID):");
        avlTree.display();

        // 2. Testing Insertion Sort
        System.out.println("\n--- Insertion Sort: Sorting Bus Capacities ---");
        int[] busCapacities = {45, 20, 60, 30, 15};
        System.out.println("Before Sorting: " + Arrays.toString(busCapacities));

        insertionSort(busCapacities);

        System.out.println("After Sorting:  " + Arrays.toString(busCapacities));


    }
}