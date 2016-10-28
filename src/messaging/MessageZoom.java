package messaging;

import types.TaskType;

public class MessageZoom extends MessageImage {
	
	private int p1x,p1y,p2x,p2y;
	
	public MessageZoom(TaskType taskType,int x1,int y1,int x2,int y2,int pixels[][][],int width,int height)
	{
		super(taskType,pixels,width,height);
		p1x = x1;
		p1y = y1;
		p2x = x2;
		p2y = y2;
	}
	
	public int getCoordXp1()
	{
		return p1x;
	}
	
	public int getCoordYp1()
	{
		return p1y;
	}
	
	public int getCoordXp2()
	{
		return p2x;
	}
	
	public int getCoordYp2()
	{
		return p2y;
	}

}
