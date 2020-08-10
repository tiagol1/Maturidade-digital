package br.com.snowmanlabs.service;

import java.text.DecimalFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.snowmanlabs.domain.Answers;
import br.com.snowmanlabs.exceptions.ValidateException;
import br.com.snowmanlabs.repository.AnswersRepository;

@Service
public class AnswersSimpleService {
	
	@Autowired
	private AnswersRepository answersRepository;

	@Transactional
	public Answers saveAnswers(Answers answers) throws ValidateException{
		
		List<Answers> answersList = answersRepository.findByClientId(answers.getClient().getId());
		double somaRequirements = 0;
		double somaTechnology = 0;
		
		for (Answers answers2 : answersList) {
			somaRequirements += answers2.getValueRequirements();
			somaTechnology += answers2.getValueTechnology();
		}
		if (somaRequirements < 42.45 || somaTechnology < 42.45) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		if ("conheco totalmente".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() > 24 && answers.getQuestions().getNumberQuestion() <= 28) {
			if (answers.getQuestions().getNumberQuestion() == 28) {
				answers.setValueRequirements(2.5);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}else {
				answers.setValueRequirements(5);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}
		 
		}else if ("conheco".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() > 24 && answers.getQuestions().getNumberQuestion() <= 28) {
			if (answers.getQuestions().getNumberQuestion() == 28) {
				answers.setValueRequirements(1);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}else {
				answers.setValueRequirements(4);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}
			
		}else if ("conheco parcialmente".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() > 24 && answers.getQuestions().getNumberQuestion() <= 28) {
			if (answers.getQuestions().getNumberQuestion() == 28) {
				answers.setValueRequirements(0);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}else {
				answers.setValueRequirements(3);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}
			
		}else if ("conheco pouco".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() > 24 && answers.getQuestions().getNumberQuestion() <= 28) {
			if (answers.getQuestions().getNumberQuestion() == 28) {
				answers.setValueRequirements(-1);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}else {
				answers.setValueRequirements(2); 
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}
			
		}else if ("completamente desconhecido".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() > 24 && answers.getQuestions().getNumberQuestion() <= 28) {
			if (answers.getQuestions().getNumberQuestion() == 28) {
				answers.setValueRequirements(-2.5);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}else {
				answers.setValueRequirements(1); 
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}
			
		}else if ("conheco totalmente".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() > 28 && answers.getQuestions().getNumberQuestion() <= 32) {
			if (answers.getQuestions().getNumberQuestion() == 32) {
				answers.setValueTechnology(2.5);
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}else {
				answers.setValueTechnology(5);
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}
		 
		}else if ("conheco".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() > 28 && answers.getQuestions().getNumberQuestion() <= 32) {
			if (answers.getQuestions().getNumberQuestion() == 32) {
				answers.setValueTechnology(1);
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}else {
				answers.setValueTechnology(4);
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}
					
		}else if ("conheco parcialmente".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() > 28 && answers.getQuestions().getNumberQuestion() <= 32) {
			if (answers.getQuestions().getNumberQuestion() == 32) {
				answers.setValueTechnology(0);
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}else {
				answers.setValueTechnology(3);
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}
			
		}else if ("conheco pouco".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() > 28 && answers.getQuestions().getNumberQuestion() <= 32) {
			if (answers.getQuestions().getNumberQuestion() == 32) {
				answers.setValueTechnology(-1);
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}else {
				answers.setValueTechnology(2); 
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}	
			
		}else if ("completamente desconhecido".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() > 28 && answers.getQuestions().getNumberQuestion() <= 32) {
			if (answers.getQuestions().getNumberQuestion() == 32) {
				answers.setValueTechnology(-2.5);
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}else {
				answers.setValueTechnology(1); 
				answersRepository.save(answers);	
				calculateResultTechnology(answers, answers.getClient().getId());
			}	
		}
		return answers;
   	}
	
	
	private double calculateResultRequirements(Answers answers, Integer id) {
		
		List<Answers> answersList = answersRepository.findByClientId(id);
		double somaRequirements = 0;
		
		for (Answers answers2 : answersList) {
			somaRequirements += answers2.getValueRequirements();
		}
		
		DecimalFormat df = new DecimalFormat("0.00");
    	df.format(somaRequirements);
	    answers.setTotal(somaRequirements);
	    
		 if (answers.getQuestions().getNumberQuestion() == 28) {
			 answers.setProposedSolution("Solução Fim (Requisitos) ..::");
			}
		answers.setQuadrante("Simples");
		return somaRequirements;
	}
	
	private double calculateResultTechnology(Answers answers, Integer id) {
		
		List<Answers> answersList = answersRepository.findByClientId(id);
		double somaTechnology = 0;
		
		for (Answers answers2 : answersList) {
			somaTechnology += answers2.getValueTechnology();
		}
		
		DecimalFormat df = new DecimalFormat("0.00");
    	df.format(somaTechnology);
	    answers.setTotal(somaTechnology);
	    
	    if (answers.getQuestions().getNumberQuestion() == 32) {
	    	answers.setProposedSolution("Solução Fim (Tecnologia) ..::");
		}
	    answers.setQuadrante("Simples");
		return somaTechnology;
	}
	
}



		
		
	