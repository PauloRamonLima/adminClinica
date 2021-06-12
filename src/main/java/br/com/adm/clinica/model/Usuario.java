package br.com.adm.clinica.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.adm.clinica.enums.TipoUsuarioEnum;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adm_usuario")
@Getter
@Setter
public class Usuario implements Serializable, UserDetails {

	private static final long serialVersionUID = 7460833738380185408L;
	
	@Id
	@SequenceGenerator(name = "usuario_GENERATION", sequenceName = "usuario_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_GENERATION")
	@Column(name = "usuario_id")
	private Long id;
	
	@Column(name = "usuario_nome")
	private String nome;
	
	@Column(name = "usuario_login")
	private String login;
	
	@Column(name = "usuario_senha")
	private String senha;
	
	@Column(name = "usuario_email")
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "usuario_tipo")
	private TipoUsuarioEnum tipoUsuarioEnum;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
