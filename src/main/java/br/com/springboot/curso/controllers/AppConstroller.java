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

    @RequestMapping(value = "/mostrarNome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name){
        return "Hello " + name + " !";
    }


    //teste de conexão com o banco, não levar a sério
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String metodoOlaMundo(@PathVariable String nome){
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuarioRepository.save(usuario);
        return "Olá Mundo "+ nome;
    }

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
}
