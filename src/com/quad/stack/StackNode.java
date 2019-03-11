package com.quad.stack;
/*
 * StackNode.java
 *
 * A plain-jane node for a ref-based Stack, assuming that the stack
 * meant to contains nodes storing values of type T.
 *
 * UVic CSC 115, Spring 2017
 */


public class StackNode<T> {
    T data;
    public StackNode<T> next;


    public StackNode(T data) {
        this.data = data;
        this.next = null;
    }


    public T getValue() {
        return this.data;
    }


    public StackNode<T> getNext() {
        return this.next;
    }


    public void setNext(StackNode<T> next) {
        this.next = next;
    }


    public String toString() {
        return data.toString();
    }
}
