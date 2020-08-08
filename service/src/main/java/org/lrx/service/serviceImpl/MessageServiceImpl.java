package org.lrx.service.serviceImpl;

import org.lrx.dao.MessageMapper;
import org.lrx.entity.UniversityMessage;
import org.lrx.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    /**
     * 查询个人信息
     * @param messageUid
     * @return
     */
    @Override
    public Map<String, Object> selectMessage(String messageUid) {
        Map<String,Object> map = new HashMap<>();
        UniversityMessage universityMessage = messageMapper.selectMessage(messageUid);
        map.put("universityMessage",universityMessage);
        return map;
    }

    /**
     * 修改个人信息
     * @param universityMessage
     * @return
     */
    @Override
    public Map<String, Object> updateMessage(UniversityMessage universityMessage) {
        Map<String, Object> map = new HashMap<>();
        UniversityMessage universityMessage1 = messageMapper.selectMessage(universityMessage.getMessageUid());
        if (universityMessage1 == null) {
            int j = messageMapper.insertMessage(universityMessage);
            if(j<0){
                map.put("type", "error");
                map.put("msg", "插入失败");
                return map;
            }
            map.put("type", "success");
            map.put("msg", "插入成功");
            return map;
        } else {
            int i = messageMapper.updateMessage(universityMessage);
            if (i < 0) {
                map.put("type", "error");
                map.put("msg", "修改失败");
                return map;
            }
            map.put("type", "success");
            map.put("msg", "修改成功");
            return map;
        }
    }
}
