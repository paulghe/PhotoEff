package messaging;

import types.FlashType;
import types.TaskType;

public class MessageFlash extends MessageImage {
	
	private FlashType flashType;
	
	public MessageFlash(TaskType taskType,FlashType flashType,int pixels[][][],int width,int height)
	{
		super(taskType,pixels,width,height);
		this.flashType=flashType;
	}
	
	public FlashType getFlashType()
	{
		return flashType;
	}
	
	public void setFlashType(FlashType flashType)
	{
		this.flashType = flashType;
	}
	
}
