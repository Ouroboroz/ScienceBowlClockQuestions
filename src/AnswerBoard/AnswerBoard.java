package AnswerBoard;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SQLite.SQLiteReader;

public class AnswerBoard extends JFrame {
	GridBagConstraints c;
	JPanel overallPanel;
	JLabel answer;
	JButton getAnswer;
	JComboBox roundDropdown, questionDropdown, typeDropdown, setDropdown;
	String[] roundChoiceList = {"round1","round2","round3","round4","round5","round6","round7","round8","round9","round10","round11","round12","round13","round14","round15","round16","round17","Round1","Round2","Round3","Round4","Round5","Round6","Round7","Round8","Round10","Round11","Round12","Round14","Round15","Round9","Round13","Round16","Round-1C","Round-2C","Round-3C","Round-4C","Round-5C","Round-6C","Round-7C","Round-8C","Round-9C","Round-10C","Round-11C","Round-12C","Round-13C","Round-14C","Round-15C","Round-16C","Round-17C"};
	String[] questionChoiceList = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25"};
	String[] typeChoiceList = {"Toss Up","Bonus"};
	String[] setChoiceList = {"First Set","Second Set"};
	public AnswerBoard(){
		setTitle("Answer Board");
		setSize(500,300);
		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		answer = new JLabel("<html>Please select the answer to view</html>");
		c = new GridBagConstraints();
		roundDropdown = new JComboBox(roundChoiceList);
			roundDropdown.setToolTipText("Selected the Round");
			roundDropdown.setSelectedIndex(0);
		questionDropdown = new JComboBox(questionChoiceList);
			questionDropdown.setToolTipText("Select Question Number");
			questionDropdown.setSelectedIndex(0);
		typeDropdown = new JComboBox(typeChoiceList);
			typeDropdown.setToolTipText("Select Question Type");
			typeDropdown.setSelectedItem(0);
		setDropdown = new JComboBox(setChoiceList);
			setDropdown.setToolTipText("Set the Question Set");
			setDropdown.setSelectedIndex(0);
		getAnswer = new JButton("Get Answer");
		getAnswer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String sAnswer = getAnswer();
				sAnswer = sAnswer.replaceAll("(\\\\n)","<BR>");
				answer.setText("<html>"+sAnswer+"</html>");
			}
			
		});
		overallPanel = new JPanel();
			overallPanel.setLayout(new GridBagLayout());
			c.fill = c.BOTH;
			c.gridy = 0;
			c.gridwidth = 4;
			c.weighty = 0.7;
				overallPanel.add(answer, c);
			c.gridy = 1;
			c.gridx = 0;
			c.weighty = 0.3;
			c.weightx = 0.25;
			c.gridwidth = 1;
				overallPanel.add(roundDropdown, c);
			c.gridx = 1;
				overallPanel.add(questionDropdown, c);
			c.gridx = 2;
				overallPanel.add(typeDropdown, c);
			c.gridx = 3;
				overallPanel.add(setDropdown, c);
			c.gridx = 4;
				overallPanel.add(getAnswer, c);
			add(overallPanel);
	}
	private String getAnswer(){
		String sql = "SELECT answer FROM questions WHERE round == \""+(String)roundDropdown.getSelectedItem()+"\" AND qnumber =="+questionDropdown.getSelectedItem()+";";
		try {
			Connection conn = SQLiteReader.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			if(typeDropdown.getSelectedItem().equals("Bonus")){
				rs.next();
			}
			if(setDropdown.getSelectedItem().equals("Second Set")){
				rs.next();
				rs.next();
			}
				return rs.getString("answer");
		}catch(Exception e1){return "Error: Answer Not Found";}
	}
}
