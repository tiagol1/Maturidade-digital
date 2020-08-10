package br.com.snowmanlabs.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tb_client")
public class Client implements Serializable{
	
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	@NotBlank
	@Size(max = 80)
	@Column(nullable = false)
	private String name;

	@NotBlank
	@Size(max = 60)
	@Email(message = "O e-mail é inválido")
	@Column(nullable = false)
	private String email;
		
	@NotBlank
	@Pattern(regexp = "[0-9]{10,11}", message = "O telefone possui formato inválido")
	@Column(length = 11, nullable = false)
	private String telephone;
	
	@NotBlank
	@Size(max = 280, message = "Descrição muito grande!")
	@Column(nullable = false)
	private String demand;	
	
	
}

