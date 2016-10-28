package components;

import messaging.Message;
import messaging.MessageFlash;
import messaging.MessageImage;
import types.FlashType;
import types.TaskType;

public class Flash extends Component{

	public Flash()
	{
		super(TaskType.FLASH);
	}
	
	public Message notify(Message message)
	{
		int h = ((MessageImage)message).getHeight();
		int w = ((MessageImage)message).getWidth();
		int pix[][][] = ((MessageImage)message).getPixels();
		MessageImage img= new MessageImage(TaskType.FLASH);
		img.setHeight(h);
		img.setWidth(w);
		if (((MessageFlash)message).getFlashType()==FlashType.OFF)  //Flash Off
		{
			img.setPixels(pix);
			return img;
		}
		if (((MessageFlash)message).getFlashType()==FlashType.ON)  //Flash On
		{
			for (int i=0; i<h; i++)
				for (int j=0; j<w; j++)
				{
					pix[i][j][0] += 50;
					pix[i][j][1] += 50;
					pix[i][j][2] += 50;
					if (pix[i][j][0]>255)
						pix[i][j][0] = 255;
					if (pix[i][j][1]>255)
						pix[i][j][1] = 255;
					if (pix[i][j][2]>255)
						pix[i][j][2] = 255;
				}
			img.setPixels(pix);
			return img;
		}
		else    //Flash Auto
		{
			int l = 0;
			for (int i=0; i<h; i++)
				for (int j=0; j<w; j++)
					l += Math.round( 0.2126*pix[i][j][0] + 0.7152*pix[i][j][1] + 0.0722*pix[i][j][2]);
			if (l/(h*w)<60)
			{
				for (int i=0; i<h; i++)
					for (int j=0; j<w; j++)
					{
						pix[i][j][0] += 50;
						pix[i][j][1] += 50;
						pix[i][j][2] += 50;
						if (pix[i][j][0]>255)
							pix[i][j][0] = 255;
						if (pix[i][j][1]>255)
							pix[i][j][1] = 255;
						if (pix[i][j][2]>255)
							pix[i][j][2] = 255;
					}
			}
			img.setPixels(pix);
			return img;
		}
	}

}
