package br.com.springboot.curso.repository;

import br.com.springboot.curso.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
