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
public class AnswersCaosService {
	
	@Autowired
	private AnswersRepository answersRepository;

	@Transactional
	public Answers saveAnswers(Answers answers) throws ValidateException{
		
		if (!validaQuestion(answers.getQuestions().getNumberQuestion(), answers.getClient().getId(), answers.getQuestions().getId())) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		
		if ("conheco totalmente".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() <= 4) {
			if (answers.getQuestions().getNumberQuestion() == 4) {
				answers.setValueRequirements(2.5);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}else {
				answers.setValueRequirements(5);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}
		 
		}else if ("conheco".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() <= 4) {
			if (answers.getQuestions().getNumberQuestion() == 4) {
				answers.setValueRequirements(1);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}else {
				answers.setValueRequirements(4);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}
			
		}else if ("conheco parcialmente".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() <= 4) {
			if (answers.getQuestions().getNumberQuestion() == 4) {
				answers.setValueRequirements(0);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}else {
				answers.setValueRequirements(3);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}
			
		}else if ("conheco pouco".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() <= 4) {
			if (answers.getQuestions().getNumberQuestion() == 4) {
				answers.setValueRequirements(-1);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}else {
				answers.setValueRequirements(2); 
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}
			
		}else if ("completamente desconhecido".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() <= 4) {
			if (answers.getQuestions().getNumberQuestion() == 4) {
				answers.setValueRequirements(-2.5);
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}else {
				answers.setValueRequirements(1); 
				answersRepository.save(answers);
				calculateResultRequirements(answers, answers.getClient().getId());
			}
			
		}else if ("conheco totalmente".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() > 3 && answers.getQuestions().getNumberQuestion() <= 8) {
			if (answers.getQuestions().getNumberQuestion() == 8) {
				answers.setValueTechnology(2.5);
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}else {
				answers.setValueTechnology(5);
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}
		 
		}else if ("conheco".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() > 3 && answers.getQuestions().getNumberQuestion() <= 8) {
			if (answers.getQuestions().getNumberQuestion() == 8) {
				answers.setValueTechnology(1);
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}else {
				answers.setValueTechnology(4);
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}
					
		}else if ("conheco parcialmente".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() > 3 && answers.getQuestions().getNumberQuestion() <= 8) {
			if (answers.getQuestions().getNumberQuestion() == 8) {
				answers.setValueTechnology(0);
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}else {
				answers.setValueTechnology(3);
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}
			
		}else if ("conheco pouco".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() > 3 && answers.getQuestions().getNumberQuestion() <= 8) {
			if (answers.getQuestions().getNumberQuestion() == 8) {
				answers.setValueTechnology(-1);
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}else {
				answers.setValueTechnology(2); 
				answersRepository.save(answers);
				calculateResultTechnology(answers, answers.getClient().getId());
			}	
			
		}else if ("completamente desconhecido".equals(answers.getOptions()) && answers.getQuestions().getNumberQuestion() > 3 && answers.getQuestions().getNumberQuestion() <= 8) {
			if (answers.getQuestions().getNumberQuestion() == 8) {
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
	    answers.setQuadrante("Caos");
	    
		 if (answers.getQuestions().getNumberQuestion() == 4 && somaRequirements < 11.25) {
		    	answers.setProposedSolution("Solução proposta para (Requisitos) ..::");
		    	 answers.setQuadrante("Caos");
			}
		return somaRequirements;
	}
	
	private double calculateResultTechnology(Answers answers, Integer id) {
		
		List<Answers> answersList = answersRepository.findByClientId(id);
		double somaTechnology = 0;
		
		for (Answers answers2 : answersList) {
			somaTechnology += answers2.getValueTechnology();
		}
		DecimalFormat formatter = new DecimalFormat("0.00");
		formatter.format(somaTechnology);
	    answers.setTotal(somaTechnology);
	    answers.setQuadrante("Caos");
	    
	    if (answers.getQuestions().getNumberQuestion() == 8 && somaTechnology < 11.25) {
	    	answers.setProposedSolution("Solução proposta para (Tecnologia) ..::");
		}
	    answers.setQuadrante("Caos");
		return somaTechnology;
	}
	
	private boolean validaQuestion(int numberQuestion,Integer id_client, Integer id_question) {
		Answers answers = answersRepository.findByQuestionsId((id_question));
		if (answers != null) {
			if (id_question == null) {
				return false;
			} else if (answers.getClient().getId().equals(id_client)) {
				return false;
			} else if (answers.getQuestions().getNumberQuestion().equals(numberQuestion) && answers.getClient().getId().equals(id_client)) {
				return false;
			}
		}
		return true;
	}
}



		
		
	