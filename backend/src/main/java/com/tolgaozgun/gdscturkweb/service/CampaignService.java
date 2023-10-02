package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.request.campaign.CreateCampaignRequest;
import com.tolgaozgun.gdscturkweb.dto.request.campaign.EditCampaignRequest;
import com.tolgaozgun.gdscturkweb.entity.*;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.exception.*;
import com.tolgaozgun.gdscturkweb.mapper.CampaignMapper;
import com.tolgaozgun.gdscturkweb.mapper.CampaignPageMapper;
import com.tolgaozgun.gdscturkweb.model.Campaign;
import com.tolgaozgun.gdscturkweb.model.CampaignPage;
import com.tolgaozgun.gdscturkweb.repository.AttachmentRepository;
import com.tolgaozgun.gdscturkweb.repository.CampaignPageRepository;
import com.tolgaozgun.gdscturkweb.repository.CampaignRepository;
import com.tolgaozgun.gdscturkweb.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final AttachmentRepository attachmentRepository;
    private final CampaignPageRepository campaignPageRepository;
    private final QuestionRepository questionRepository;
    private final CampaignMapper campaignMapper;
    private final CampaignPageMapper campaignPageMapper;

    public List<Campaign> getAllCampaigns() {
        try {
            List<CampaignEntity> topicEntities = campaignRepository.findAll();
            return campaignMapper.toModel(topicEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<Campaign> getCurrentCampaigns() {
        try {
            Date today = new Date();
            List<CampaignEntity> campaignEntities = campaignRepository.findAllByStartDateAfterAndEndDateBefore(today, today);

            return campaignMapper.toModel(campaignEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Campaign getCampaign(Long campaignId) {
        try {
            Optional<CampaignEntity> optionalCampaignEntity = campaignRepository.findById(campaignId);

            if (optionalCampaignEntity.isEmpty()) {
                throw new CampaignNotFoundException(campaignId);
            }

            CampaignEntity campaignEntity = optionalCampaignEntity.get();
            return campaignMapper.toModel(campaignEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public CampaignPage getCampaignPageById(Long campaignPageId) {
        try {
            Optional<CampaignPageEntity> optionalCampaignPageEntity = campaignPageRepository.findById(campaignPageId);

            if (optionalCampaignPageEntity.isEmpty()) {
                throw new CampaignPageNotFoundException(campaignPageId);
            }

            CampaignPageEntity campaignPageEntity = optionalCampaignPageEntity.get();

            return campaignPageMapper.toModel(campaignPageEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<CampaignPage> getCampaignPagesByCampaign(Long campaignId) {
        try {
            Optional<CampaignEntity> optionalCampaignEntity = campaignRepository.findById(campaignId);

            if (optionalCampaignEntity.isEmpty()) {
                throw new CampaignNotFoundException(campaignId);
            }

            CampaignEntity campaignEntity = optionalCampaignEntity.get();

            return campaignPageMapper.toModel(campaignEntity.getCampaignPages());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Campaign createCampaign(CreateCampaignRequest createCampaignRequest) {
        try {

            CampaignEntity campaignEntity = new CampaignEntity();

            String title = createCampaignRequest.getTitle();
            String description = createCampaignRequest.getDescription();
            Date startDate = createCampaignRequest.getStartDate();
            Date endDate = createCampaignRequest.getStartDate();

            campaignEntity.setTitle(title);
            campaignEntity.setDescription(description);
            campaignEntity.setStartDate(startDate);
            campaignEntity.setEndDate(endDate);

            List<AttachmentEntity> attachmentEntities = new ArrayList<>();

            if (createCampaignRequest.getAttachmentIds() != null) {
                List<Long> attachmentIds = createCampaignRequest.getAttachmentIds();


                for (Long attachmentId: attachmentIds) {
                    Optional<AttachmentEntity> optionalAttachmentEntity = attachmentRepository.findById(attachmentId);

                    if (optionalAttachmentEntity.isEmpty()) {
                        throw new AttachmentNotFoundException(attachmentId);
                    }

                    AttachmentEntity attachmentEntity = optionalAttachmentEntity.get();
                    attachmentEntities.add(attachmentEntity);
                }
            }

            campaignEntity.setAttachments(attachmentEntities);
            List<QuestionEntity> questionEntities = new ArrayList<>();


            if (createCampaignRequest.getQuestionIds() != null) {
                List<Long> questionIds = createCampaignRequest.getQuestionIds();

                for (Long questionId: questionIds) {
                    Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(questionId);

                    if (optionalQuestionEntity.isEmpty()) {
                        throw new QuestionNotFoundException(questionId);
                    }

                    QuestionEntity questionEntity = optionalQuestionEntity.get();
                    questionEntities.add(questionEntity);
                }
            }
            campaignEntity.setQuestions(questionEntities);

            List<UserType> userTypes = createCampaignRequest.getPermittedUserTypes();

            if (userTypes == null || userTypes.isEmpty()) {
                userTypes.add(UserType.FACILITATOR);
                userTypes.add(UserType.LEAD);
                userTypes.add(UserType.CORE_TEAM_MEMBER);
                userTypes.add(UserType.GOOGLER);
                userTypes.add(UserType.ADMIN);
            }

            campaignEntity.setPermittedUserTypes(userTypes);

            campaignEntity = campaignRepository.save(campaignEntity);
            return campaignMapper.toModel(campaignEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Campaign editCampaign(Long campaignId, EditCampaignRequest editCampaignRequest) {
        try {
            Optional<CampaignEntity> optionalCampaignEntity = campaignRepository.findById(campaignId);

            if (optionalCampaignEntity.isEmpty()) {
                throw new CampaignNotFoundException(campaignId);
            }

            CampaignEntity campaignEntity = optionalCampaignEntity.get();

            if (editCampaignRequest.getDescription() != null) {
                String description = editCampaignRequest.getDescription();
                campaignEntity.setDescription(description);
            }

            if (editCampaignRequest.getTitle() != null) {
                campaignEntity.setTitle(editCampaignRequest.getTitle());
            }

            if (editCampaignRequest.getStartDate() != null) {
                campaignEntity.setStartDate(editCampaignRequest.getStartDate());
            }

            if (editCampaignRequest.getEndDate() != null) {
                campaignEntity.setEndDate(editCampaignRequest.getEndDate());
            }

            if (editCampaignRequest.getAttachmentIds() != null) {
                List<AttachmentEntity> attachmentEntities = new ArrayList<>();
                List<Long> attachmentIds = editCampaignRequest.getAttachmentIds();


                for (Long attachmentId: attachmentIds) {
                    Optional<AttachmentEntity> optionalAttachmentEntity = attachmentRepository.findById(attachmentId);

                    if (optionalAttachmentEntity.isEmpty()) {
                        throw new AttachmentNotFoundException(attachmentId);
                    }

                    AttachmentEntity attachmentEntity = optionalAttachmentEntity.get();
                    attachmentEntities.add(attachmentEntity);
                }
                campaignEntity.setAttachments(attachmentEntities);
            }


            if (editCampaignRequest.getQuestionIds() != null) {
                List<QuestionEntity> questionEntities = new ArrayList<>();
                List<Long> questionIds = editCampaignRequest.getQuestionIds();

                for (Long questionId: questionIds) {
                    Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(questionId);

                    if (optionalQuestionEntity.isEmpty()) {
                        throw new QuestionNotFoundException(questionId);
                    }

                    QuestionEntity questionEntity = optionalQuestionEntity.get();
                    questionEntities.add(questionEntity);
                }
                campaignEntity.setQuestions(questionEntities);
            }

            if (editCampaignRequest.getPermittedUserTypes() != null && !editCampaignRequest.getPermittedUserTypes().isEmpty()) {
                campaignEntity.setPermittedUserTypes(editCampaignRequest.getPermittedUserTypes());
            }

            return campaignMapper.toModel(campaignRepository.save(campaignEntity));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


}
