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

public class GUI extends JFrame {
	private JTextField szerzodo1 = new JTextField(20);
	private JTextField szerzodo2 = new JTextField(20);
	private JTextField lakcim1 = new JTextField(20);
	private JTextField lakcim2 = new JTextField(20);
	private JTextField szerzodesfajta = new JTextField(20);
	private JTextField adoszam = new JTextField(20);
	private JTextField datum = new JTextField(20);
	private JTextField inputfile = new JTextField(40);
	private FileChooserActionListener choosefile = new FileChooserActionListener(inputfile);
	private JButton choosefilebutton = new JButton("Choose File");
	
	public GUI()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Contract Database");
		setSize(600, 600);
		setResizable(false);
		this.setLayout(new BorderLayout());
		SpringLayout layout = new SpringLayout();
		JPanel datapanel = new JPanel();
		datapanel.setLayout(layout);
		String[] labels = {"Szerzõdõ1:", "Szerzõdõ2:", "Lakcím1:", "Lakcím2:", "Szezõdésfajta:", "Adószám:", "Dátum:"};
		JTextField[] fields = {szerzodo1, szerzodo2, lakcim1, lakcim2, szerzodesfajta, adoszam, datum};
		
		for(int i = 0; i < fields.length; i++)
		{
			Container contentPane = getContentPane();
			JTextField f = fields[i];
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
                    i*30+5,
                    SpringLayout.NORTH, contentPane);
			layout.putConstraint(SpringLayout.NORTH, f,
                    i*30+5,
                    SpringLayout.NORTH, contentPane);			
			
		}
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

	public class ClickActionListener implements ActionListener
	{
		private JFrame j;
		private ClickActionListener(JFrame jj)
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
		m1.addActionListener(new ClickActionListener(this));
		JMenuItem m2 = new JMenuItem("DataBase");
		mb.add(m1);
		mb.add(m2);
		this.setJMenuBar(mb);
	}
	
	public static void main(String[] args)
	{
		new GUI();
	}
	
	
}
