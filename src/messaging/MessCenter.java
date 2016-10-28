package messaging;


public class MessCenter extends MessageCenter {
	
	public MessCenter(String centerName,int ComponentsNumber,int noCenters)
	{
		super(centerName);
	}
	
	public Message publishAlgorithm(Message message)
	{
		if (viz.contains(message.getId())==true) 
			return null;
		viz.add(message.getId());
		for (int i=0; i<comp.size(); i++)
			if (message.getTaskType()==comp.get(i).getTaskType())   //Component found
				return comp.get(i).notify(message);
		for (int i=0; i<v.size(); i++) // Component not found in this center
		{
			Message m = v.get(i).publish(message);
			if (m!=null)
				return m;	
		}
		return null;
	}
	
}
