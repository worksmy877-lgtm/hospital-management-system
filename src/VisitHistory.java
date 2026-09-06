/**
 * Singly Linked List that stores a single patient's previous hospital visits.
 * Supported operations: add visit, remove visit, search visit, display history.
 */
public class VisitHistory {
    private Visit head;
    private int size;

    public VisitHistory() {
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    // ---------- ADD ----------
    // New visits are appended to the end so the list reads in chronological order.
    public void addVisit(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        Visit newVisit = new Visit(visitId, visitDate, doctorName, diagnosis, treatment);
        if (head == null) {
            head = newVisit;
        } else {
            Visit current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newVisit;
        }
        size++;
    }

    // ---------- REMOVE ----------
    public boolean removeVisit(int visitId) {
        if (head == null) {
            return false;
        }
        if (head.visitId == visitId) {
            head = head.next;
            size--;
            return true;
        }
        Visit current = head;
        while (current.next != null && current.next.visitId != visitId) {
            current = current.next;
        }
        if (current.next == null) {
            return false; // not found
        }
        current.next = current.next.next;
        size--;
        return true;
    }

    // ---------- SEARCH ----------
    public Visit searchVisit(int visitId) {
        Visit current = head;
        while (current != null) {
            if (current.visitId == visitId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // ---------- DISPLAY ----------
    public void displayVisits() {
        if (head == null) {
            System.out.println("No visit history for this patient.");
            return;
        }
        System.out.println("---- Visit History ----");
        Visit current = head;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }
}
