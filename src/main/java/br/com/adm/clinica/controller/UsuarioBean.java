package br.com.adm.clinica.controller;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import br.com.adm.clinica.model.Usuario;
import br.com.adm.clinica.service.UsuarioService;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
@Getter
@Setter
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = -4326028160868302820L;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private Usuario usuario;
	
	@Inject
	private Usuario usuarioLogado;

	private String login;

	private String senha;

	private String repitaSenha;

	public void salvar() {
		try {
			Usuario user = usuarioService.buscarUsuarioPorLogin(usuario.getLogin());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Login Em Uso, Por Favor Alterar", "Login Em Uso, Por Favor Alterar"));
			return;
		} catch (NoResultException e) {
			usuario.setSenha(convertStringToMd5(senha));
			usuarioService.salvar(usuario);
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Cadastrado", "Usuario Cadastrado"));
		}
	}

	public void alterarSenha() {
		try {
			Usuario user = usuarioService.buscarUsuarioPorLogin(login);
			if (senha.equals(repitaSenha)) {
				user.setSenha(convertStringToMd5(senha));
				usuarioService.alterar(user);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha Atualizada", "Senha Atualizada"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas Diferentes", "Senhas Diferentes"));
			}
		} catch (NoResultException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario N�o Encontrado", "Usuario N�o Encontrado"));
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
			usuarioLogado = usuarioService.logar(login, convertStringToMd5(senha));
			if (usuarioLogado != null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().getSessionMap().put("usuarioLogado", usuarioLogado);
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
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login.xhtml?faces-redirect=true";
	}

}
