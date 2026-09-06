/**
 * A single node in a Patient's Visit History singly linked list.
 * Represents one past hospital visit.
 */
public class Visit {
    int visitId;
    String visitDate;
    String doctorName;
    String diagnosis;
    String treatment;
    Visit next;

    public Visit(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        this.visitId = visitId;
        this.visitDate = visitDate;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.next = null;
    }

    @Override
    public String toString() {
        return String.format("Visit ID: %-4d | Date: %-12s | Doctor: %-15s | Diagnosis: %-15s | Treatment: %s",
                visitId, visitDate, doctorName, diagnosis, treatment);
    }
}
