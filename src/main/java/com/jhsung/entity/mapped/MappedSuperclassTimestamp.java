package com.jhsung.entity.mapped;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;

@Getter
@MappedSuperclass
public class MappedSuperclassTimestamp {

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date created;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updated;

	@PrePersist
	public void onPersist() {
		created = new Date();
		updated = created;
	}

	@PreUpdate
	public void onUpdate() {
		updated = new Date();
	}

}
