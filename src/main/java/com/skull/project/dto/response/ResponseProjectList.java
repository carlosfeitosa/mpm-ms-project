package com.skull.project.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class for project list response (embedded).
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-08-05
 *
 */
public class ResponseProjectList {

	@JsonProperty("_embedded")
	private EmbeddedProjectList embedded;

	public EmbeddedProjectList getEmbedded() {
		return embedded;
	}

	public void setEmbedded(EmbeddedProjectList embedded) {
		this.embedded = embedded;
	}
}
