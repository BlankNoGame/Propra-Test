package de.hhu.propra;

public class List {
    private static class Node {
        private final int data;
        private Node next;
        
        private Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head = null;

    /**
     * Fügt element am Ende der Liste an.
     */
    public void add(int element) {
        if(head == null) {
            head = new Node(element, null);
            return;
        }
        Node current = head;
        while(current.next != null) {
            current = current.next;
        }
        current.next = new Node(element, null);
    }
    
    public String toString() {
        StringBuilder output = new StringBuilder();
        Node current = head;
        while(current != null) {
            output.append(current.data).append(",");
            current = current.next;
        }
        return output.toString();
    }

    public void forEach(Printer printer) {
        Node current = head;
        while(current != null) {
            // TODO hier eine Zeile ergänzen
            current = current.next;
        }
    }
}
