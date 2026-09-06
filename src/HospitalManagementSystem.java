import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Mini Hospital Emergency Management System.
 *
 * Ties together four data structures:
 *   1. PatientBST      - all registered patients, keyed by Patient ID
 *   2. EmergencyQueue   - patients currently waiting for treatment (FIFO)
 *   3. TreatmentStack   - completed treatment records (LIFO)
 *   4. VisitHistory     - per-patient singly linked list of past visits
 */
public class HospitalManagementSystem {

    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextVisitId = 1;

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("=================================================");
        System.out.println(" Mini Hospital Emergency Management System");
        System.out.println("=================================================");

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: registerPatient(); break;
                case 2: searchPatient(); break;
                case 3: deletePatient(); break;
                case 4: patientBST.displayInOrder(); break;
                case 5: emergencyQueue.displayQueue(); break;
                case 6: treatNextPatient(); break;
                case 7: treatmentStack.displayStack(); break;
                case 8: undoLastTreatment(); break;
                case 9: addVisit(); break;
                case 10: removeVisit(); break;
                case 11: searchVisit(); break;
                case 12: displayVisitHistory(); break;
                case 0:
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("---------------------- MENU ----------------------");
        System.out.println(" Patient Records (BST)");
        System.out.println("  1. Register new patient");
        System.out.println("  2. Search patient by ID");
        System.out.println("  3. Delete patient");
        System.out.println("  4. Display all patients (in-order)");
        System.out.println(" Emergency Queue");
        System.out.println("  5. Display waiting queue");
        System.out.println("  6. Treat next patient (dequeue)");
        System.out.println(" Treatment History (Stack)");
        System.out.println("  7. Display treatment history");
        System.out.println("  8. Undo last treatment (pop)");
        System.out.println(" Patient Visit History (Linked List)");
        System.out.println("  9. Add visit record to patient");
        System.out.println("  10. Remove visit record from patient");
        System.out.println("  11. Search visit record");
        System.out.println("  12. Display patient visit history");
        System.out.println("  0. Exit");
        System.out.println("----------------------------------------------------");
    }

    // ---------------- BST OPERATIONS ----------------
    private static void registerPatient() {
        int id = readInt("Enter Patient ID: ");
        if (patientBST.search(id) != null) {
            System.out.println("A patient with ID " + id + " already exists.");
            return;
        }
        String name = readString("Enter Patient Name: ");
        int age = readInt("Enter Age: ");
        String contact = readString("Enter Contact Number: ");
        String condition = readString("Enter Medical Condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);
        emergencyQueue.enqueue(patient);
        System.out.println("Patient registered successfully and added to the emergency queue.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
        } else {
            System.out.println("Patient found: " + patient);
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        boolean deleted = patientBST.delete(id);
        if (deleted) {
            System.out.println("Patient with ID " + id + " deleted successfully.");
        } else {
            System.out.println("No patient found with ID " + id);
        }
    }

    // ---------------- QUEUE OPERATIONS ----------------
    private static void treatNextPatient() {
        Patient patient = emergencyQueue.dequeue();
        if (patient == null) {
            return; // message already printed inside dequeue()
        }
        System.out.println("Now treating: " + patient);
        String treatmentDetails = readString("Enter treatment given: ");

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        TreatmentRecord record = new TreatmentRecord(patient.getPatientId(), patient.getName(),
                treatmentDetails, timestamp);
        treatmentStack.push(record);

        // Automatically log this treatment as a visit in the patient's history
        patient.getVisitHistory().addVisit(nextVisitId++, timestamp.substring(0, 10),
                "Duty Doctor", patient.getMedicalCondition(), treatmentDetails);
        System.out.println("Treatment completed and recorded.");
    }

    // ---------------- STACK OPERATIONS ----------------
    private static void undoLastTreatment() {
        TreatmentRecord record = treatmentStack.pop();
        if (record != null) {
            System.out.println("Removed most recent treatment record: " + record);
        }
    }

    // ---------------- LINKED LIST OPERATIONS ----------------
    private static void addVisit() {
        Patient patient = getPatientOrPrintError();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID: ");
        String date = readString("Enter Visit Date (yyyy-MM-dd): ");
        String doctor = readString("Enter Doctor Name: ");
        String diagnosis = readString("Enter Diagnosis: ");
        String treatment = readString("Enter Treatment: ");

        patient.getVisitHistory().addVisit(visitId, date, doctor, diagnosis, treatment);
        System.out.println("Visit record added to patient " + patient.getName() + "'s history.");
    }

    private static void removeVisit() {
        Patient patient = getPatientOrPrintError();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID to remove: ");
        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit removed successfully." : "Visit ID not found.");
    }

    private static void searchVisit() {
        Patient patient = getPatientOrPrintError();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        System.out.println(visit == null ? "Visit ID not found." : "Visit found: " + visit);
    }

    private static void displayVisitHistory() {
        Patient patient = getPatientOrPrintError();
        if (patient == null) return;
        patient.getVisitHistory().displayVisits();
    }

    private static Patient getPatientOrPrintError() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
        }
        return patient;
    }

    // ---------------- INPUT HELPERS ----------------
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
