package com.nnk.springboot.dto;

import javax.validation.constraints.NotBlank;

/**
 * This class groups User related information.
 *
 * @author Yahia CHERIFI
 */

public class UserDTO {

    /**
     * user id.
     */
    private Integer id;

    /**
     * user name.
     */
    @NotBlank(message = "Username is mandatory")
    private String username;

    /**
     * user password.
     */
    @NotBlank(message = "Password is mandatory and must not be black")
    private String password;

    /**
     * user full name.
     */
    @NotBlank(message = "FullName is mandatory")
    private String fullName;

    /**
     * user role.
     */
    @NotBlank(message = "Role is mandatory")
    private String role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
    
}
