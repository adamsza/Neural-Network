package user_interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.filechooser.FileFilter;

import network.Network;
import network.NetworkHolder;
import neural.Evaluator;
import tools.TxtFileFilter;

public class GUI extends JFrame {
	private JTextField[] fields;
	private JTextField inputfile = new JTextField(35);
	private FileChooserActionListener choosefile = new FileChooserActionListener(inputfile);
	private JButton choosefilebutton = new JButton("Choose File");
	private JButton startbutton = new JButton("Start");
	
	private ArrayList<Network> networks;
	
	public GUI(ArrayList<Network> networklist)
	{
		networks = networklist;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Contract Database");
		setSize(600, 600);
		setResizable(false);
		this.setLayout(new BorderLayout());
		SpringLayout layout = new SpringLayout();
		JPanel datapanel = new JPanel();
		datapanel.setLayout(layout);
		String[] labels = {"Szerzõdõ1:", "Lakcím1:", "Személyi1", "Adószám1:", "Szerzõdõ2:", "Lakcím2:", "Személyi2", "Adószám2:", "Szezõdésfajta:", "Dátum:"};
		fields = new JTextField[10];
		Container contentPane = getContentPane();
		int j = 0;
		for(int i = 0; i < fields.length; i++)
		{
			if(i == 4 || i == 8) j++;
			JTextField f = new JTextField(20);
			fields[i] = f;
			f.setEditable(false);
			JLabel l = new JLabel(labels[i]);
			datapanel.add(l);
			l.setLabelFor(f);
			datapanel.add(fields[i]);
			layout.putConstraint(SpringLayout.WEST, f,
                    100,
                    SpringLayout.WEST, contentPane);
			layout.putConstraint(SpringLayout.WEST, l,
                    5,
                    SpringLayout.WEST, contentPane);
			layout.putConstraint(SpringLayout.NORTH, l,
                    j*30+5,
                    SpringLayout.NORTH, contentPane);
			layout.putConstraint(SpringLayout.NORTH, f,
                    j*30+5,
                    SpringLayout.NORTH, contentPane);
			
			j++;
		}
		
		startbutton.addActionListener(new StartClickActionListener());
		datapanel.add(startbutton);
		layout.putConstraint(SpringLayout.EAST, startbutton,
                -50,
                SpringLayout.EAST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, startbutton,
                190,
                SpringLayout.NORTH, contentPane);
		
		JPanel inputpanel = new JPanel();
		inputpanel.add(inputfile);
		choosefilebutton.addActionListener(choosefile);
		inputpanel.add(choosefilebutton);
		menubar();
		add(inputpanel, BorderLayout.PAGE_START);
		add(datapanel, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void remove()
	{
		removeAll();
		add(new JPanel());
		update(this.getGraphics());
	}

	public class MenuClickActionListener implements ActionListener
	{
		private JFrame j;
		private MenuClickActionListener(JFrame jj)
		{
			j = jj;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			j.getContentPane().removeAll();
		    j.getContentPane().add(new JPanel());
		    j.update(getGraphics());
		}
		
	}
	
	public class StartClickActionListener implements ActionListener
	{
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String s = inputfile.getText();
			Evaluator evaluator = new Evaluator(networks);
			evaluator.execute(inputfile.getText());
			setText(evaluator.getDataList());
		}
	}
	
	public void setText(ArrayList<String> datalist)
	{
		for(int i = 0; i < fields.length; i++)
		{
			fields[i].setText(datalist.get(i));
		}
	}
	
	public class FileChooserActionListener implements ActionListener
	{
		private JTextField input;
		private JFileChooser inputchooser;
		private FileChooserActionListener(JTextField tf)
		{
			input = tf;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			inputchooser = new JFileChooser();
			inputchooser.setAcceptAllFileFilterUsed(false);
			FileFilter ff = new TxtFileFilter();
			inputchooser.setFileFilter(ff);
			
			if(inputchooser.showOpenDialog(null) == 0)
			{
				File choosen = inputchooser.getSelectedFile();
				input.setText(choosen.getPath());
			}
		}
		
	}
	
	public void menubar() {
		JMenuBar mb = new JMenuBar();		
		JMenuItem m1 = new JMenuItem("Add New Data");
		m1.addActionListener(new MenuClickActionListener(this));
		JMenuItem m2 = new JMenuItem("...");
		mb.add(m1);
		mb.add(m2);
		this.setJMenuBar(mb);
	}
		
}