package br.com.snowmanlabs.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.snowmanlabs.domain.Answers;
import br.com.snowmanlabs.repository.AnswersRepository;
import br.com.snowmanlabs.service.AnswersCaosService;
import br.com.snowmanlabs.service.AnswersComplexService;
import br.com.snowmanlabs.service.AnswersComplicatedService;
import br.com.snowmanlabs.service.AnswersSimpleService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/dmd")
public class AnswersController {
	
	@Autowired
	private AnswersCaosService answersCaosService;
	
	@Autowired
	private AnswersComplexService answersComplexService;
	
	@Autowired
	private AnswersComplicatedService answersComplicatedService;
	
	@Autowired
	private AnswersSimpleService answersSimpleService;
	
	@Autowired
	private AnswersRepository answersRepository;
	
	@ApiOperation(value = "Resposta quadrante Caos.")
	@PostMapping(value = "/answersCaos")
	public ResponseEntity<Answers> newAnswers(@RequestBody Answers answers) {
		try {
			answersCaosService.saveAnswers(answers);
			return ResponseEntity.ok(answers);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
    	}
	
	@ApiOperation(value = "Resposta quadrante complexo.")
	@PostMapping(value = "/answersComplex")
	public ResponseEntity<Answers> newAnswersComplex(@RequestBody Answers answers) {
		try {
			answersComplexService.saveAnswers(answers);
			return ResponseEntity.ok(answers);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Resposta quadrante complicado.")
	@PostMapping(value = "/answersComplicated")
	public ResponseEntity<Answers> newAnswersComplicated(@RequestBody Answers answers) {
		try {
			answersComplicatedService.saveAnswers(answers);
			return ResponseEntity.ok(answers);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Resposta quadrante simples.")
	@PostMapping(value = "/answersSimple")
	public ResponseEntity<Answers> newAnswersSimple(@RequestBody Answers answers) {
		try {
			answersSimpleService.saveAnswers(answers);
			return ResponseEntity.ok(answers);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Retorna as respostas do cliente pelo seu Id.")
	@GetMapping("/getAnswersClientId/{id}")
	public ResponseEntity<List<Answers>> findByIdClient(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok().body(answersRepository.findByClientId(id));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
			
	@ApiOperation(value = "Retorna lista de todas as respostas.")
	@GetMapping(value = "/getAnswers")
	public ResponseEntity<List<Answers>> getAnswers() {
		return ResponseEntity.ok().body(answersRepository.findAll());
	}
	
	@ApiOperation(value = "Retorna resposta por Id.")
	@GetMapping("/getAnswersId/{id}")
	public ResponseEntity<Answers> findById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok().body(answersRepository.findById(id).get());
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}

 