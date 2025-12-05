package com.xinhu.framework.listener;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.xinhu.common.constant.CacheConstants;
import com.xinhu.common.constant.CacheNames;
import com.xinhu.common.core.domain.dto.UserOnlineDTO;
import com.xinhu.common.core.domain.model.LoginUser;
import com.xinhu.common.enums.UserType;
import com.xinhu.common.helper.LoginHelper;
import com.xinhu.common.utils.ServletUtils;
import com.xinhu.common.utils.redis.CacheUtils;
import com.xinhu.common.utils.ip.AddressUtils;
import com.xinhu.common.utils.redis.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * 用户行为 侦听器的实现
 *
 * @author Lion Li
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class UserActionListener implements SaTokenListener {

    private final SaTokenConfig tokenConfig;

    /**
     * 每次登录时触发
     */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginParameter saLoginParameter) {
        UserType userType = UserType.getUserType(loginId.toString());
        if (userType == UserType.SYS_USER) {
            UserAgent userAgent = UserAgentUtil.parse(ServletUtils.getRequest().getHeader("User-Agent"));
            String ip = ServletUtils.getClientIP();
            LoginUser user = LoginHelper.getLoginUser();
            UserOnlineDTO dto = new UserOnlineDTO();
            dto.setIpaddr(ip);
            dto.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
            dto.setBrowser(userAgent.getBrowser().getName());
            dto.setOs(userAgent.getOs().getName());
            dto.setLoginTime(System.currentTimeMillis());
            dto.setTokenId(tokenValue);
            dto.setUserName(user.getUsername());
            dto.setDeptName(user.getDeptName());
            String cacheNames = CacheNames.ONLINE_TOKEN;
            if (tokenConfig.getTimeout() > 0) {
                // 增加 ttl 过期时间 单位秒
                cacheNames = CacheNames.ONLINE_TOKEN + "#" + tokenConfig.getTimeout() + "s";
            }
            if(tokenConfig.getTimeout() == -1) {
                RedisUtils.setCacheObject(CacheConstants.ONLINE_TOKEN_KEY + tokenValue, dto);
            } else {
                RedisUtils.setCacheObject(CacheConstants.ONLINE_TOKEN_KEY + tokenValue, dto, Duration.ofSeconds(tokenConfig.getTimeout()));
            }
            CacheUtils.put(cacheNames, tokenValue, dto);
            log.info("user doLogin, userId:{}, token:{}", loginId, tokenValue);
        } else if (userType == UserType.APP_USER) {
            // app端 自行根据业务编写
        }
    }

    /**
     * 每次注销时触发
     */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        CacheUtils.evict(CacheNames.ONLINE_TOKEN, tokenValue);
        RedisUtils.deleteObject(CacheConstants.ONLINE_TOKEN_KEY + tokenValue);
        log.info("user doLogout, userId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次被踢下线时触发
     */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        CacheUtils.evict(CacheNames.ONLINE_TOKEN, tokenValue);
        RedisUtils.deleteObject(CacheConstants.ONLINE_TOKEN_KEY + tokenValue);
        log.info("user doLogoutByLoginId, userId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次被顶下线时触发
     */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
        CacheUtils.evict(CacheNames.ONLINE_TOKEN, tokenValue);
        log.info("user doReplaced, userId:{}, token:{}", loginId, tokenValue);
    }


    /**
     * 每次被封禁时触发
     */
    @Override
    public void doDisable(String s, Object o, String s1, int i, long l) {

    }

    /**
     * 每次被解封时触发
     */
    @Override
    public void doUntieDisable(String s, Object o, String s1) {

    }

    /**
     * 每次打开二级认证时触发
     */
    @Override
    public void doOpenSafe(String s, String s1, String s2, long l) {

    }

    /**
     * 每次创建Session时触发
     */
    @Override
    public void doCloseSafe(String s, String s1, String s2) {

    }

    /**
     * 每次创建Session时触发
     */
    @Override
    public void doCreateSession(String id) {
    }

    /**
     * 每次注销Session时触发
     */
    @Override
    public void doLogoutSession(String id) {
    }

    /**
     * 每次Token续期时触发
     */
    @Override
    public void doRenewTimeout(String s, Object o, String s1, long l) {

    }


}
