package br.com.snowmanlabs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import br.com.snowmanlabs.domain.Client;
import br.com.snowmanlabs.exceptions.ValidateException;
import br.com.snowmanlabs.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional
	public Client saveClient(Client client) throws ValidateException{
		
		if (!validateEmail(client.getEmail(), client.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}else {
			return clientRepository.save(client);
		}
	}
	
	@Transactional
	public Client updateClient(Client client) throws ValidateException{
		
		if (client == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}else {
			return clientRepository.save(client);
		}
	}
	
	private boolean validateEmail(String email, Integer id) {
		
		Client client = clientRepository.findByEmail(email);
		
		if (client != null) {
			if (id == null) {
				return false;
			}
			if (!client.getId().equals(id)) {
				return false;
			}
		}
		return true;
	}	
	
}

