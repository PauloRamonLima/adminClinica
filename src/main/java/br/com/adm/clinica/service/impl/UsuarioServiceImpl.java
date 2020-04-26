package br.com.adm.clinica.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.adm.clinica.dao.UsuarioDAO;
import br.com.adm.clinica.model.Usuario;
import br.com.adm.clinica.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService, Serializable {

	private static final long serialVersionUID = 5197989185645187917L;
	
	@Inject
	private UsuarioDAO usuarioDAO;

	@Override
	public void salvar(Usuario usuario) {
		usuarioDAO.save(usuario);
	}

	@Override
	public void deletar(Long id) {
		usuarioDAO.delete(id);
	}

	@Override
	public void alterar(Usuario usuario) {
		usuarioDAO.update(usuario);
	}

	@Override
	public List<Usuario> listar() {
		return usuarioDAO.findAll();
	}

	@Override
	public Usuario buscarPorId(Long id) {
		return usuarioDAO.findById(id);
	}

	@Override
	public Usuario logar(String login, String senha) {
		return usuarioDAO.logar(login, senha);
	}

	@Override
	public Usuario buscarUsuarioPorLogin(String login) {
		return usuarioDAO.buscarUsuarioPorLogin(login);
	}

}
