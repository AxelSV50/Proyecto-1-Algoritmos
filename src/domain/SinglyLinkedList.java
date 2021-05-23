/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class SinglyLinkedList implements List {

    private Node first; //apunta al inicio de la lista dinamica

    //Constructor
    public SinglyLinkedList() {
        this.first = null; //la lista todavia no existe
    }

    @Override
    public int size() throws ListException {
        if (isEmpty()) {
            throw new ListException("SinglyLinkedList is empty");
        }
        Node aux = first;
        int count = 0;
        while (aux != null) {
            count++;
            aux = aux.next;
        }
        return count;
    }

    @Override
    public void clear() {
        this.first = null;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("SinglyLinkedList is empty");
        }
        Node aux = first;
        while (aux != null) {
            if (util.Utility.equals(aux.data, element)) {
                return true;
            }
            aux = aux.next;
        }
        return false; //indica q el elemento no existe
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            this.first = newNode;
        } else {
            Node aux = first;
            //el aux es para moverme por la lista hasta el ult elemento
            while (aux.next != null) {
                aux = aux.next;
            }
            //cuando se sale del while quiere decir q aux.next == null
            aux.next = newNode;
        }
    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            this.first = newNode;
        }
        newNode.next = first;
        first = newNode;
    }

    @Override
    public void addLast(Object element) {
        add(element);
    }

    @Override
    public void addInSortedList(Object element) {
        Node newNode = new Node(element);
        //CASO 1. LA LISTA ESTA VACIA
        if (isEmpty()) {
            first = newNode;
        } else {
            //CASO 2. first.next es nulo, o no es nulo
            //y el elemento a insertar es menor al del inicio
            if (util.Utility.greaterT(first.data, element)) {
                newNode.next = first;
                first = newNode;
            } else {
                //CASE 3. TODO LO DEMAS
                Node prev = first;
                Node aux = first.next;
                boolean added = false;
                while (aux != null && !added) {
                    if (util.Utility.lessT(element, aux.data)) {
                        prev.next = newNode;
                        newNode.next = aux;
                        added = true;
                    }
                    prev = aux;
                    aux = aux.next;
                }
                //si llega aqui, el elemento se agrega al final de la lista
                if (!added) {
                    prev.next = newNode;
                }
            }
        }
    }

    @Override
    public void remove(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("SinglyLinkedList is empty");
        }
        //CASO 1. EL ELEMENTO A SUPRIMIR ES EL PRIMERO DE LA LISTA
        if (util.Utility.equals(first.data, element)) {
            first = first.next;
        } else {
            //CASO 2. EL ELEMENTO A SUPRIMIR ESTA EN CUALQUIER OTRO NODO
            Node prev = first; //para dejar rastro, apunta al anterior de aux
            Node aux = first.next;
            while (aux != null && !util.Utility.equals(aux.data, element)) {
                prev = aux; //un nodo atras de aux
                aux = aux.next;
            }
            //se sale del while cuando alcanza nulo
            //o cuando encuentra el elemento a suprimir
            if (aux != null && util.Utility.equals(aux.data, element)) {
                //desenlazo o desconecto el nodo
                prev.next = aux.next;
            }
        }
    }

    @Override
    public Object removeFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("SinglyLinkedList is empty");
        }
        Object element = first.data;
        first = first.next; //muevo el apuntador al sgte nodo
        return element;
    }

    @Override
    public Object removeLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("SinglyLinkedList is empty");
        }
        Node aux = first;
        Node prev = first; //para dejar rastro, apunta al anterior de aux
        while (aux.next != null) {
            prev = aux; //un nodo atras de aux
            aux = aux.next;
        }
        //se sale del while cuando esta en el ultimo nodo
        Object element = aux.data;
        prev.next = null; //desconecto el ultimo nodo
        return element;
    }

    @Override
    public void sort() throws ListException {

        if (isEmpty()) {
            throw new ListException("SinglyLinkedList is empty");
        }

        if (size() > 1) {

            boolean exchange;

            do {

                Node current = first;
                Node previous = null;
                Node nextE = first.next;
                exchange = false;

                while (nextE != null) {

                    if (util.Utility.greaterT(current.data, nextE.data)) {

                        exchange = true;

                        if (previous != null) {

                            Node aux = nextE.next;
                            previous.next = nextE;
                            nextE.next = current;
                            current.next = aux;

                        } else {

                            Node aux = nextE.next;
                            first = nextE;
                            nextE.next = current;
                            current.next = aux;
                        }
                        previous = nextE;
                        nextE = current.next;
                    } else {
                        previous = current;
                        current = nextE;
                        nextE = nextE.next;
                    }
                }
            } while (exchange);
        }
    }

    @Override
    public int indexOf(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("SinglyLinkedList is empty");
        }
        Node aux = first;
        int index = 1; //el primer nodo estara en el indice 1
        while (aux != null) {
            if (util.Utility.equals(aux.data, element)) {
                return index; //ya lo encontro
            }
            index++;
            aux = aux.next;
        }
        return -1; //significa q el elemento no existe
    }

    @Override
    public Object getFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("SinglyLinkedList is empty");
        }
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("SinglyLinkedList is empty");
        }
        Node aux = first;
        while (aux.next != null) {
            aux = aux.next;
        }
        return aux.data;
    }

    @Override
    public Object getPrev(Object element) throws ListException {

        if (isEmpty()) {
            throw new ListException("SinglyLinkedList is empty");
        }

        Node prev = null;
        Node aux = first;
        while (aux != null) {
            if (util.Utility.equals(aux.data, element)) {

                if (prev != null) {
                    
                    return prev.data;
                }
                return null;
            }
            prev = aux;
            aux = aux.next;
        }
        return null;
    }

    @Override
    public Object getNext(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("SinglyLinkedList is empty");
        }

        Node aux = first;
        while (aux != null) {
            if (util.Utility.equals(aux.data, element)) {

                if (aux.next == null) {

                    return null;
                }
                return aux.next.data;
            }
            aux = aux.next;
        }
        return null;
    }

    @Override
    public Node getNode(int index) throws ListException {
        if (isEmpty()) {
            throw new ListException("SinglyLinkedList is empty");
        }
        Node aux = first;
        int i = 1; //el indice del primer elemento de la lista
        while (aux != null) {
            if (util.Utility.equals(i, index)) {
                return aux; //ya lo encontro
            }
            i++;
            aux = aux.next;
        }
        return null; //si llega aqui, no encontro el nodo
    }

 
    
    @Override
    public String toString() {
        String result = "SINGLY LINKED LIST\n";
        Node aux = first;
        //el aux es para moverme por la lista hasta el ult elemento
        while (aux != null) {
            result += aux.data + "\n";
            aux = aux.next;
        }
        return result;
    }

}
