package br.com.snowmanlabs.controller;

import java.util.List;
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
import br.com.snowmanlabs.domain.Client;
import br.com.snowmanlabs.repository.ClientRepository;
import br.com.snowmanlabs.service.ClientService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/dmd")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientRepository clientRepository;

	@PostMapping(value = "/client")
	@ApiOperation(value = "Cadastra novos clientes.")
	public ResponseEntity<Client> newClient(@RequestBody Client client) {
		try {
			clientService.saveClient(client);
			return ResponseEntity.ok(client);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getClient")
	@ApiOperation(value = "Retorna lista de clientes.")
	public ResponseEntity<List<Client>> findAllClient() {
		return ResponseEntity.ok().body(clientRepository.findAll());
	}

	@GetMapping("/getClientId/{id}")
	@ApiOperation(value = "Retorna cliente por Id.")
	public ResponseEntity<Client> findById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok().body(clientRepository.findById(id).get());
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateClient")
	@ApiOperation(value = "Atualiza dados do cliente.")
	public ResponseEntity<Client> updateClient(@RequestBody Client client) {
		try {
			clientService.updateClient(client);
			return ResponseEntity.ok(client);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Deleta cliente.")
	@DeleteMapping("/deleteClient")
	public ResponseEntity<Client> deleteClient(@RequestBody Client client) {
		try {
			clientRepository.delete(client);
			return ResponseEntity.ok(client);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}

