package components;

import types.TaskType;
import messaging.Message;
import messaging.MessageImage;

public class RawPhoto extends Component{
	
	public RawPhoto()
	{
		super(TaskType.RAW_PHOTO);
	}
	
	public Message notify(Message message)
	{
		int h = ((MessageImage)message).getHeight();
		int w = ((MessageImage)message).getWidth();
		int pix[][][] = ((MessageImage)message).getPixels();
		int aux;
		for (int i=0; i<h/2; i++)
		{
			for (int j=0; j<w; j++)
			{
				aux=pix[i][j][0];
				pix[i][j][0]=pix[h-i-1][j][0];
				pix[h-i-1][j][0]=aux;
				aux=pix[i][j][1];
				pix[i][j][1]=pix[h-i-1][j][1];
				pix[h-i-1][j][1]=aux;
				aux=pix[i][j][2];
				pix[i][j][2]=pix[h-i-1][j][2];
				pix[h-i-1][j][2]=aux;			
			}
		}
		((MessageImage)message).setPixels(pix);
		return (MessageImage)message;
	}

}
