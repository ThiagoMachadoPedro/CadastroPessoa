package br.com.javaSpringBootCadastro.controllers;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.javaSpringBootCadastroModel.UsuarioModelo;
import br.com.javaSpringBootCadastroRepository.UsuarioRepository;


@RestController
public class GreetingsController {

	@Autowired /* IC/CD ou CDI - Injeção de dependencia */
	private UsuarioRepository usuarioRepository;
	
	
	@RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Curso  Spring Boot API: " + name + "!";
	}

	@RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String retornaOlaMundo(@PathVariable String nome) {

		UsuarioModelo usuario = new UsuarioModelo();
		usuario.setNome(nome);

		usuarioRepository.save(usuario);/* grava no banco de dados */

		return "Ola mundo " + nome;
	}

	@GetMapping(value = "listatodos") /* Nosso primeiro método de API */
	@ResponseBody /* Retorna os dados par ao corpo da resposta */
	public ResponseEntity<List<UsuarioModelo>> listaUsuario() {

		List<UsuarioModelo> usuarios = usuarioRepository.findAll();/* executa a consulta no banco de dados */

		return new ResponseEntity<List<UsuarioModelo>>(usuarios, HttpStatus.OK);/* Retorna a lista em JSON */

	}

	@PostMapping(value = "salvar") /* mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<UsuarioModelo> salvar(@RequestBody UsuarioModelo usuario) { /* Recebe os dados para salvar */

		UsuarioModelo user = usuarioRepository.save(usuario);

		return new ResponseEntity<UsuarioModelo>(user, HttpStatus.CREATED);

	}
	
	
	@PutMapping(value = "atualizar") /* mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<?> atualizar(@RequestBody UsuarioModelo usuario) { /* Recebe os dados para salvar */
		
		if (usuario.getId() == null) {
			return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.OK);
		}

		UsuarioModelo user = usuarioRepository.saveAndFlush(usuario);

		return new ResponseEntity<UsuarioModelo>(user, HttpStatus.OK);

	}
	
	
	
	@DeleteMapping(value = "delete") /* mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<String> delete(@RequestParam Long iduser) { /* Recebe os dados para delete */

		usuarioRepository.deleteById(iduser);

		return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);

	}
	
	
	
	@GetMapping(value = "buscaruserid") /* mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<UsuarioModelo> buscaruserid(@RequestParam(name = "iduser") Long iduser) { /* Recebe os dados para consultar */

		UsuarioModelo usuario = usuarioRepository.findById(iduser).get();

		return new ResponseEntity<UsuarioModelo>(usuario, HttpStatus.OK);

	}
	
	
	
	
	@GetMapping(value = "buscarPorNome") /* mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<List<UsuarioModelo>> buscarPorNome(@RequestParam(name = "name") String name) { /* Recebe os dados para consultar */

		List<UsuarioModelo> usuario = usuarioRepository.buscarpornome(name.trim().toUpperCase());

		return new ResponseEntity<List<UsuarioModelo>>(usuario, HttpStatus.OK);

	}
	
	
	
		

}
