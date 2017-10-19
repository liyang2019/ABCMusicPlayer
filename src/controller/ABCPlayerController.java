package controller;

import java.awt.EventQueue;

import model.ABCPlayerModel;
import model.IViewAdapter;
import provided.util.ABCInstrument;
import view.ABCPlayerGUI;
import view.IModelAdapter;

/**
 * The ABC player controller. It controls the GUI view and the .abc file parser model.
 * 
 * @author Li Yang, Yue Pan
 *
 */
public class ABCPlayerController {
	
	/**
	 * the ABC music player GUI view.
	 */
	private ABCPlayerGUI<ABCInstrument> view;
	/**
	 * the ABC music player model.
	 */
	private ABCPlayerModel model;
	
	/**
	 * constructor for the ABC player controller.
	 */
	public ABCPlayerController() {
		// initialize the view
		view = new ABCPlayerGUI<ABCInstrument>(new IModelAdapter<ABCInstrument>() {
			
			@Override
			/**
			 * load .abc file given fileName.
			 */
			public String loadFile(String fileName) {
				return model.loadFile(fileName);
			}

			@Override
			/**
			 * parse .abc file given fileName.
			 */
			public String parsePhrase(String fileName) {
				return model.parseFile(fileName);
			}

			@Override
			/**
			 * play the parsed .abc music file.
			 */
			public void play(ABCInstrument instrumemt) {
				model.startplay(instrumemt);
			}

			@Override
			/**
			 * stop the current playing.
			 */
			public void stop() {
				model.stopPlay();
			}
		});
		
		model = new ABCPlayerModel(new IViewAdapter() {

			@Override
			/**
			 * initialize the GUI to list all the instruments to play music.
			 */
			public void listInstruments(ABCInstrument[] instruments) {
				view.listInstruments(instruments);
			}
			
		});
	}
	
	/**
	 * Start the application
	 */
	public void start() {
		model.start();
		view.start();
	}
	
	/**
	 * Launch the application.   Sets the window closing behavior to WindowConstants.EXIT_ON_CLOSE
	 * @param args input parameters.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					(new ABCPlayerController()).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
