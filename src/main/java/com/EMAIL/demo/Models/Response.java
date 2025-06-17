package com.EMAIL.demo.Models;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "Registrations")
public class Response {
    @Id
    @Column(name = "Id")
    private UUID id;

    private String ResponseStatus;

    @Column(name = "FormId")
    private String formId;

    @Column(name = "UserId")
    private String userId;

    @Column(name = "RelatedTo")
    private String relatedTo;

    @Column(name = "RelatedToId")
    private String relatedToId;

    @Column(name = "Amount")
    private float amount;

    @Column(name = "OrganisationId")
    private UUID organisationId;

    @Column(name = "TeamId")
    private UUID teamId;

    @Column(name = "ResponseData")
    private String responseData;

    @Column(name = "CreatedAt")
    private OffsetDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setResponseStatus(String responseStatus) {
        ResponseStatus = responseStatus;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRelatedTo(String relatedTo) {
        this.relatedTo = relatedTo;
    }

    public void setRelatedToId(String relatedToId) {
        this.relatedToId = relatedToId;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setOrganisationId(UUID organisationId) {
        this.organisationId = organisationId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getResponseStatus() {
        return ResponseStatus;
    }

    public String getFormId() {
        return formId;
    }

    public String getUserId() {
        return userId;
    }

    public String getRelatedTo() {
        return relatedTo;
    }

    public String getRelatedToId() {
        return relatedToId;
    }

    public float getAmount() {
        return amount;
    }

    public UUID getOrganisationId() {
        return organisationId;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public String getResponseData() {
        return responseData;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
