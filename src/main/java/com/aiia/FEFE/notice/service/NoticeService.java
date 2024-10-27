package com.aiia.FEFE.notice.service;

import com.aiia.FEFE.exception.NoticeNotFoundException;
import com.aiia.FEFE.notice.domain.Notice;
import com.aiia.FEFE.notice.dto.NoticeRequest;
import com.aiia.FEFE.notice.repository.NoticeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public List<Notice> getAllNotices(){
        return noticeRepository.findAll();
    }

    public Optional<Notice> getNoticeById(Long id) {
        return noticeRepository.findById(id);
    }

    public Notice createNotice(NoticeRequest noticeRequest){
        Notice notice = new Notice();
        notice.setTitle(noticeRequest.getTitle());
        notice.setContent(noticeRequest.getContent());
        notice.setImageUrl(noticeRequest.getImageUrl());
        notice.setCreatedAt(LocalDateTime.now());
        notice.setUpdatedAt(LocalDateTime.now());
        return noticeRepository.save(notice);
    }

    public Optional<Notice> updateNotice(Long id, NoticeRequest noticeRequest){
        return noticeRepository.findById(id).map(notice -> {
            notice.setTitle(noticeRequest.getTitle());
            notice.setContent(noticeRequest.getContent());
            notice.setImageUrl(noticeRequest.getImageUrl());
            notice.setCreatedAt(LocalDateTime.now());
            notice.setUpdatedAt(LocalDateTime.now());
            return noticeRepository.save(notice);
        });

    }
    public void deleteNotice(Long id){
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFoundException("Notice not found with id = " + id));
        noticeRepository.delete(notice);
    }
}
