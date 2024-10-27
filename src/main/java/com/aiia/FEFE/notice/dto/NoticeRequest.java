package com.aiia.FEFE.notice.dto;

import lombok.Getter;

@Getter
public class NoticeRequest {
    private String title;
    private String content;
    private String imageUrl;

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
