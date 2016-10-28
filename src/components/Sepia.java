package components;

import types.TaskType;
import messaging.Message;
import messaging.MessageImage;

public class Sepia extends Component{
	
	public Sepia()
	{
		super(TaskType.SEPIA);
	}
	
	public Message notify(Message message)
	{
		MessageImage image = new MessageImage(TaskType.SEPIA);
		int h = ((MessageImage)message).getHeight();
		int w = ((MessageImage)message).getWidth();
		int pix[][][] = ((MessageImage)message).getPixels();
		int r,g,b;
		for (int i=0; i<h; i++)
			for (int j=0; j<w; j++)
			{
				r = (int)Math.round( pix[i][j][0]*0.393 + pix[i][j][1]*0.769 + pix[i][j][2]*0.189 ); 
				g = (int)Math.round( pix[i][j][0]*0.349 + pix[i][j][1]*0.686 + pix[i][j][2]*0.168 );
				b = (int)Math.round( pix[i][j][0]*0.272 + pix[i][j][1]*0.534 + pix[i][j][2]*0.131 );
				if (r > 255)
					r = 255;
				if (g > 255)
					g = 255;
				if (b > 255)
					b = 255;
				pix[i][j][0] = r;
				pix[i][j][1] = g;
				pix[i][j][2] = b;
			}
		image.setHeight(h);
		image.setWidth(w);
		image.setPixels(pix);
		return image;
	}

}
