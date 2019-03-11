package com.quad.stack;

import com.quad.maze.MazeLocation;

public class StackRefBased<T> implements Stack<T> {
	
	private int size;
	public StackNode<T> head;

    public StackRefBased() {
    	head = null;
    	size = 0;
    }

    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return (head == null);
    }


    public void push(T data) {
        StackNode<T> node = new StackNode<T>(data);
        
        //check if empty
        if(head == null) head = node;
        
        //if not empty
        else {
        	node.next = head;
        	head = node;
        }
        
        size++;
    }


    public T pop() throws StackEmptyException {
        T data = null;
        
        try {
			data = head.getValue();
			head = head.getNext();
			size--;
        }catch(NullPointerException e) {
        	throw new StackEmptyException();
        }
       
        return data;
    }


    public T peek() throws StackEmptyException {
    	T data = null;
        
    	try {
    		data = head.getValue();
    	}catch(NullPointerException e) {
    		throw new StackEmptyException();
    	}
    	
    	return data;
    }


    public void makeEmpty() {
    	head = null;
    	size = 0;
    }

    //returns a reversed stack
    public StackRefBased<T> reverseStack(){
    	StackRefBased<T> stack = new StackRefBased<T>();
    	StackNode<T> temp = null;
    	
    	for(temp = head; temp != null; temp = temp.next) {
    		stack.push(temp.getValue());
    	}
         
    	return stack;
    }
    
    public T get(int pos) {
    	StackNode<T> temp = head;
    	
    	for(int i = 0; i < pos; i++) {
    		temp = temp.getNext();
    	}
    	
    	return temp.getValue();
    }

    public boolean intersects(T other) {
    	StackNode<T> tempNode = null;
    	
    	 for(tempNode = head; tempNode != null; tempNode = tempNode.next) {
            if(other.toString().equals(tempNode.getValue().toString())) return true;
         }
    	 
    	 return false;
    }

    public String toString() {
    	  StringBuilder result = new StringBuilder();
          StackNode<T> tempNode = null;
          StackRefBased<T> stack = reverseStack();
         
          if (stack.head != null) {
              for(tempNode = stack.head; tempNode != null; tempNode = tempNode.next) {
                  result.append(tempNode.data + " ");
              }
          }
         
          return result.toString();
    }
}

