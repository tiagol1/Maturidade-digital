package br.com.snowmanlabs.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.snowmanlabs.domain.Questions;
import br.com.snowmanlabs.repository.QuestionRepository;
import br.com.snowmanlabs.service.QuestionService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/dmd")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuestionRepository questionRepository; 

	@ApiOperation(value = "Cadastrar pergunta.")
	@PostMapping(value = "/question")
	public ResponseEntity<Questions> newQuestion(@Valid @RequestBody Questions question){
		try {
			questionService.saveNewQuestion(question);
			return ResponseEntity.ok(question);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Retorna lista de perguntas.")
	@GetMapping(value = "/getQuestions")
	public ResponseEntity<List<Questions>> findAllQuestion() {
		return ResponseEntity.ok().body(questionRepository.findAll());
	}
	
	@ApiOperation(value = "Retorna pergunta por Id.")
	@GetMapping("/getQuestionId/{id}")
	public ResponseEntity<Questions> findById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok().body(questionRepository.findById(id).get());
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value="Atualiza pergunta.")
	@PutMapping("/updateQuestion")
	public ResponseEntity<Questions> updateQuestion(@RequestBody Questions question) {
		questionService.saveNewQuestion(question);
		return ResponseEntity.ok(question);
	}
	
	@ApiOperation(value="Deleta pergunta.")
	@DeleteMapping("/deleteQuestion")
	public ResponseEntity<Questions> deleteQuestion(@RequestBody Questions question) {
		try {
			questionRepository.delete(question);
			return ResponseEntity.ok(question);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}

