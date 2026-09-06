# Mini Hospital Emergency Management System

A console-based Java application that simulates patient management in a
hospital emergency unit, built to demonstrate four core data structures.

## Data Structures Used

| Requirement                     | Data Structure          | Files                                      |
|----------------------------------|--------------------------|---------------------------------------------|
| Patient Records                  | Binary Search Tree (BST) | `Patient.java`, `BSTNode.java`, `PatientBST.java` |
| Emergency Patient Queue          | Queue (linked list based)| `QueueNode.java`, `EmergencyQueue.java`     |
| Treatment History                | Stack (linked list based)| `TreatmentRecord.java`, `StackNode.java`, `TreatmentStack.java` |
| Patient Visit History            | Singly Linked List       | `Visit.java`, `VisitHistory.java`           |

All four structures are implemented from scratch (no `java.util.Stack`,
`java.util.Queue`, or `java.util.LinkedList`) so the underlying pointer
logic is fully visible.

## Project Structure

```
HospitalManagementSystem/
├── README.md
└── src/
    ├── Patient.java
    ├── BSTNode.java
    ├── PatientBST.java
    ├── QueueNode.java
    ├── EmergencyQueue.java
    ├── TreatmentRecord.java
    ├── StackNode.java
    ├── TreatmentStack.java
    ├── Visit.java
    ├── VisitHistory.java
    └── HospitalManagementSystem.java   (main / menu)
```

## How to Compile and Run

```bash
cd src
javac *.java
java HospitalManagementSystem
```

## How Each Data Structure Is Used

- **BST (`PatientBST`)** — Every registered patient is inserted into a
  binary search tree keyed on `patientId`, so patients can be searched
  and deleted in O(log n) average time, and `displayInOrder()` prints
  all patients sorted by ID using an in-order traversal.
- **Queue (`EmergencyQueue`)** — When a patient is registered they are
  automatically enqueued. `dequeue()` pulls the next patient to be
  treated, in strict arrival order (FIFO).
- **Stack (`TreatmentStack`)** — Once a patient is treated, a
  `TreatmentRecord` is pushed onto the stack. `pop()` removes the most
  recently completed treatment (LIFO), useful for "undo last action"
  style review.
- **Singly Linked List (`VisitHistory`)** — Each `Patient` owns one
  `VisitHistory`. Completing a treatment appends a new `Visit` node to
  that patient's list, and visits can also be added, removed, searched
  and displayed manually from the menu.

## Menu Overview

```
 Patient Records (BST)
  1. Register new patient
  2. Search patient by ID
  3. Delete patient
  4. Display all patients (in-order)
 Emergency Queue
  5. Display waiting queue
  6. Treat next patient (dequeue)
 Treatment History (Stack)
  7. Display treatment history
  8. Undo last treatment (pop)
 Patient Visit History (Linked List)
  9. Add visit record to patient
  10. Remove visit record from patient
  11. Search visit record
  12. Display patient visit history
  0. Exit
```

## Sample Workflow

1. Register a few patients (option 1) — each is added to the BST and
   the emergency queue.
2. Display the queue (option 5) to see arrival order.
3. Treat the next patient (option 6) — they are dequeued, a treatment
   record is pushed onto the stack, and a visit entry is logged.
4. Display treatment history (option 7) to see it in LIFO order.
5. Display the patient's visit history (option 12) to see the new visit.

## Notes on GitHub Submission

This project is structured so it can be committed progressively, e.g.:

1. `Initial project structure`
2. `Implemented Patient model and BST insert/search`
3. `Added BST deletion and in-order traversal`
4. `Implemented Emergency Queue`
5. `Implemented Treatment Stack`
6. `Implemented Visit linked list`
7. `Wired everything together in HospitalManagementSystem main menu`
8. `Added README and manual testing notes`

Commit each stage separately rather than uploading the finished project
as a single commit.
