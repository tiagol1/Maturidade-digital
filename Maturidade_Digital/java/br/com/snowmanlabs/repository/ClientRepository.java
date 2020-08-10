package br.com.snowmanlabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.snowmanlabs.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
	
	Client findByEmail(String email);

}

