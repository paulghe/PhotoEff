package components;

import messaging.Message;
import messaging.MessageImage;
import messaging.MessageZoom;
import types.TaskType;

public class Zoom extends Component {
	
	public Zoom()
	{
		super(TaskType.ZOOM);
	}
	
	public Message notify(Message message)
	{
		int pix[][][] = ((MessageImage)message).getPixels();
		int y1 = ((MessageZoom)message).getCoordXp1();
		int x1 = ((MessageZoom)message).getCoordYp1();
		int y2 = ((MessageZoom)message).getCoordXp2();
		int x2 = ((MessageZoom)message).getCoordYp2();
		int newpix[][][] = new int[x2-x1+1][y2-y1+1][3];
		int i=0,j=0;
		for (int k=x1; k<=x2; k++)
		{
			for (int l=y1; l<=y2; l++)
			{
				newpix[i][j][0] = pix[k][l][0];
				newpix[i][j][1] = pix[k][l][1];
				newpix[i][j][2] = pix[k][l][2];
				j++;
			}
			i++;	
			j=0;
		}
		MessageImage img = new MessageImage(TaskType.ZOOM);
		img.setHeight(x2-x1+1);
		img.setWidth(y2-y1+1);
		img.setPixels(newpix);
		return img;
	}

}
