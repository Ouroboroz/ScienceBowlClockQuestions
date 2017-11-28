package QuestionBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import SQLite.SQLiteReader;

public class QuestionBoard extends JFrame {
	private JPanel overallPanel, roundPanel, typeNnumberNtopicPanel, questionPanel, answerPanel, buttonPanel;
	private JButton showAnswer, nextQuestion, previousQuestion;
	private JLabel type, round, topic, questionNumber, question, answer;
	private JPanel typeP, roundP, topicP, questionNP, questionP, answerP;
	private JComboBox<String> roundDropdown;
	private String[] dropdownChoices = {"round1","round2","round3","round4","round5","round6","round7","round8","round9","round10","round11","round12","round13","round14","round15","round16","round17","Round1","Round2","Round3","Round4","Round5","Round6","Round7","Round8","Round10","Round11","Round12","Round14","Round15","Round9","Round13","Round16","Round-1C","Round-2C","Round-3C","Round-4C","Round-5C","Round-6C","Round-7C","Round-8C","Round-9C","Round-10C","Round-11C","Round-12C","Round-13C","Round-14C","Round-15C","Round-16C","Round-17C"};
	private JPanel aP, nP, pP, dP;
	private int arrayNumber = 0;
	GridBagConstraints c;
	
	public QuestionBoard(){
		setTitle("Question Board");
		setSize(690,1000);
		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		//Button that shows the answer which normally would be hidden
		showAnswer = new JButton("Show Answer");
		showAnswer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(evt.getActionCommand().equals("Show Answer")){
					//Displays answer
					updateAnswer();
					//Changes button to hide answer
					showAnswer.setText("Hide Answer");
				}
				if(evt.getActionCommand().equals("Hide Answer")){
					//Hides answer
					hideAnswer();
					//Changes button to show answer
					showAnswer.setText("Show Answer");
				}
			}
		});
		//Button that goes to the next question
		nextQuestion = new JButton("Next Question");
			nextQuestion.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					//If the round is not the same
					if(!round.getText().equals((String)roundDropdown.getSelectedItem())){
						//Set the new round
						SQLiteReader.selectRound((String)roundDropdown.getSelectedItem());
						updateRound();
						//Sets array to the beginning
						arrayNumber = 0;
						//Reverse increment so the index would remain at 0
						incrementQuestionNumber(false);
					}
					else
						//Increases the array index normally
						incrementQuestionNumber(true);
					//Set to Show Answer
					showAnswer.setText("Show Answer");
					//Updates the question
					newQuestionUpdate();
				}
			});
		//Button that goes to the previous question
		previousQuestion = new JButton("Previous Question");
			previousQuestion.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					//Decrease array number
					incrementQuestionNumber(false);
					//Set to Show Answer
					showAnswer.setText("Show Answer");
					//Update question
					newQuestionUpdate();
				}
			});
		//Default text
		type = new JLabel("Science");
		round = new JLabel("Bowl");
		topic = new JLabel("Clock");
		questionNumber = new JLabel("1");
		question = new JLabel("Example Question");
		answer = new JLabel("Example Answer");
		overallPanel = new JPanel();
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		roundPanel = new JPanel();
			roundP = new JPanel();
			roundP.setLayout(new GridBagLayout());
			roundP.add(round);
			roundPanel.setLayout(new GridBagLayout());
			roundPanel.add(roundP);
		typeNnumberNtopicPanel = new JPanel();
			typeNnumberNtopicPanel.setBackground(new Color(0,0,255));
			typeNnumberNtopicPanel.setLayout(new GridBagLayout());
			c.gridx = 0;
			c.weightx = 0.4;
			typeP = new JPanel();
			typeP.setPreferredSize(new Dimension((int)(overallPanel.getSize().width*0.4),(int)(overallPanel.getSize().height*0.1)));
			typeP.setBackground(new Color(255,0,0));
			typeP.setLayout(new GridBagLayout());
			typeP.add(type);
			typeNnumberNtopicPanel.add(typeP, c);
			c.gridx = 1;
			c.weightx = 0.2;
			questionNP = new JPanel();
			questionNP.setLayout(new GridBagLayout());
			questionNP.add(questionNumber);
			typeNnumberNtopicPanel.add(questionNP,c);
			c.gridx = 2;
			c.weightx = 0.4;
			topicP = new JPanel();
			topicP.setPreferredSize(new Dimension((int)(overallPanel.getSize().width*0.4),(int)(overallPanel.getSize().height*0.1)));
			topicP.setLayout(new GridBagLayout());
			topicP.add(topic);
			typeNnumberNtopicPanel.add(topicP,c);
		questionPanel = new JPanel();
			c.weightx = 1;
			questionP = new JPanel();
			questionP.setLayout(new GridBagLayout());
			questionP.add(question,c);
			questionPanel.setPreferredSize(new Dimension(overallPanel.getSize().width,(int)(overallPanel.getSize().height *0.6)));
			question.setHorizontalAlignment(question.CENTER);
			question.setFont(new Font("SansSerif",Font.PLAIN,20));
			questionPanel.setLayout(new GridBagLayout());
			questionPanel.setBackground(new Color(0,255,0));
			questionP.setBackground(new Color(0,255,0));
			questionPanel.add(questionP,c);
		answerPanel = new JPanel();
			answerP = new JPanel();
			answerP.setLayout(new GridBagLayout());
			answerP.add(answer);
			answerPanel.setLayout(new GridBagLayout());
			c.gridx = 0;
			c.weightx = 0.7;
			answerP.setPreferredSize(new Dimension((int)(overallPanel.getSize().width*0.7),(int)(overallPanel.getSize().height*0.2)));
			answerPanel.add(answerP, c);
			c.gridx = 1;
			c.weightx = 0.3;
			aP = new JPanel();
			aP.setLayout(new GridBagLayout());
			aP.add(showAnswer);
			answerPanel.add(aP,c);
		roundDropdown = new JComboBox<String>(dropdownChoices);
		dP = new JPanel();
		dP.setLayout(new GridBagLayout());
		dP.add(roundDropdown);
		roundDropdown.setSelectedIndex(0);
		buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridBagLayout());
			c.gridx = 0;
			c.weightx = 0.3;
			pP = new JPanel();
			pP.setLayout(new GridBagLayout());
			pP.add(previousQuestion);
			buttonPanel.add(pP,c);
			c.gridx = 1;
			c.weightx = 0.4;
			buttonPanel.add(dP, c);
			c.gridx = 2;
			c.weightx = 0.3;
			nP = new JPanel();
			nP.setLayout(new GridBagLayout());
			nP.add(nextQuestion);
			buttonPanel.add(nP, c);
			overallPanel.setLayout(new GridBagLayout());
			c.gridy = 0;
			c.weighty = 0.1;
			overallPanel.add(roundPanel,c);
			c.gridy = 1;
			c.weighty = 0.1;
			overallPanel.add(typeNnumberNtopicPanel, c);
			c.gridy = 2;
			c.weighty = 0.5;
			overallPanel.add(questionPanel, c);
			c.gridy = 3;
			c.weighty = 0.2;
			overallPanel.add(answerPanel, c);
			c.gridy = 4;
			c.weighty = 0.1;
			overallPanel.add(buttonPanel, c);
		add(overallPanel);
		SQLiteReader.selectRound((String)roundDropdown.getSelectedItem());
		updateRound();
		newQuestionUpdate();
	}
	public void newQuestionUpdate(){
		updateQuestionNumber();
		updateType();
		updateTopic();
		updateQuestion();
		hideAnswer();
	}
	public void updateRound(){
		round.setText((String)roundDropdown.getSelectedItem());
	}
	public void updateQuestionNumber(){
		questionNumber.setText(""+SQLiteReader.getQuestionsArray().get(arrayNumber).getQuestionNumber());
	}
	public void updateType(){
		type.setText(SQLiteReader.getQuestionsArray().get(arrayNumber).getType());
	}
	public void updateTopic(){
		topic.setText(SQLiteReader.getQuestionsArray().get(arrayNumber).getTopic());
	}
	public void updateQuestion(){
		String update = SQLiteReader.getQuestionsArray().get(arrayNumber).getQuestion();
		update = update.replaceAll("(\\\\n)","<br>");
		question.setText("<html>"+update+"</html>");
	}
	public void hideAnswer(){
		answer.setText("*~*~*~*~*~*~*");
	}
	public void updateAnswer(){
		answer.setText(SQLiteReader.getQuestionsArray().get(arrayNumber).getAnswer());
	}
	public void incrementQuestionNumber(boolean isIncrease){
		if(isIncrease){
			arrayNumber++;
			if(arrayNumber >= SQLiteReader.getQuestionsArray().size())
				arrayNumber = SQLiteReader.getQuestionsArray().size()-1;
		}
		else{
			arrayNumber--;
			if(arrayNumber < 0)
				arrayNumber = 0;
		}
	}
}