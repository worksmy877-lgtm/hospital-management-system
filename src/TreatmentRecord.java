/**
 * Represents a completed treatment record.
 */
public class TreatmentRecord {
    private int patientId;
    private String patientName;
    private String treatmentDetails;
    private String completionTimestamp;

    public TreatmentRecord(int patientId, String patientName, String treatmentDetails, String completionTimestamp) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentDetails = treatmentDetails;
        this.completionTimestamp = completionTimestamp;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    public String getCompletionTimestamp() {
        return completionTimestamp;
    }

    @Override
    public String toString() {
        return String.format("Patient ID: %-5d | Name: %-15s | Treatment: %-20s | Completed: %s",
                patientId, patientName, treatmentDetails, completionTimestamp);
    }
}
