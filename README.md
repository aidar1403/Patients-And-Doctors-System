# Patients and Doctors Management System

A Java-based console application designed to manage hospital workflows, including patient registration, doctor authentication, and medicine prescription queues. This project demonstrates the practical application of the **Java Collection Framework (JCF)** and Object-Oriented Programming (OOP) principles.

##  Project Overview
The system simulates real-world medical workflows using role-based access control (RBAC). There are two main roles:
*   **Patient:** Can register, log in, update their medical card (add allergies), and submit prescriptions/medicine requests to a queue.
*   **Doctor:** Can log in, view and sort the patient list, manage the medicine catalog, process requests from the queue, and issue medicines to patients (reducing stock).

##  Technologies Used
*   **Java 17+**
*   **Java Collection Framework (JCF)**
*   **OOP (Inheritance, Abstraction, Encapsulation, Polymorphism)**
*   **SHA-256 Password Hashing** (for basic security)

##  Architecture & JCF Implementation
The project is strictly divided into layers (Model, Service, UI) to ensure clean code and Single Responsibility Principle (SRP). The JCF is utilized across the application as follows:

### 1. Registries & Repositories (List / Set)
*   `List<Patient> (ArrayList)`: Used in `PatientService` to store the sequential registry of patients.
*   `Set<String> (HashSet)`: Used inside the `Patient` model to store unique allergies, preventing duplicates.

### 2. Fast Lookup & Indexing (Map)
*   `Map<String, User> (HashMap)`: Used in `AuthService` for O(1) instant login lookup by username.
*   `Map<Long, Patient> (HashMap)`: Used for O(1) patient search by their unique ID.
*   `Map<String, Medicine> (HashMap)`: Used in `MedicineService` for O(1) lookup by SKU (barcode).
*   `Map<MedicineCategory, List<Medicine>> (EnumMap)`: Used to categorize medicines efficiently. `EnumMap` is chosen because the keys are enum constants, making it faster and more memory-efficient than a standard `HashMap`.

### 3. Sorting & Ordering (Comparable & Comparator)
*   **Comparable:** Implemented in `Patient`, `Medicine`, and `Prescription`. This defines the natural order (e.g., alphabetical by name, or by ID for the queue).
*   **Comparator:** Multiple custom comparators are implemented (e.g., `BY_PRICE_ASC`, `BY_CATEGORY_THEN_NAME` for medicines) to allow dynamic, multi-criteria sorting without modifying the original classes.

### 4. Workflow & Request Queues (Queue)
*   `Queue<Prescription> (PriorityQueue)`: Used in `PrescriptionService`. Patients use `offer()` to submit requests, and Doctors use `poll()` to process them. It acts as a real-time processing queue where requests are handled in order.

##  Object Contracts
All domain entities properly override `equals()` and `hashCode()` based on **business keys** (not technical IDs) to ensure correct behavior in Hash-based collections:
*   `Patient`: by `policyNumber`
*   `Medicine`: by `sku`
*   `User`: by `login`

##  How to Run
1. Clone the repository.
2. Open the project in IntelliJ IDEA (or any Java IDE).
3. Navigate to `src/main/java/org/example/ui/Main.java`.
4. Run the `main` method.
5. Use the terminal menu to register as a Patient or a Doctor, and test the workflows.
