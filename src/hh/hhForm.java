package hh;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

public class hhForm {
	
	private static ArrayList<ProfArea> ProfData	= null;
	private static ProfArea selectProfArea	= null;
	private static Prof selectProf	= null;
	private static JComboBox jprof	= null;
	private static JLabel PALabel	= null;
	private static JLabel profLabel	= null;
	
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

	public static void createGUI()
    {
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
        int heightPanel	= widthPanel; 
        
        JPanel northPanel	= new JPanel();
        northPanel.setPreferredSize(new Dimension(widthPanel,heightPanel));
        mainPanel.add(northPanel,BorderLayout.NORTH);

        JPanel southPanel	= new JPanel();
        southPanel.setPreferredSize(new Dimension(widthPanel,heightPanel));
        mainPanel.add(southPanel,BorderLayout.SOUTH);

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
        
        PALabel = new JLabel("");
		PALabel.setVerticalAlignment(JLabel.CENTER);
		PALabel.setHorizontalAlignment(JLabel.LEFT);
        vericalPanel.add(PALabel);
        
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
        
        frame.setPreferredSize(new Dimension(500, 500));
        
        frame.pack();
        frame.setVisible(true);          
   }
}
