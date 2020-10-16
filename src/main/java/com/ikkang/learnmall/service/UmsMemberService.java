package com.ikkang.learnmall.service;

/**
 * 会员管理Service
 */
public interface UmsMemberService {
    /**
     * 生成验证码
     * @param telephone
     * @return
     */
    String generateAuthCode(String telephone);

    /**
     * 验证码校验
     * @param telephone
     * @param authCode
     * @return
     */
    boolean verifyAuthCode(String telephone, String authCode);
}
