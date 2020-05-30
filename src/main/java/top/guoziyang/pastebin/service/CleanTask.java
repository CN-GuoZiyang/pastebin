package top.guoziyang.pastebin.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.guoziyang.pastebin.entity.PasteDo;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class CleanTask {

    @Resource
    private MongoTemplate mongoTemplate;

    @Scheduled(cron = "* * 1 * * ?")
    public void cleanExpired() {
        mongoTemplate.remove(new Query(Criteria.where("expireTime").lt(new Date())), PasteDo.class);
    }

}
