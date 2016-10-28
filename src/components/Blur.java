package components;

import messaging.Message;
import messaging.MessageImage;
import types.TaskType;

public class Blur extends Component{
	
	public Blur()
	{
		super(TaskType.BLUR);
	}
	
	public Message notify(Message message)
	{
		MessageImage image = new MessageImage(TaskType.BLUR);
		int h = ((MessageImage)message).getHeight();
		int w = ((MessageImage)message).getWidth();
		int pix[][][] = ((MessageImage)message).getPixels();
		int newpix[][][]= new int[h][w][3];
		int sumRed,sumBlue,sumGreen,vec;
		image.setHeight(h);
		image.setWidth(w);
		for (int ten=1; ten<=10; ten++)
		{
			for (int i=0; i<h; i++)
				for (int j=0; j<w; j++)
				{
					sumRed = 0;
					sumBlue = 0;
					sumGreen = 0;
					vec = 0;
					for (int RowMod=-1; RowMod<=1; RowMod++)
						for (int ColMod=-1; ColMod<=1; ColMod++)
						{
							if (i+RowMod >= 0 && i+RowMod < h && j+ColMod >= 0 && j+ColMod < w && (RowMod != 0 || ColMod  != 0))
							{
								sumRed += pix[i+RowMod][j+ColMod][0];
								sumGreen += pix[i+RowMod][j+ColMod][1];
								sumBlue += pix[i+RowMod][j+ColMod][2];
								vec++;
							}
						}
					if (vec==0) // One pixel
					{
						for (int k=0; k<3; k++)
							pix[i][j][k] = 0;
						image.setPixels(pix);
						return image;
					}
					newpix[i][j][0] = (int)Math.round(sumRed/(double)vec);
					newpix[i][j][1] = (int)Math.round(sumGreen/(double)vec);
					newpix[i][j][2] = (int)Math.round(sumBlue/(double)vec);
				}
			for (int i=0; i<h; i++)
				for (int j=0; j<w; j++)
					for (int k=0; k<3; k++)
						pix[i][j][k] = newpix[i][j][k];
		}
		image.setPixels(newpix);
		return image;
	}

}
