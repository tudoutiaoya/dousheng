package com.zzqedu.dousheng.dto.resp;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class UserRespDto {
    /**
     * id
     */
    @Schema(description = "Id")
    private Long id;

    /**
     * 用户名称
     */
    @Schema(description = "用户名称")
    private String name = "";

    /**
     * 关注总数
     */
    @Schema(description = "关注总数")
    private Long followCount = 0L;

    /**
     * 粉丝总数
     */
    @Schema(description = "粉丝总数")
    private Long followerCount = 0L;

    /**
     * true已关注 false未关注
     */
    @Schema(description = "是否关注")
    private Boolean isFollow = false;

    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private String avatar = "";

    /**
     * 用户个人页顶部大图
     */
    @Schema(description = "用户个人页顶部大图")
    private String backgroundImage = "";

    /**
     * 个人简介
     */
    @Schema(description = "个人简介")
    private String signature = "";

    /**
     * 获赞数量
     */
    @Schema(description = "获赞数量")
    private Long totalFavorited = 0L;

    /**
     * 作品数
     */
    @Schema(description = "作品数")
    private Long workCount = 0L;

    /**
     * 喜欢数
     */
    @Schema(description = "喜欢数")
    private Long favoriteCount = 0L;


}
