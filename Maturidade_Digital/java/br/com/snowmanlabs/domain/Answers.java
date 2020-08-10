package br.com.snowmanlabs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tb_answers")
public class Answers implements Serializable{

	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
		
	@NotBlank(message = "A resposta não pode ser vazia!")
	@Column(nullable = false)
	private String options;
		
	@NotNull
	@ManyToOne
	private Client client;
		
	@ManyToOne
	private Questions questions;
	
	private double valueTechnology;
	
	private double valueRequirements;
	
	private double total;
	
	private String quadrante;
	
	private String proposedSolution;
	
}
