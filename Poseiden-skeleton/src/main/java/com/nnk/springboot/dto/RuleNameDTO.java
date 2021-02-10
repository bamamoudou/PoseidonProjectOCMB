package com.nnk.springboot.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

public class RuleNameDTO {

    /**
     * RuleName id.
     */
    private Integer id;

    /**
     * name.
     */
    @NonNull
    @NotBlank(message = "Name is mandatory")
    private String name;

    /**
     * description.
     */
    @NonNull
    @NotBlank(message = "Description is mandatory")
    private String description;

    /**
     * json.
     */
    @NonNull
    @NotBlank(message = "Json is mandatory")
    private String json;

    /**
     * template.
     */
    @NonNull
    @NotBlank(message = "Template is mandatory")
    private String template;

    /**
     * sqlStr.
     */
    @NonNull
    @NotBlank(message = "SqlStr is mandatory")
    private String sqlStr;

    /**
     * sqlPart.
     */
    @NonNull
    @NotBlank(message = "SqlPart is mandatory")
    private String sqlPart;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public String getSqlPart() {
		return sqlPart;
	}

	public void setSqlPart(String sqlPart) {
		this.sqlPart = sqlPart;
	}
    
    
}
