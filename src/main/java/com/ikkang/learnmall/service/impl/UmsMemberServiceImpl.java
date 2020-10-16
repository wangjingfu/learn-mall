package com.ikkang.learnmall.service.impl;

import com.ikkang.learnmall.service.RedisService;
import com.ikkang.learnmall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * 会员管理Service实现类
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public String generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        String key = makeRedisKey(telephone);
        redisService.set(key, sb.toString());
        redisService.expire(key, AUTH_CODE_EXPIRE_SECONDS);
        return sb.toString();
    }

    @Override
    public boolean verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return false;
        }
        String key = makeRedisKey(telephone);
        String realAuthCode = redisService.get(key);
        return authCode.equals(realAuthCode);
    }

    private String makeRedisKey(String telephone) {
        return REDIS_KEY_PREFIX_AUTH_CODE + telephone;
    }
}
