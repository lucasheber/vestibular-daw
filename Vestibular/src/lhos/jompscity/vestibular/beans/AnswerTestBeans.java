package lhos.jompscity.vestibular.beans;

import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lhos.jompscity.vestibular.dao.DAO;
import lhos.jompscity.vestibular.model.AnswerTest;

@ViewScoped
@ManagedBean
public class AnswerTestBeans {
 
	// Armazena o gabarito 
	private String answers = "";
	private AnswerTest answerTest = new AnswerTest();
	private DAO<AnswerTest> dao = new DAO<>(AnswerTest.class);
	private Character[] validsAnswers = {'A', 'B', 'C', 'D', 'E'};
	
	public void register () {
		
		boolean isValid = true;
		
		for (int i = 0; i < answers.length(); i++) {
			
			System.out.println(Arrays.binarySearch(validsAnswers, answers.charAt(i)));
			if (Arrays.binarySearch(validsAnswers, answers.charAt(i)) < 0) {
				
				isValid = false;
				break;
			}
		}
		
		if (!isValid) return;
		
		System.out.println("## passou ##");
		
		for (int i = 0; i < answers.length(); i++) {
			
			
			answerTest.setAnswer(answers.charAt(i));
			answerTest.setQuestion(i + 1);
			
			dao.insert(answerTest);
			setAnswerTest(new AnswerTest());
		}
		
		answers = "";
	}
	
	public String getAnswers() {
		return answers;
	}
	
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	
	public AnswerTest getAnswerTest() {
		return answerTest;
	}
	
	public void setAnswerTest(AnswerTest answerTest) {
		this.answerTest = answerTest;
	}
}
