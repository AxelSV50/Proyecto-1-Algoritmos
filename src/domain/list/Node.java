/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.list;

import domain.*;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class Node {
    public Object data; //el dato almancenado en el nodo
    public Node next; //el apuntador al sgte nodo
    public Node prev; //el apuntador al nodo anterior
    
    //Constructor
    public Node(Object data){
        this.data = data;
        this.next = this.prev = null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    
    
}
