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
	
	
	// Para exibir a mensagem de retorno
	private Boolean status = null;
	private String message = "";
	
	private String answer;
	private Long question;
	
	public void register () {
		
		boolean isValid = true;
	
		if (answers.length() != 50) {
			status = false;
			message = "O gabarito deve conter 50 valores!";
			
			return;
		}
		
		for (int i = 0; i < answers.length(); i++) {
		
			if (Arrays.binarySearch(validsAnswers, answers.charAt(i)) < 0) {
				
				isValid = false;
				break;
			}
		}
		
		if (!isValid) {
			status = false;
			message = "O gabarito deve conter apenas os seguintes valores: A, B, C, D, ou E";
		
		} else {
		
			for (int i = 0; i < answers.length(); i++) {
				answerTest.setAnswer(answers.charAt(i));
				answerTest.setQuestion(i + 1);
				
				dao.insert(answerTest);
				setAnswerTest(new AnswerTest());
			}
			
			status = true;
			message = "Gabarito cadastrado com sucesso!";
			
			answers = "";
		}
	}// register
	
	public void searchBy () {
		if (question > 0 && question <= 50)
			answer = "Resposta: " + dao.searchById(question).getAnswer();
		else answer = "O valor passado é inválido";
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

	public Boolean getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Long getQuestion() {
		return question;
	}

	public void setQuestion(Long question) {
		this.question = question;
	}
}
