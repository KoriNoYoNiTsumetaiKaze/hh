package hh;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class FindJobsActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
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
