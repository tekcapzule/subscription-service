package com.tekcapsule.subscription.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.Command;
import com.tekcapsule.subscription.domain.model.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class SubscribeCommand extends Command {
    private Name name;
    private Gender gender;
    private String tenantId;
    private String headLine;
    private Contact contact;
    private String photoUrl;
    private DateOfBirth dateOfBirth;
    private List<String> tags;
    List<ProfessionalExperience> professionalExperiences;
    List<EducationalQualification> educationalQualifications;
    List<Award> awards;
    List<Certification> certifications;
    List<Publication> publications;
    private Social social;
}
