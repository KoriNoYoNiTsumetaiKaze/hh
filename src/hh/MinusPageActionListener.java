package hh;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;

import org.json.simple.parser.ParseException;

public class MinusPageActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int page	= hhForm.getPage();
		int pages	= hhForm.getPages();
		page--;
		if (page<0) page	= pages-1;
		if (page<0) page	= 0;
		hhForm.setPage(page);
		try {
			hhForm.setFindJobs(hhData.getJobs());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
