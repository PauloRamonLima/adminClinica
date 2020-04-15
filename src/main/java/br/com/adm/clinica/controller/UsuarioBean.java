package br.com.adm.clinica.controller;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.persistence.NoResultException;

import com.sun.istack.internal.logging.Logger;

import br.com.adm.clinica.config.SessionContext;
import br.com.adm.clinica.dao.UsuarioDAO;
import br.com.adm.clinica.model.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = -4326028160868302820L;

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	private Usuario usuario = new Usuario();

	private String login;

	private String senha;

	private String repitaSenha;

	public void salvar() {
		try {
		Usuario user = usuarioDAO.buscarUsuarioPorLogin(usuario.getLogin());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Login Em Uso, Por Favor Alterar", "Login Em Uso, Por Favor Alterar"));
		return;
		}catch (NoResultException e) {
		usuario.setSenha(convertStringToMd5(senha));
		usuarioDAO.save(usuario);
		usuario = new Usuario();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Usuario Cadastrado", "Usuario Cadastrado"));
	}
	}
	
	public void alterarSenha() {
		try {
			Usuario user = usuarioDAO.buscarUsuarioPorLogin(login);
			if(senha.equals(repitaSenha)) {
				user.setSenha(convertStringToMd5(senha));
				usuarioDAO.update(user);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Senha Atualizada", "Senha Atualizada"));
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Senhas Diferentes", "Senhas Diferentes"));
			}
		}catch (NoResultException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Usuario Não Encontrado", "Usuario Não Encontrado"));
			return;
		}
	}

	private String convertStringToMd5(String valor) {
		MessageDigest mDigest;
		try {

			mDigest = MessageDigest.getInstance("MD5");

			// Convert a String valor para um array de bytes em MD5
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}

			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void showIndexPage() throws IOException {
		try {
			if (usuarioDAO.logar(login, convertStringToMd5(senha)) != null) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?faces-redirect=true");
			}
		} catch (NoResultException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Login Ou Senha Incorreta", "Login Ou Senha Incorreto"));
		}
	}

	public void showCadastroPage() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext().redirect("novousuario.xhtml?faces-redirect=true");
	}

	public void showAlterarSenhaPage() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext().redirect("alterarsenha.xhtml?faces-redirect=true");
	}

	public void showLoginPage() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml?faces-redirect=true");
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getRepitaSenha() {
		return repitaSenha;
	}

	public void setRepitaSenha(String repitaSenha) {
		this.repitaSenha = repitaSenha;
	}

}
