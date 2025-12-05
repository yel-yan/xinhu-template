package com.xinhu.common.translation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinhu.common.annotation.Anonymous;
import com.xinhu.common.translation.annotation.TranslationType;
import com.xinhu.common.translation.core.TranslationInterface;
import com.xinhu.common.translation.core.handler.TranslationBeanSerializerModifier;
import com.xinhu.common.translation.core.handler.TranslationHandler;
import com.xinhu.common.utils.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 翻译模块配置类
 *
 * @author Lion Li
 */
@Slf4j
@Configuration
public class TranslationConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private List<TranslationInterface<?>> list;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

//    @PostConstruct
//    public void init() {
//        // 延迟初始化，在需要时才获取所有实现类
//        initializeTranslationMap();
//    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeTranslationMap() {
        // 应用完全启动后再初始化
        Map<String, TranslationInterface> beans =
            applicationContext.getBeansOfType(TranslationInterface.class);

        Map<String, TranslationInterface<?>> map = new HashMap<>(beans.size());
        for (Map.Entry<String, TranslationInterface> entry : beans.entrySet()) {
            TranslationInterface<?> trans = entry.getValue();
            if (trans.getClass().isAnnotationPresent(TranslationType.class)) {
                TranslationType annotation = trans.getClass().getAnnotation(TranslationType.class);
                map.put(annotation.type(), trans);
            } else {
                log.warn(trans.getClass().getName() + " 翻译实现类未标注 TranslationType 注解!");
            }
        }
        TranslationHandler.TRANSLATION_MAPPER.putAll(map);

        // 设置 Bean 序列化修改器
        objectMapper.setSerializerFactory(
            objectMapper.getSerializerFactory()
                .withSerializerModifier(new TranslationBeanSerializerModifier()));
    }
//
//    @PostConstruct
//    public void init() {
//        //获取所有TranslationInterface的实现类
//        Map<String, TranslationInterface> beans =
//            SpringUtils.context().getBeansOfType(TranslationInterface.class);
//        Map<String, TranslationInterface<?>> map = new HashMap<>(list.size());
//        for (TranslationInterface<?> trans : list) {
//            if (trans.getClass().isAnnotationPresent(TranslationType.class)) {
//                TranslationType annotation = trans.getClass().getAnnotation(TranslationType.class);
//                map.put(annotation.type(), trans);
//            } else {
//                log.warn(trans.getClass().getName() + " 翻译实现类未标注 TranslationType 注解!");
//            }
//        }
//        TranslationHandler.TRANSLATION_MAPPER.putAll(map);
//        // 设置 Bean 序列化修改器
//        objectMapper.setSerializerFactory(
//            objectMapper.getSerializerFactory()
//                .withSerializerModifier(new TranslationBeanSerializerModifier()));
//    }
}
