package br.com.myprojectRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.myprojectModel.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("select p from Pessoa p where p.nome like %?1%")// faz pesquisa no banco de dados e busca por letras digitada pelo usuario se fosse 2 parametro colocava and %?%
	List<Pessoa>findPessoaByName(String nome);//aqui puxo lista de do objeto
	
	
	
	
	
}
