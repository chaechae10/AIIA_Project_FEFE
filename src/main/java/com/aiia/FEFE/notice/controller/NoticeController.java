package com.aiia.FEFE.notice.controller;

import com.aiia.FEFE.exception.NoticeNotFoundException;
import com.aiia.FEFE.notice.domain.Notice;
import com.aiia.FEFE.notice.dto.NoticeRequest;
import com.aiia.FEFE.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notices")
public class NoticeController {
    private final NoticeService noticeService;

    //모든 공지사항 조회
    @GetMapping
    public List<Notice> getAllNotices() {
       return noticeService.getAllNotices();
    }

    //특정 공지사항 조회
    @GetMapping("/{id}")
    public ResponseEntity<Notice> getNoticeById(@PathVariable Long id){
        Notice notice = noticeService.getNoticeById(id)
                .orElseThrow(() -> new NoticeNotFoundException("Notice not found with id = " + id));
        return ResponseEntity.ok(notice);
    }

    //공지사항 작성
    @PostMapping
    public ResponseEntity<Notice> createNotice(@RequestBody NoticeRequest noticeRequest){
        Notice notice = noticeService.createNotice(noticeRequest);
        return new ResponseEntity<>(notice, HttpStatus.CREATED);
    }

    //공지사항 수정
    @PutMapping("/{id}")
    public ResponseEntity<Notice> updateNotice(@PathVariable Long id, @RequestBody NoticeRequest noticeRequest){
        Optional<Notice> notice = noticeService.updateNotice(id, noticeRequest);
        return notice.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }


    //공지사항 삭제
    @DeleteMapping("/{id}")
    public void deleteNotice(@PathVariable Long id){
        noticeService.deleteNotice(id);
    }
}
