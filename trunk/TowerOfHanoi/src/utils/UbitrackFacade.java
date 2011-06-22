package utils;

import java.io.File;
import ubitrack.SimpleFacade;
import ubitrack.SimpleImageReceiver;
import ubitrack.SimplePoseReceiver;
import ubitrack.ubitrack;

public class UbitrackFacade {
	
	static {

		System.loadLibrary("ubitrack_java");
	}

	private SimpleFacade facade;
        public final static String COMPONENT_DIRECTORY = System.getProperty("user.dir") + File.separator + "libs" + File.separator + "ubitrack" + File.separator + "bin" + File.separator + "ubitrack";
	public final static String DATAFLOW_PATH = System.getProperty("user.dir") + File.separator + "dataflow" + File.separator + "3D-UI-SS-2011-Markertracker_Direct.dfg";
	public void initUbitrack() {
		ubitrack.initLogging();
		
		facade = new SimpleFacade(COMPONENT_DIRECTORY);
		
		if (facade.getLastError() != null) 
                {
			return;
		}
		if (!facade.loadDataflow(DATAFLOW_PATH)) 
                {
			return;
		}
	}
	
	public boolean setPoseCallback(String name, SimplePoseReceiver poseReceiver){
		return facade.setPoseCallback(name, poseReceiver);
	}
	
	public boolean setImageCallback(String name, SimpleImageReceiver imageReceiver){
		return facade.setImageCallback(name, imageReceiver);
	}

	public void startDataflow(){
		facade.startDataflow();
	}
	public void stopUbitrack() {
		System.out.println("stopUbitrack");
		facade.stopDataflow();
		 
		System.gc();
		System.runFinalization();
	}
	
}
