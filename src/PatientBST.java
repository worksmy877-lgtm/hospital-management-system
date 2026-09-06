/**
 * Binary Search Tree that stores all registered patients,
 * keyed by Patient ID.
 *
 * Supported operations: insert, search, delete, in-order traversal.
 */
public class PatientBST {
    private BSTNode root;

    public PatientBST() {
        this.root = null;
    }

    // ---------- INSERT ----------
    public boolean insert(Patient patient) {
        if (search(patient.getPatientId()) != null) {
            // Duplicate Patient ID is not allowed since it is the BST key
            return false;
        }
        root = insertRec(root, patient);
        return true;
    }

    private BSTNode insertRec(BSTNode node, Patient patient) {
        if (node == null) {
            return new BSTNode(patient);
        }
        if (patient.getPatientId() < node.patient.getPatientId()) {
            node.left = insertRec(node.left, patient);
        } else if (patient.getPatientId() > node.patient.getPatientId()) {
            node.right = insertRec(node.right, patient);
        }
        return node;
    }

    // ---------- SEARCH ----------
    public Patient search(int patientId) {
        BSTNode result = searchRec(root, patientId);
        return (result == null) ? null : result.patient;
    }

    private BSTNode searchRec(BSTNode node, int patientId) {
        if (node == null || node.patient.getPatientId() == patientId) {
            return node;
        }
        if (patientId < node.patient.getPatientId()) {
            return searchRec(node.left, patientId);
        }
        return searchRec(node.right, patientId);
    }

    // ---------- DELETE ----------
    public boolean delete(int patientId) {
        if (search(patientId) == null) {
            return false;
        }
        root = deleteRec(root, patientId);
        return true;
    }

    private BSTNode deleteRec(BSTNode node, int patientId) {
        if (node == null) {
            return null;
        }

        if (patientId < node.patient.getPatientId()) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.getPatientId()) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Node found

            // Case 1: no children
            if (node.left == null && node.right == null) {
                return null;
            }
            // Case 2: one child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // Case 3: two children -> replace with in-order successor
            // (smallest value in the right subtree)
            BSTNode successor = findMin(node.right);
            node.patient = successor.patient;
            node.right = deleteRec(node.right, successor.patient.getPatientId());
        }
        return node;
    }

    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // ---------- IN-ORDER TRAVERSAL ----------
    public void displayInOrder() {
        if (root == null) {
            System.out.println("No patients registered yet.");
            return;
        }
        System.out.println("---- Patients (ascending Patient ID) ----");
        inOrderRec(root);
    }

    private void inOrderRec(BSTNode node) {
        if (node == null) {
            return;
        }
        inOrderRec(node.left);
        System.out.println(node.patient);
        inOrderRec(node.right);
    }

    public boolean isEmpty() {
        return root == null;
    }
}
