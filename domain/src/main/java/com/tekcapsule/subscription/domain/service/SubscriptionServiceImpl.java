package com.tekcapsule.subscription.domain.service;

import com.tekcapsule.subscription.domain.command.UnsubscribeCommand;
import com.tekcapsule.subscription.domain.model.Subscription;
import com.tekcapsule.subscription.domain.query.SearchItem;
import com.tekcapsule.subscription.domain.query.SearchQuery;
import com.tekcapsule.subscription.domain.repository.SubscriptionDynamoRepository;
import com.tekcapsule.subscription.domain.command.SubscribeCommand;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionDynamoRepository mentorRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionDynamoRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    @Override
    public Subscription create(SubscribeCommand subscribeCommand) {

        log.info(String.format("Entering create mentor service - Tenant Id:{0}, Name:{1}", subscribeCommand.getTenantId(), subscribeCommand.getName().toString()));

        Name name = subscribeCommand.getName();
        if (name != null) {
            name.setDisplayName(String.format("{0} {1}", name.getFirstName(), name.getLastName()));
        }
        DateOfBirth dateOfBirth = subscribeCommand.getDateOfBirth();
        if (dateOfBirth != null) {
            dateOfBirth.setDateOfBirth(String.format("{0}/{1}/{2}", dateOfBirth.getDay(), dateOfBirth.getMonth(), dateOfBirth.getYear()));
        }
        Subscription subscription = Subscription.builder()
                .active(true)
                .activeSince(DateTime.now(DateTimeZone.UTC).toString())
                .blocked(false)
                .awards(subscribeCommand.getAwards())
                .certifications(subscribeCommand.getCertifications())
                .contact(subscribeCommand.getContact())
                .dateOfBirth(dateOfBirth)
                .educationalQualifications(subscribeCommand.getEducationalQualifications())
                .headLine(subscribeCommand.getHeadLine())
                .name(name)
                .professionalExperiences(subscribeCommand.getProfessionalExperiences())
                .publications(subscribeCommand.getPublications())
                .social(subscribeCommand.getSocial())
                .tags(subscribeCommand.getTags())
                .tenantId(subscribeCommand.getTenantId())
                .userId(subscribeCommand.getContact().getEmailId())
                .build();

        subscription.setAddedOn(subscribeCommand.getExecOn());
        subscription.setUpdatedOn(subscribeCommand.getExecOn());
        subscription.setAddedBy(subscribeCommand.getExecBy().getUserId());

        return mentorRepository.save(subscription);
    }

    @Override
    public void disable(UnsubscribeCommand unsubscribeCommand) {

        log.info(String.format("Entering disable mentor service - Tenant Id:{0}, User Id:{1}", disableCommand.getTenantId(), disableCommand.getUserId()));

        mentorRepository.disableById(disableCommand.getTenantId(), disableCommand.getUserId());
    }

    @Override
    public List<SearchItem> search(SearchQuery searchQuery) {

        log.info(String.format("Entering search mentor service - Tenant Id:{0}", searchQuery.getTenantId()));

        return mentorRepository.search(searchQuery.getTenantId());
    }

    @Override
    public Subscription get(String tenantId, String userId) {

        log.info(String.format("Entering get mentor service - Tenant Id:{0}, User Id:{1}", tenantId, userId));

        return mentorRepository.findBy(tenantId, userId);
    }
}
