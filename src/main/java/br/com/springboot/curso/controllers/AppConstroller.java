package br.com.springboot.curso.controllers;

import br.com.springboot.curso.model.Usuario;
import br.com.springboot.curso.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppConstroller {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/listarTodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listarUsuario(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
    }

    @PostMapping("/salvar")
    @ResponseBody
    public ResponseEntity<Usuario>salvarUsuario(@RequestBody Usuario usuario){
        Usuario user = usuarioRepository.save(usuario);
        return new ResponseEntity<Usuario>(user,HttpStatus.CREATED);
    }

    @DeleteMapping("/deletar")
    @ResponseBody
    public ResponseEntity<String> deletarUsuario (@RequestParam Long id){
        usuarioRepository.deleteById(id);
        return new ResponseEntity<String>("Usuário deletado com sucesso!", HttpStatus.OK);
    }

    @GetMapping("/buscarId")
    @ResponseBody
    public ResponseEntity<Usuario> buscarUsuarioId (@RequestParam(name = "id") Long id){
        Usuario usuario = usuarioRepository.findById(id).get();
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @PutMapping("/atualizar")
    @ResponseBody
    public ResponseEntity<?>atualizarUsuario(@RequestBody Usuario usuario){

        if (usuario.getId() == null){
            return new ResponseEntity<String>("Erro, não foi possível localizar o ID. Tente novamente",HttpStatus.BAD_REQUEST);
        }
        Usuario user = usuarioRepository.saveAndFlush(usuario);
        return new ResponseEntity<Usuario>(user,HttpStatus.OK);
    }

    @GetMapping("/buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome (@RequestParam(name = "name") String name){
        List<Usuario> usuario = usuarioRepository.buscarPorNome(name);
        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
}
