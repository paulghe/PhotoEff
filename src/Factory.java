import types.FlashType;

import components.BlackWhite;
import components.Blur;
import components.Component;
import components.Flash;
import components.ImageLoader;
import components.ImageSaver;
import components.NormalPhoto;
import components.RawPhoto;
import components.Sepia;
import components.Zoom;

/**
 * Optional class for building instances based on a string
 *
 */
public class Factory {
	private static Factory instance = new Factory();
	private Factory(){}
	
	public static Factory createFactory() {
		return instance;
	}
	
	public Component getComponent(String name) {
		if (name.equals("Flash")==true)
			return new Flash();
		if (name.equals("NormalPhoto")==true)
			return new NormalPhoto();
		if (name.equals("Zoom")==true)
			return new Zoom(); 			
		if (name.equals("Blur")==true)
			return new Blur();
		if (name.equals("RawPhoto")==true)
			return new RawPhoto();
		if (name.equals("Sepia")==true)
			return new Sepia();
		if (name.equals("BlackWhite")==true)
			return new BlackWhite();
		if (name.equals("ImageLoader")==true)
			return new ImageLoader();
		if (name.equals("ImageSaver")==true)
			return new ImageSaver();	
		return null;
	}
	
	public FlashType getFlashType(String string) {
		

		return null;
	}
	
}
