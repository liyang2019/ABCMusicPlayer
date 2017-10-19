package view;

/**
 * The view to model adapter.
 * 
 * @author Yang Li, Yue Pan
 * @param <ComboObject> the object type in the combo list.
 *
 */
public interface IModelAdapter<ComboObject> {

	/**
	 * @param fileName fileName.abc is the ABC file name.
	 * @return the string content of ABC file
	 */
	String loadFile(String fileName);

	/**
	 * parse the fileName.abc file and save the parsed IPharse object in model,
	 * return the string representation of the parse IPharse object.
	 * 
	 * @param fileName fileName.abc is the ABC file name.
	 * @return the string representation of parsed ABC file
	 */
	String parsePhrase(String fileName);

	/**
	 * play the parsed ABC music file.
	 * @param comboObject  object type in the combo list for use to play the music.
	 */
	public void play(ComboObject comboObject);
	/**
	 * stop tp play the ABC music file
	 */
	public void stop();

}
