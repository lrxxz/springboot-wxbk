package org.lrx.controller;

import org.lrx.entity.UniversityMessage;
import org.lrx.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/selectMessage")
    public Map<String, Object> selectMessage(@RequestParam("messageUid") String messageUid) {
        return  messageService.selectMessage(messageUid);
    }

    @PostMapping("/updateMessage")
    public Map<String, Object> updateMessage(@RequestBody UniversityMessage universityMessage) {
        return  messageService.updateMessage(universityMessage);
    }
}
