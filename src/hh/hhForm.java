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
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

public class hhForm {
	
	private static ArrayList<ProfArea> ProfData	= null;
	private static ProfArea selectProfArea	= null;
	private static JComboBox jprof	= null;
	
	public static void setSelectProfArea(ProfArea selectProfArea) {
		
		if (selectProfArea==null) return;
		hhForm.selectProfArea	= selectProfArea;
		if (jprof==null) return;
		jprof.removeAllItems();
		ArrayList<Prof> prof	= selectProfArea.getProf();
		for (int i=0;i<prof.size();i++) {
			jprof.addItem(prof.get(i));
			}
	}
		
	public static void createGUI()
    {
		try {
			ProfData	= (ArrayList<ProfArea>) hhData.getSpecializations();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
        
        JComboBox ProfArea	= new JComboBox(ProfData.toArray());
        vericalPanel.add(ProfArea);
        ActionListener PAactionListener = new SelectProfAreaActionListener();
        ProfArea.addActionListener(PAactionListener);
        
       jprof	= new JComboBox();
       setSelectProfArea(ProfData.get(0));
       vericalPanel.add(jprof);
        
        frame.setPreferredSize(new Dimension(500, 500));
        
        frame.pack();
        frame.setVisible(true);          
   }
}
