package com.zzqedu.dousheng.core.common.resp;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zzqedu.dousheng.core.common.constant.ErrorCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

/**
 * http rest 响应工具类及数据格式封装
 * 使用构建者模式
 */
@Schema(description = "响应参数")
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResp {

    /**
     * 响应码
     */
    @Schema(description = "响应状态码")
    @JsonProperty("status_code")
    private int statusCode;

    /**
     * 响应消息
     */
    @JsonProperty("status_msg")
    private String statusMsg;


    /**
     * 下次请求的时间
     */
    @JsonProperty("next_time")
    private Long nextTime;

    /**
     * 视频列表
     */
    @JsonProperty("video_list")
    private List<?> videoList;


    /**
     * 用户id
     */
    @JsonProperty("user_id")
    private Long userId;

    /**
     * 用户鉴权token
     */
    @JsonProperty("token")
    private String token;

    /**
     * 用户信息
     */
    @JsonProperty("user")
    private Object user;

    /**
     * 评论
     */
    @JsonProperty("comment")
    private Object comment;

    /**
     * 评论列表
     */
    @JsonProperty("comment_list")
    private List<?> commentList;

    /**
     * 消息列表
     */
    @JsonProperty("message_list")
    private List<?> messageList;

    /**
     * 用户列表
     */
    @JsonProperty("user_list")
    private List<?> userList;


    public RestResp() {
        this.statusCode = ErrorCodeEnum.OK.getCode();
        this.statusMsg = ErrorCodeEnum.OK.getMessage();
    }

    public RestResp(ErrorCodeEnum errorCodeEnum) {
        this.statusCode = errorCodeEnum.getCode();
        this.statusMsg = errorCodeEnum.getMessage();
    }

    private RestResp(Builder builder) {
        this.statusCode = ErrorCodeEnum.OK.getCode();
        this.statusMsg = ErrorCodeEnum.OK.getMessage();

        this.nextTime = builder.nextTime;
        this.videoList = builder.videoList;
        this.userId = builder.userId;
        this.token = builder.token;
        this.user = builder.user;
        this.comment = builder.comment;
        this.commentList = builder.commentList;
        this.messageList = builder.messageList;
        this.userList = builder.userList;
    }

    // 使用构建者模式
    public static final class Builder {
        private Long nextTime;
        private List<?> videoList;
        private Long userId;
        private String token;
        private Object user;
        private Object comment;
        private List<?> commentList;
        private List<?> messageList;
        private List<?> userList;

        public Builder() {
        }

        public Builder withNextTime(Long nextTime) {
            this.nextTime = nextTime;
            return this;
        }
        public Builder withVideoList(List<?> videoList) {
            this.videoList = videoList;
            return this;
        }
        public Builder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }
        public Builder withToken(String token) {
            this.token = token;
            return this;
        }
        public Builder withComment(Object comment) {
            this.comment = comment;
            return this;
        }
        public Builder withCommentList(List<?> commentList) {
            this.commentList = commentList;
            return this;
        }
        public Builder withMessageList(List<?> messageList) {
            this.messageList = messageList;
            return this;
        }
        public Builder withUserList(List<?> userList) {
            this.userList = userList;
            return this;
        }

        public RestResp build() {
            return new RestResp(this);
        }


    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * 业务处理成功，没有数据返回
     */
    public static RestResp ok() {
        return new RestResp();
    }



    /**
     * 业务处理失败
     */
    public static RestResp fail(ErrorCodeEnum errorCode) {
        return new RestResp(errorCode);
    }

    /**
     * 业务处理失败
     */
    public static RestResp fail(int code, String message) {
        RestResp resp = new RestResp();
        resp.statusCode = code;
        resp.statusMsg = message;
        return resp;
    }

    /**
     * 系统错误
     */
    public static RestResp error() {
        return new RestResp(ErrorCodeEnum.SYSTEM_ERROR);
    }

}
