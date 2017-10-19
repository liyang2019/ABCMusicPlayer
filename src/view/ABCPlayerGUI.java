package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

/**
 * The ABC music player GUI.
 * 
 * @author Li Yang, Yue Pan
 * @param <ComboObject> the object type in the combo list.
 *
 */
public class ABCPlayerGUI<ComboObject> extends JFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2716978961603836725L;

	private IModelAdapter<ComboObject> _modelAdapter;

	private final JPanel contentPane = new JPanel();
	private final JPanel pnlCtl = new JPanel();
	private final JTextField txtInput = new JTextField();
	private final JButton btnLoad = new JButton("Load");
	private final JButton btnParse = new JButton("Parse");
	private final JComboBox<ComboObject> comboInstruments = new JComboBox<>();
	private final JButton btnPlay = new JButton("Play");
	
	private final JSplitPane splitPane = new JSplitPane();
	private final JScrollPane spFileContents = new JScrollPane();
	private final JTextArea taFileContents = new JTextArea();
	private final JScrollPane spParsedStructure = new JScrollPane();
	private final JTextArea taParsedStructure = new JTextArea();
	private final JButton btnStop = new JButton("Stop");

	/**
	 * Create the frame.
	 * @param _modelAdapter view to model adapter.
	 */
	public ABCPlayerGUI(IModelAdapter<ComboObject> _modelAdapter) {
		this._modelAdapter = _modelAdapter;
		initGUI();
	}
	
	/**
	 * Start the view, i.e. make it visible.
	 */
	public void start() {
		setVisible(true);
	}
	
	/**
	 * Initialize the GUI components
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pnlCtl.setToolTipText("the controll panel");
		getContentPane().add(pnlCtl, BorderLayout.NORTH);
		
		JLabel lblFile = new JLabel("File:");
		pnlCtl.add(lblFile);
		txtInput.setToolTipText("input a .abc music file name (withour .abc)");
		
		txtInput.setText("music file name");
		pnlCtl.add(txtInput);
		txtInput.setColumns(10);
		btnLoad.setToolTipText("load the abc music file");
		
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taFileContents.setText(_modelAdapter.loadFile(txtInput.getText()));
			}
		});
		pnlCtl.add(btnLoad);
		btnParse.setToolTipText("parse the abc music file");
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taParsedStructure.setText(_modelAdapter.parsePhrase(txtInput.getText()));
			}
		});
		
		pnlCtl.add(btnParse);
		comboInstruments.setToolTipText("to select a instrument to play the abc music file");
		pnlCtl.add(comboInstruments);
		btnPlay.setToolTipText("play the music");
		btnPlay.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				_modelAdapter.play((ComboObject) comboInstruments.getSelectedItem());
			}
		});
		pnlCtl.add(btnPlay);
		btnStop.setToolTipText("stop the current play of the music");
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_modelAdapter.stop();
			}
		});
		pnlCtl.add(btnStop);
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.5);
		contentPane.add(splitPane, BorderLayout.CENTER);
		splitPane.setLeftComponent(spFileContents);
		taFileContents.setBorder(new TitledBorder(null, "File Contents", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		taFileContents.setToolTipText("The abc music file content is displayed here");
		taFileContents.setLineWrap(true);
		taFileContents.setWrapStyleWord(true);
		spFileContents.setViewportView(taFileContents);
		splitPane.setRightComponent(spParsedStructure);
		taParsedStructure.setBorder(new TitledBorder(null, "Parsed IPhrase Structure", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		taParsedStructure.setToolTipText("The parsed abc music file phrase content is displayed here.");
		taParsedStructure.setLineWrap(true);
		taParsedStructure.setWrapStyleWord(true);
		spParsedStructure.setViewportView(taParsedStructure);
	}

	/**
	 * list available combo objects.
	 * 
	 * @param comboObjects available combo objects. 
	 */
	public void listInstruments(ComboObject[] comboObjects) {
		for (ComboObject comboObject : comboObjects) {
			comboInstruments.addItem(comboObject);
		}
		comboInstruments.setSelectedIndex(0);
	}
}
