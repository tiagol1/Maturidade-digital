package br.com.snowmanlabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.snowmanlabs.domain.Questions;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Integer>{
	
	public Questions findByNumberQuestion(int numberQuestion); 
	
}
