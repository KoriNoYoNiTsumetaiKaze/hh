package hh;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class hhForm {
	public static void createGUI()
    {
        JFrame frame = new JFrame("Test frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        String[] items = {
        		"Элемент списка 1",
        		"Элемент списка 2",
        		"Элемент списка 3"
        		};
        JComboBox comboBox = new JComboBox(items);
        frame.getContentPane().add(comboBox);
        
        frame.setPreferredSize(new Dimension(200, 100));
        
        frame.pack();
        frame.setVisible(true);          
   }
}
