package br.com.javaSpringBootCadastroRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.javaSpringBootCadastroModel.UsuarioModelo;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModelo, Long> {

	@Query(value = "select u From UsuarioModelo u where upper(trim(u.nome)) like %?1%")// trim tira os espaço que vem do banco de dados
	List<UsuarioModelo>buscarpornome(String nome);
}
