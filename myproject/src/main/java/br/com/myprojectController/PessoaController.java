package br.com.myprojectController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.myprojectModel.Pessoa;
import br.com.myprojectRepository.PessoaRepository;

@Controller
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository;

	// tela de cadastro
	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public ModelAndView inicio() {

		ModelAndView modelandView = new ModelAndView("cadastro/cadastropessoa");
		modelandView.addObject("pessoaobjeto", new Pessoa());

		return modelandView;
	}

	// salvar no banco de dados
	@PostMapping(value = "/salvarpessoa") // method post valva no banco aquisições html dois asteristicos iguinora tudo antes da url
	public ModelAndView salvar(Pessoa pessoa) {// model ira pegar objeto pessoa e mostrara na tela pro usuario

		pessoaRepository.save(pessoa); // salva
		
		ModelAndView andview = new ModelAndView("cadastro/cadastropessoa");// html onde esta mapeada
		Iterable<Pessoa> pessoaItarable = pessoaRepository.findAll();//
		andview.addObject("pessoas", pessoaItarable);
		andview.addObject("pessoaobjeto", new Pessoa());
		return andview;
	}

//liga modelo de dados com a view pegara informçoes do banco de dados
	@RequestMapping(method = RequestMethod.GET, value = "/listapessoas") // get na lista
	public ModelAndView pessoas() { // função modelandview para adicionar funçoes na tela
		ModelAndView andview = new ModelAndView("cadastro/cadastropessoa");// html onde esta mapeada
		Iterable<Pessoa> pessoaItarable = pessoaRepository.findAll();//
		andview.addObject("pessoas", pessoaItarable);
		andview.addObject("pessoaobjeto", new Pessoa());
		return andview;
	}

	// editar do banco de dados
	@GetMapping("/editarpessoa/{idpessoa}") // variavel com seu parametro do thymelif //usar o get para buscar url com o
											// banco parametro em cochetes
	public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {// editar pessoa banco

		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa); // puxa do objeto pessoa pelo seu id olha sempre
																		// findall que esta sendo usado

		ModelAndView modelandView = new ModelAndView("cadastro/cadastropessoa");
		modelandView.addObject("pessoaobjeto", pessoa.get());// aqui adiciona na tela pra ser estartado pra usuario
	
		
		return modelandView;
	}

	// deletar pessoa do banco de dados
	@GetMapping("/removerpessoa/{idpessoa}") // variavel com seu parametro do thymelif //usar o get para buscar url com
												// o banco parametro em cochetes
	public ModelAndView excluir(@PathVariable("idpessoa") Long idpessoa) {// editar pessoa banco

		pessoaRepository.deleteById(idpessoa); // aqui pegará pessoa do banco é excluirá

		ModelAndView modelandView = new ModelAndView("cadastro/cadastropessoa");
		modelandView.addObject("pessoas", pessoaRepository.findAll());// aqui adiciona na tela pra ser estartado  															usuario
		modelandView.addObject("pessoaobjeto", new Pessoa()); 

		return modelandView;
	}

	@PostMapping(value = "/pesquisarpessoa") // aqui url com parametros do form
	public ModelAndView pesquisar(@RequestParam("/nomepesquisa") String nomepesquisa) {// aqui é o campo que o usuario
																						// digitou

		ModelAndView modelandView = new ModelAndView("cadastro/cadastropessoa");
		modelandView.addObject("pessoas", pessoaRepository.findPessoaByName("nomepesquisa")); // aqui irá consultar a
																							// query feita no
																							// repositorio
		modelandView.addObject("pessoaobjeto", new Pessoa());// aqui voltará pra mesma tela

		return modelandView;
	}

}
