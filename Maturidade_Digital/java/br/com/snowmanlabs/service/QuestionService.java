package br.com.snowmanlabs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import br.com.snowmanlabs.domain.Questions;
import br.com.snowmanlabs.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	
	@Transactional
	public Questions saveNewQuestion(Questions question){
		if (!validaQuestion(question.getNumberQuestion(), question.getQuestion(),question.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}else {
			return questionRepository.save(question);
		}
	}
	
	private boolean validaQuestion(int numberQuestion, String question,Integer id) {
		Questions questions = questionRepository.findByNumberQuestion(numberQuestion);
		if (questions != null) {
			if (id == null) {
				return false;
			}else if (!questions.getId().equals(id)) {
				return false;
			}else if (!questions.getNumberQuestion().equals(numberQuestion)) {
				return false;
			}
		}
		return true;
	}	
}
