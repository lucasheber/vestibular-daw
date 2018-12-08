package lhos.jompscity.vestibular.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="answers")
public class AnswerTest {

	@Id
	@GeneratedValue(generator="answer_id", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="answer_id", sequenceName="answer_seq", allocationSize=1)
	private Long id;
	
	private Integer question;
	private Character answer;
	
	public AnswerTest() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuestion() {
		return question;
	}

	public void setQuestion(Integer question) {
		this.question = question;
	}

	public Character getAnswer() {
		return answer;
	}

	public void setAnswer(Character answer) {
		this.answer = answer;
	}
}
