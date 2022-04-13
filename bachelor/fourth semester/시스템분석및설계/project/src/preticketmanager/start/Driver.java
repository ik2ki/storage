package preticketmanager.start;

import java.io.IOException;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import preticketmanager.Loader;
import preticketmanager.Timethreadsmanager;
import preticketmanager.customui.*;

public class Driver {
	public static LoadingFrame_GUI loadingScreen = new LoadingFrame_GUI();
	public static MainFrame_GUI mainFrame;
	public static final String BASEROUTE = "./src/preticketmanager/data/";
	public static void main(String args[]) throws RowsExceededException, WriteException, IOException
	{
		Loader loader = new Loader();
		loadingScreen.display();
		loader.load();
		loadingScreen.dispose();
		
		mainFrame = new MainFrame_GUI();
		mainFrame.display();
		
		Thread t = new Thread(mainFrame);
		t.start();
		
		try {
			Timethreadsmanager time = new Timethreadsmanager();
			time.start();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
