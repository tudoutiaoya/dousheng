package com.zzqedu.dousheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zzqedu.dousheng.core.common.constant.ErrorCodeEnum;
import com.zzqedu.dousheng.core.common.constant.RedisConstant;
import com.zzqedu.dousheng.core.common.exception.BusinessException;
import com.zzqedu.dousheng.core.common.resp.RestResp;
import com.zzqedu.dousheng.core.common.util.RandomUtils;
import com.zzqedu.dousheng.core.util.JWTUtil;
import com.zzqedu.dousheng.core.util.UserHolder;
import com.zzqedu.dousheng.dao.entity.User;
import com.zzqedu.dousheng.dao.mapper.UserMapper;
import com.zzqedu.dousheng.dto.req.LoginReqDto;
import com.zzqedu.dousheng.dto.resp.UserRespDto;
import com.zzqedu.dousheng.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final String AVATAR_PREFIX = "http://tiktok.tudoutiao.pro/avatar/";
    private final String AVATAR_SUFFIX = ".jfif";

    private final UserMapper userMapper;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public RestResp register(LoginReqDto loginReqDto) {
        String username = loginReqDto.getUsername();
        String password = loginReqDto.getPassword();
        // 查看用户是否存在
        if (userMapper.selectByName(username) != null) {
            throw new BusinessException(ErrorCodeEnum.USER_NAME_EXIST);
        }
        // 密码加密(Bcrypt)
        String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User();
        user.setName(username);
        user.setPassword(encodedPassword);
        user.setAvatar(AVATAR_PREFIX + RandomUtils.generateAvatarNumber() + AVATAR_SUFFIX);

        // 保存
        userMapper.insertSelective(user);

        // jwt生成token
        String token = JWTUtil.createToken(user.getId());

        UserRespDto userRespDto = user2userDto(user);

        // redis中保存token
        stringRedisTemplate.opsForValue().set(RedisConstant.TOKEN + token, JSON.toJSONString(userRespDto), RedisConstant.TOKEN_TIME ,TimeUnit.DAYS);

        return RestResp.builder()
                .withUserId(user.getId())
                .withToken(token)
                .build();
    }

    @Override
    public RestResp login(LoginReqDto loginReqDto) {
        String username = loginReqDto.getUsername();
        String password = loginReqDto.getPassword();
        // 根据用户名查询
        User user = userMapper.selectByName(username);
        // 用户存在否
        if (user == null) {
            throw new BusinessException(ErrorCodeEnum.USER_NOT_EXIST);
        }
        boolean isSame = BCrypt.checkpw(password, user.getPassword());
        if (!isSame) {
            throw new BusinessException(ErrorCodeEnum.PASSWORD_ERROR);
        }
        // 用户信息脱敏存入到redis
        UserRespDto userDto = user2userDto(user);

        // 生成token
        String token = JWTUtil.createToken(user.getId());

        // redis中保存token
        stringRedisTemplate.opsForValue().set(RedisConstant.TOKEN + token, JSON.toJSONString(userDto), RedisConstant.TOKEN_TIME ,TimeUnit.DAYS);

        return RestResp.builder()
                .withToken(token)
                .withUserId(user.getId())
                .build();
    }

    @Override
    public RestResp currentUser(String userId, String token) {
        // redis查询当前用户信息
        UserRespDto userRespDto = UserHolder.get();
        if (!userId.equals(userRespDto.getId().toString())) {
            throw new BusinessException(ErrorCodeEnum.TOKEN_IS_INVALID);
        }
        return RestResp.builder()
                .withUser(userRespDto)
                .build();
    }

    /**
     * 用户信息拖敏
     */
    private UserRespDto user2userDto(User user) {
        UserRespDto userRespDto = new UserRespDto();
        BeanUtil.copyProperties(user, userRespDto, CopyOptions.create().ignoreNullValue());
        return userRespDto;
    }

    @Override
    public RestResp getUserById(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        String useStr = JSON.toJSONString(user);
        return RestResp.builder().withToken(useStr).build();
    }


}