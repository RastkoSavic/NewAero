package com.aerobase.app.entity;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "company_seeking")
@AssociationOverrides({
		@AssociationOverride(name = "pk.settings", joinColumns = @JoinColumn(name = "id_company_setting")),
		@AssociationOverride(name = "pk.part", joinColumns = @JoinColumn(name = "part_number")) })
public class CompanySeeking {

	private CompanySeekingId pk = new CompanySeekingId();

	private String type; // max 4

	private Date created;

	private Date updated;

	public CompanySeeking() {
	}

	@EmbeddedId
	public CompanySeekingId getPk() {
		return pk;
	}

	public void setPk(CompanySeekingId pk) {
		this.pk = pk;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Transient
	public CompanySettings getSettings() {
		return getPk().getSettings();
	}

	public void setSettings(CompanySettings settings) {
		getPk().setSettings(settings);
	}

	@Transient
	public Part getPart() {
		return getPk().getPart();
	}

	public void setPart(Part part) {
		getPk().setPart(part);
	}

	@Override
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
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

		final CompanySeeking other = (CompanySeeking) obj;

		if (getPk() != null ? !getPk().equals(other.getPk()) : other.getPk() != null) {
			return false;
		}

		return true;
	}
}
