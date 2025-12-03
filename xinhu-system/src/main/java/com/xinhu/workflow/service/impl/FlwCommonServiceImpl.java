package com.xinhu.workflow.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xinhu.common.core.domain.dto.UserDTO;
import com.xinhu.common.sse.dto.SseMessageDto;
import com.xinhu.common.sse.utils.SseMessageUtils;
import com.xinhu.common.utils.StreamUtils;
import com.xinhu.common.utils.StringUtils;
import com.xinhu.common.utils.email.MailUtils;
import com.xinhu.common.utils.spring.SpringUtils;
import com.xinhu.workflow.common.ConditionalOnEnable;
import com.xinhu.workflow.common.enums.MessageTypeEnum;
import com.xinhu.workflow.service.IFlwCommonService;
import com.xinhu.workflow.service.IFlwTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.warm.flow.core.FlowEngine;
import org.dromara.warm.flow.core.entity.Node;
import org.dromara.warm.flow.orm.entity.FlowTask;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


/**
 * 工作流工具
 *
 * @author LionLi
 */
@ConditionalOnEnable
@Slf4j
@RequiredArgsConstructor
@Service
public class FlwCommonServiceImpl implements IFlwCommonService {

    private static final String DEFAULT_SUBJECT = "单据审批提醒";

    /**
     * 根据流程实例发送消息给当前处理人
     *
     * @param flowName    流程定义名称
     * @param instId      流程实例ID
     * @param messageType 消息类型列表
     * @param message     消息内容，为空则使用默认消息
     */
    @Override
    public void sendMessage(String flowName, Long instId, List<String> messageType, String message) {
        if (CollUtil.isEmpty(messageType)) {
            return;
        }
        IFlwTaskService flwTaskService = SpringUtils.getBean(IFlwTaskService.class);
        List<FlowTask> list = flwTaskService.selectByInstId(instId);
        if (CollUtil.isEmpty(list)) {
            return;
        }
        if (StringUtils.isBlank(message)) {
            message = "有新的【" + flowName + "】单据已经提交至您，请您及时处理。";
        }
        List<UserDTO> userList = flwTaskService.currentTaskAllUser(StreamUtils.toList(list, FlowTask::getId));
        if (CollUtil.isEmpty(userList)) {
            return;
        }
        sendMessage(messageType, message, DEFAULT_SUBJECT, userList);
    }

    /**
     * 发送消息给指定用户列表
     *
     * @param messageType 消息类型列表
     * @param message     消息内容
     * @param subject     邮件标题
     * @param userList    接收用户列表
     */
    @Override
    public void sendMessage(List<String> messageType, String message, String subject, List<UserDTO> userList) {
        if (CollUtil.isEmpty(messageType) || CollUtil.isEmpty(userList)) {
            return;
        }
        List<Long> userIds = new ArrayList<>(StreamUtils.toSet(userList, UserDTO::getUserId));
        Set<String> emails = StreamUtils.toSet(userList, UserDTO::getEmail);

        for (String code : messageType) {
            MessageTypeEnum messageTypeEnum = MessageTypeEnum.getByCode(code);
            if (ObjectUtil.isEmpty(messageTypeEnum)) {
                continue;
            }
            switch (messageTypeEnum) {
                case SYSTEM_MESSAGE:
                    SseMessageDto dto = new SseMessageDto();
                    dto.setUserIds(userIds);
                    dto.setMessage(message);
                    SseMessageUtils.publishMessage(dto);
                    break;
                case EMAIL_MESSAGE:
                    MailUtils.sendText(emails, subject, message);
                    break;
                case SMS_MESSAGE:
                    //todo 短信发送
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + messageTypeEnum);
            }
        }
    }


    /**
     * 申请人节点编码
     *
     * @param definitionId 流程定义id
     * @return 申请人节点编码
     */
    @Override
    public String applyNodeCode(Long definitionId) {
        List<Node> firstBetweenNode = FlowEngine.nodeService().getFirstBetweenNode(definitionId, new HashMap<>());
        return firstBetweenNode.get(0).getNodeCode();
    }
}
