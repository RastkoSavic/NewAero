package com.aerobase.app.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class CompanySeekingId implements Serializable {

	private CompanySettings settings;

	private Part part;

	public CompanySeekingId() {
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	public CompanySettings getSettings() {
		return settings;
	}

	public void setSettings(CompanySettings settings) {
		this.settings = settings;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	@Override
	public int hashCode() {
		int hash = 5;

		hash = 53 * hash + Objects.hashCode(this.settings);
		hash = 53 * hash + Objects.hashCode(this.part);

		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final CompanySeekingId other = (CompanySeekingId) obj;

		if (!Objects.equals(this.settings, other.settings)) {
			return false;
		}

		if (!Objects.equals(this.part, other.part)) {
			return false;
		}

		return true;
	}
}
