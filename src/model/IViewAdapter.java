package model;

import provided.util.ABCInstrument;

/**
 * Model to view adapter.
 * 
 * @author Li Yang, Yue Pan
 *
 */
public interface IViewAdapter {
	/**
	 * To initialize a null object to the IViewAdapter
	 */
	public static final IViewAdapter NULL_OBJECT = new IViewAdapter() {

		@Override
		public void listInstruments(ABCInstrument[] instruments) {
		}

	};

	/**
	 * list the instruments available for the MIDI system in the view.
	 * @param instruments The available instruments for the MIDI system.
	 */
	public void listInstruments(ABCInstrument[] instruments);

}
