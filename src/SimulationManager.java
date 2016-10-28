import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import messaging.MessCenter;
import messaging.MessageCenter;
import messaging.MessageFlash;
import messaging.MessageImage;
import messaging.MessageLoad;
import messaging.MessageSave;
import messaging.MessageSuccess;
import messaging.MessageZoom;
import types.FlashType;
import types.TaskType;


public class SimulationManager {
	private MessageCenter messageCenter;
	private MessageCenter[] Centers;


	
	public SimulationManager(String networkConfigFile) {
		this.messageCenter = buildNetwork(networkConfigFile);
	}
	
	
	/**
	 * Builds the network of message centers.
	 * @param networkConfigFile configuration file
	 * @return the first message center from the config file
	 */
	private MessageCenter buildNetwork(String networkConfigFile) {
		try {
			Scanner input= new Scanner(new File(networkConfigFile));
			int n = input.nextInt();
			Centers = new MessCenter[n];    //n number of MessageCenters
			String s = input.nextLine();
			String[] parts;
			for (int i=0; i<n; i++)   // Creating Centers & Components associated
			{
				s = input.nextLine();
				parts = s.split(" "); 	
				Centers[i] = new MessCenter(parts[0],parts.length,n); // Creating new MessageCenter with parts.length Components and n Neighbours
				for (int j=1; j<parts.length; j++)     // Subscribing Components 
					Centers[i].subscribe(Factory.createFactory().getComponent(parts[j]));
			}
			for (int i=0; i<n; i++)   
			{
				s = input.nextLine();
				parts = s.split(" ");
				for (int j=1; j<parts.length; j++) // Setting Neighbours
					for (int k=0; k<n; k++)  
						if (parts[j].equals(Centers[k].getName())==true) 
						{
							Centers[i].setNeighbour((MessCenter)Centers[k]);
							break;
						}
			}
			input.close();
		} catch (FileNotFoundException e)
		{
			System.out.println("File Not Found");
		}
		
		return Centers[0];
	}
	
	
	/**
	 * Reads the commands from stdin and uses the messageCenter to solve all the tasks
	 */
	public void start() {
		Scanner input = new Scanner(System.in);
		String s = input.nextLine();
		String[] parts;
		while (s.equals("exit")==false)
		{
			parts = s.split(" ");
			// parts[0] <- ImageLoader
			MessageLoad load = new MessageLoad(TaskType.IMAGE_LOAD, parts[0]);
			MessageImage img = (MessageImage)this.messageCenter.publish(load);
			
			
			// parts[2] <- Pre-Capture
			MessageFlash image = new MessageFlash(TaskType.FLASH,FlashType.AUTO,img.getPixels(),img.getWidth(),img.getHeight());
			if (parts[2].charAt(10)=='o')  //Flash on / off
			{				
				if (parts[2].charAt(11)=='n')  // Flash ON
					image.setFlashType(FlashType.ON);
				else                           //Flash OFF
					image.setFlashType(FlashType.OFF);
			} 
			img = (MessageImage)this.messageCenter.publish(image);
			// Zoom
			if (parts[2].indexOf("zoom")!=-1) // Zoom Active
			{
				// Getting the Strings with just the coords
				String[] coords;
				coords =  parts[2].substring( parts[2].indexOf("zoom")+5,parts[2].indexOf(")") ).split(",");
				int x1 = Integer.parseInt(coords[0]);
				int y1 = Integer.parseInt(coords[1]);
				int x2 = Integer.parseInt(coords[2]);
				int y2 = Integer.parseInt(coords[3]);
				MessageZoom zoom = new MessageZoom(TaskType.ZOOM,x1,y1,x2,y2,img.getPixels(),img.getWidth(),img.getHeight());
				img = (MessageImage)this.messageCenter.publish(zoom);
			}
			

			// parts[3] <- Capture
			img.setTaskType(TaskType.RAW_PHOTO);
			MessageImage raw = new MessageImage(TaskType.RAW_PHOTO,img.getPixels(),img.getWidth(),img.getHeight());
			img = (MessageImage)this.messageCenter.publish(raw);
			
			if (parts[3].indexOf("normal")!=-1) // Normal Photo
			{
				MessageImage normal = new MessageImage( TaskType.NORMAL_PHOTO,img.getPixels(),img.getWidth(),img.getHeight() );
				img = (MessageImage)this.messageCenter.publish(normal);
			}
				
			
			// parts[4] <- Post-Capture
			String []postparts;
			postparts = parts[4].substring( parts[4].indexOf("(")+1,parts[4].indexOf(")") ).split(";");
			for (int i=0; i<postparts.length; i++)
			{
				MessageImage filter;
				if (postparts[i].equals("blur")==true)
					filter = new MessageImage(TaskType.BLUR,img.getPixels(),img.getWidth(),img.getHeight());
				else if (postparts[i].equals("sepia")==true)
					filter = new MessageImage(TaskType.SEPIA,img.getPixels(),img.getWidth(),img.getHeight());
				else if (postparts[i].equals("black_white")==true)
					filter = new MessageImage(TaskType.BLACK_WHITE,img.getPixels(),img.getWidth(),img.getHeight());
				else 
					break;
				img = (MessageImage)this.messageCenter.publish(filter);
			}
			
			
			//parts[1]  <- ImageSave
			MessageSave save = new MessageSave(TaskType.IMAGE_SAVE, img.getPixels(),img.getWidth(),img.getHeight(),parts[1]);
			MessageSuccess suc = (MessageSuccess)this.messageCenter.publish(save);
			s = input.nextLine();
		}
		input.close();
	
	}
	
	
	/**
	 * Main method
	 * @param args program arguments
	 */
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Usage: java SimulationManager <network_config_file>");
			return;
		}
		SimulationManager simulationManager = new SimulationManager(args[0]);
		simulationManager.start();
	}

}
