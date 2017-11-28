package SQLite;

public class SQLiteItem {
	private String type, topic, question, answer;
	private int qNumber;
	public SQLiteItem(int qnum, String ty, String to, String q, String a){
		qNumber = qnum;
		type = ty;
		topic = to;
		question = q;
		answer = a;
	}
	public void setQuestionNumber(int qnum){
		qNumber = qnum;
	}
	public void setType(String ty){
		type = ty;
	}
	public void setTopic(String to){
		topic = to;
	}
	public void setQuestion(String q){
		question = q;
	}
	public void setAnswer(String a){
		answer = a;
	}
	public int getQuestionNumber(){
		return qNumber;
	}
	public String getType(){
		return type;
	}
	public String getTopic(){
		return topic;
	}
	public String getQuestion(){
		return question;
	}
	public String getAnswer(){
		return answer;
	}
}
