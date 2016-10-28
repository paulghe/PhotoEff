package components;

import types.TaskType;
import messaging.Message;

public class NormalPhoto extends Component{
	
	public NormalPhoto()
	{
		super(TaskType.NORMAL_PHOTO);
	}
	
	public Message notify(Message message)
	{
		return new RawPhoto().notify(message);
	}

}
