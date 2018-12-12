package lhos.jompscity.vestibular.beans;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import lhos.jompscity.vestibular.dao.DAO;
import lhos.jompscity.vestibular.model.AnswerTest;
import lhos.jompscity.vestibular.model.Candidate;
import lhos.jompscity.vestibular.model.Result;

@ViewScoped
@ManagedBean
public class ResultBeans {

	private UploadedFile uploadedFile;
	private DAO<Candidate> daoCandidate = new DAO<>(Candidate.class);
	private DAO<AnswerTest> daoAnswer = new DAO<>(AnswerTest.class);
	private DAO<Result> dao = new DAO<>(Result.class);
	
	private Boolean status = null;
	private String message = "";

	public void handleFileUpload(FileUploadEvent event) {
		System.out.println("## Entrou");
		
		if(event.getFile() != null) {
			status = true;
			message = "Arquivo importado com sucesso!";
		} else {
			status = false;
			message = "Arquivo não foi importado!";
		}
			
	}
	
	private List<Candidate> parseFile(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream);
		
		List<String> linhasDoArquivo = new ArrayList<String>();
		List<Candidate> candidates = new ArrayList<>();
		
		while (scanner.hasNext()) {
			linhasDoArquivo.add(scanner.nextLine());
		}
		
		
		for (String string : linhasDoArquivo) {
			Long id = Long.parseLong((string.substring(4, 8)));
			Candidate candidate = daoCandidate.searchById(id);
			
			Result result = new Result();
			result.setHits(totalHits(string.substring(9)));
			result.setCandidate(candidate);
			candidate.setResult(result);
			
			daoCandidate.update(candidate);
		}
		
		return candidates;
	}
	
	private int totalHits (String answers) {
		int hits = 0;
		
		List<AnswerTest> answerTests = daoAnswer.list();
		
		for (int i = 0; i < answers.length(); i++) {
			if (answers.charAt(i) == answerTests.get(i).getAnswer().charValue())
				hits++;
		}
		
		return hits;
	}
	
	public void correct () {
		
		if (uploadedFile != null)
			try {
				parseFile(uploadedFile.getInputstream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		else 
			System.out.println("Arquivo nullo!!");
	}
	
	public Boolean getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}
}
