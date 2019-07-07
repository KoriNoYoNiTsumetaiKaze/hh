package hh;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.json.simple.parser.ParseException;

public class hhForm {
	
	private static ArrayList<ProfArea> ProfData	= null;
	private static ProfArea selectProfArea		= null;
	private static Prof selectProf				= null;
	private static JComboBox jprof				= null;
	private static JLabel PALabel				= null;
	private static JLabel profLabel				= null;
	private static ArrayList<Job> findJobs		= null;
	private static JTable tableFJs				= null;
	private static JTextField PageField			= null;
	private static JLabel PagesLabel			= null;

	public static String getPage() {
		return PageField.getText();
	}

	public static int getPages() {
		return Integer.parseInt(PagesLabel.getText());
	}
	
	public static void setPages(String page, String pages) {
		PageField.setText(page);
		PagesLabel.setText(pages);
	}
	
	public static ProfArea getSelectProfArea() {
		return selectProfArea;
	}

	public static Prof getSelectProf() {
		return selectProf;
	}
	
	public static void setSelectProfArea(ProfArea selectProfArea) {
		if (selectProfArea==null) return;
		hhForm.selectProfArea	= selectProfArea;
		if (PALabel!=null) PALabel.setText(selectProfArea.toString());
		if (jprof==null) return;
		jprof.removeAllItems();
		ArrayList<Prof> prof	= selectProfArea.getProf();
		if (prof==null) return;
		for (int i=0;i<prof.size();i++) {
			jprof.addItem(prof.get(i));
			}
	}

	public static void setSelectProf(Prof selectProf) {
		if (selectProf==null) return;
		hhForm.selectProf	= selectProf;
		if (profLabel!=null) profLabel.setText(selectProf.toString());
	}

	public static void setFindJobs(ArrayList<Job> findJobs) {
		if (findJobs==null) return;
		hhForm.findJobs	= findJobs;
		if (tableFJs==null) return;
		//tableFJs.removeAll();
		DefaultTableModel model = (DefaultTableModel) tableFJs.getModel();
		model.setRowCount(0);
		for (int i=0;i<findJobs.size();i++) {
			Job fj	= findJobs.get(i);
			Vector<String> newRow = new Vector<String>();
			newRow.add(fj.getName());
			newRow.add(fj.getEmployerName());
			model.addRow(newRow);			
			}		
	      }

	public static void createGUI() {
		try {
			ProfData	= (ArrayList<ProfArea>) hhData.getSpecializations();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        JFrame frame	= new JFrame("hh");
        Container cp	= frame.getContentPane(); 
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel	= new JPanel();
        mainPanel.setLayout(new BorderLayout());        
        cp.add(mainPanel);

        int widthPanel	= 15;
        int heightPanel	= 20; 
        
        JPanel northPanel	= new JPanel();
        northPanel.setPreferredSize(new Dimension(widthPanel,heightPanel));
        mainPanel.add(northPanel,BorderLayout.NORTH);

        PALabel = new JLabel("");
		PALabel.setVerticalAlignment(JLabel.CENTER);
		PALabel.setHorizontalAlignment(JLabel.LEFT);
		northPanel.add(PALabel);

        JPanel southPanel	= new JPanel();
        southPanel.setPreferredSize(new Dimension(widthPanel,heightPanel));
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        mainPanel.add(southPanel,BorderLayout.SOUTH);

        JLabel BorderLeftLabel = new JLabel();
        BorderLeftLabel.setPreferredSize(new Dimension(widthPanel,heightPanel));
        southPanel.add(BorderLeftLabel);
        PageField = new JTextField("0");
        southPanel.add(PageField);
        JLabel from = new JLabel(" из ");
        southPanel.add(from);
        PagesLabel = new JLabel("0");
        southPanel.add(PagesLabel);
        JLabel pagesText = new JLabel(" страниц");
        southPanel.add(pagesText);
        JLabel BorderRightLabel = new JLabel();
        BorderRightLabel.setPreferredSize(new Dimension(widthPanel,heightPanel));
        southPanel.add(BorderRightLabel);
        
        JPanel westPanel	= new JPanel();
        westPanel.setPreferredSize(new Dimension(widthPanel,heightPanel));
        mainPanel.add(westPanel,BorderLayout.WEST);

        JPanel eastPanel	= new JPanel();
        eastPanel.setPreferredSize(new Dimension(widthPanel,heightPanel));
        mainPanel.add(eastPanel,BorderLayout.EAST);

        JPanel vericalPanel	= new JPanel();
        vericalPanel.setLayout(new BoxLayout(vericalPanel, BoxLayout.Y_AXIS));
        vericalPanel.setPreferredSize(new Dimension(100, 100));
        mainPanel.add(vericalPanel,BorderLayout.CENTER);
                
        JComboBox PABox	= new JComboBox(ProfData.toArray());
        vericalPanel.add(PABox);
        ActionListener PAactionListener = new SelectProfAreaActionListener();
        PABox.addActionListener(PAactionListener);
        
        profLabel = new JLabel("");
        profLabel.setVerticalAlignment(JLabel.CENTER);
        profLabel.setHorizontalAlignment(JLabel.LEFT);
        vericalPanel.add(profLabel);
        
       jprof	= new JComboBox();
       setSelectProfArea(ProfData.get(0));
       vericalPanel.add(jprof);
       ActionListener ProfActionListener = new SelectProfActionListener();
       jprof.addActionListener(ProfActionListener);
       
       JButton button = new JButton("Найти вакансии");
       vericalPanel.add(button);
       ActionListener buttonListener = new FindJobsActionListener();
       button.addActionListener(buttonListener);

       Vector<String> TabHeader = new Vector<String>();
       TabHeader.add("Вакансия");
       TabHeader.add("Работодатель");
       DefaultTableModel model = new DefaultTableModel(TabHeader, 0);
       tableFJs = new JTable();
       tableFJs.setModel(model);
       JScrollPane scrollPane = new JScrollPane(tableFJs);
       vericalPanel.add(scrollPane);
        
        frame.setPreferredSize(new Dimension(500, 500));
        
        frame.pack();
        frame.setVisible(true);          
   }
}
