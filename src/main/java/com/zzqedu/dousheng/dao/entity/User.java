package com.zzqedu.dousheng.dao.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Schema
@Data
public class User implements Serializable {
    /**
     * Id
     */
    @Schema(description = "Id")
    private Long id;

    /**
     * 用户名称
     */
    @Schema(description = "用户名称")
    private String name;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 关注总数
     */
    @Schema(description = "关注总数")
    private Long followCount;

    /**
     * 粉丝总数
     */
    @Schema(description = "粉丝总数")
    private Long followerCount;

    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private String avatar;

    /**
     * 用户个人页顶部大图
     */
    @Schema(description = "用户个人页顶部大图")
    private String backgroundImage;

    /**
     * 个人简介
     */
    @Schema(description = "个人简介")
    private String signature;

    /**
     * 获赞数量
     */
    @Schema(description = "获赞数量")
    private Long totalFavorited;

    /**
     * 作品数
     */
    @Schema(description = "作品数")
    private Long workCount;

    /**
     * 喜欢数
     */
    @Schema(description = "喜欢数")
    private Long favoriteCount;

    @Schema(description = "")
    private LocalDateTime createTime;

    @Schema(description = "")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}