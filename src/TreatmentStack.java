/**
 * Treatment History Stack implemented as a linked-list based stack.
 * Follows LIFO (Last-In, First-Out) principle.
 */
public class TreatmentStack {
    private StackNode top;
    private int size;

    public TreatmentStack() {
        this.top = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    // ---------- PUSH ----------
    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.next = top;
        top = newNode;
        size++;
        System.out.println("Treatment record for " + record.getPatientName() + " pushed onto history stack.");
    }

    // ---------- POP ----------
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment history stack is empty. Nothing to pop.");
            return null;
        }
        TreatmentRecord record = top.record;
        top = top.next;
        size--;
        return record;
    }

    public TreatmentRecord peek() {
        if (isEmpty()) {
            return null;
        }
        return top.record;
    }

    // ---------- DISPLAY ----------
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("Treatment history stack is empty.");
            return;
        }
        System.out.println("---- Treatment History (most recent first) ----");
        StackNode current = top;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.record);
            current = current.next;
            position++;
        }
    }
}
