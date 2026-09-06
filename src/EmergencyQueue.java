/**
 * Emergency Patient Queue implemented as a linked-list based queue.
 * Follows FIFO (First-In, First-Out) principle.
 */
public class EmergencyQueue {
    private QueueNode front;
    private QueueNode rear;
    private int size;

    public EmergencyQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    // ---------- ENQUEUE ----------
    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Patient " + patient.getName() + " (ID: " + patient.getPatientId()
                + ") added to the emergency waiting queue.");
    }

    // ---------- DEQUEUE ----------
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient waiting for treatment.");
            return null;
        }
        Patient patient = front.patient;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return patient;
    }

    public Patient peek() {
        if (isEmpty()) {
            return null;
        }
        return front.patient;
    }

    // ---------- DISPLAY ----------
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty.");
            return;
        }
        System.out.println("---- Patients waiting in Emergency Queue (front -> rear) ----");
        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }
}
