package br.com.adm.clinica.service;

import java.util.List;

import br.com.adm.clinica.model.Usuario;

public interface UsuarioService {
	
	public void salvar(Usuario usuario);
	public void deletar(Long id);
	public void alterar(Usuario usuario);
	public List<Usuario> listar();
	public Usuario buscarPorId(Long id);
	public Usuario logar(String login, String senha);
	public Usuario buscarUsuarioPorLogin(String login);

}
