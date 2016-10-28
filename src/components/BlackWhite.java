package components;

import messaging.Message;
import messaging.MessageImage;
import types.TaskType;

public class BlackWhite extends Component{

	public BlackWhite()
	{
		super(TaskType.BLACK_WHITE);
	}
	
	public Message notify(Message message)
	{
		MessageImage image = new MessageImage(TaskType.BLACK_WHITE);
		int h = ((MessageImage)message).getHeight();
		int w = ((MessageImage)message).getWidth();
		int pix[][][] = ((MessageImage)message).getPixels();
		int newpix[][][]= new int[h][w][3];
		for (int i=0; i<h; i++)
			for (int j=0; j<w; j++)
			{
				newpix[i][j][0] = (int)Math.round( pix[i][j][0]*0.3 + pix[i][j][1]*0.59 + pix[i][j][2]*0.11 );  //Red
				newpix[i][j][1] = (int)Math.round( pix[i][j][0]*0.3 + pix[i][j][1]*0.59 + pix[i][j][2]*0.11 );  //Green
				newpix[i][j][2] = (int)Math.round( pix[i][j][0]*0.3 + pix[i][j][1]*0.59 + pix[i][j][2]*0.11 );  //Blue
			}
		image.setHeight(h);
		image.setWidth(w);
		image.setPixels(newpix);
		return image;
	}

}
