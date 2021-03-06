package fr.greta91.cda.agriotes.services;

import fr.greta91.cda.agriotes.dtos.UserDTO;

public class JWTResult {
	
	private String login;
	private UserDTO userDTO;
	private boolean ok;
	
	private JWTResult(String login, UserDTO userDTO, boolean ok) {
		this.login = login;
		this.userDTO = userDTO;
		this.ok = ok;
	}

	public String getLogin() {
		return login;
	}
	
	public UserDTO getUserDTO() {
		return userDTO;
	}

	public boolean isOk() {
		return ok;
	}
	
	public boolean isFormateur() {
		return userDTO.getRoles().contains("ROLE_FORMATEUR");
	}
	
	public boolean isEtudiant() {
		return userDTO.getRoles().contains("ROLE_ETUDIANT");
	}
	
	public static JWTResult buildFail() {
		return new JWTResult(null, null, false);
	}
	
	public static JWTResult buildInfo(String login, UserDTO userDTO) {
		return new JWTResult(login, userDTO, true);
	}

}
