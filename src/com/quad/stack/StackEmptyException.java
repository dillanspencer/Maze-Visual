package com.quad.stack;

@SuppressWarnings("serial")

public class StackEmptyException extends Exception
{
	
	String msg;

	public StackEmptyException()
	{
		msg = "StackEmptyException";
	}
	
	public StackEmptyException (String m)
	{
		msg = m;
	}

	public String toString()
	{
		return "StackEmptyException: " + msg;
	}
}
