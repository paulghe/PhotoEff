package messaging;

import java.util.ArrayList;

import components.Component;

public abstract class MessageCenter {
	private String centerName;
	protected ArrayList<Component> comp;
	protected ArrayList<MessCenter> v;
	protected ArrayList<Integer> viz; // Processed IDs
		
	public MessageCenter(String centerName) {
		super();
		this.centerName = centerName;
		v = new ArrayList<MessCenter>();
		viz = new ArrayList<Integer>();
		comp = new ArrayList<Component>();
	}

	public Message publish(Message message)	{
		System.out.println(centerName);
		return publishAlgorithm(message);
	}
	
	public void subscribe(Component c)
	{
		comp.add(c);
	}
	
	public String getName()
	{
		return centerName;
	}

	public void setNeighbour(MessCenter neighbour)
	{
		v.add(neighbour);
	}
	
	protected abstract Message publishAlgorithm(Message message);
}
