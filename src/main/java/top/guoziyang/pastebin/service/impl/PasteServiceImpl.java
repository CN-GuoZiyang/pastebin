package top.guoziyang.pastebin.service.impl;

import io.seruco.encoding.base62.Base62;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import top.guoziyang.pastebin.entity.PasteDo;
import top.guoziyang.pastebin.entity.PasteDto;
import top.guoziyang.pastebin.entity.PostPasteVo;
import top.guoziyang.pastebin.entity.Type;
import top.guoziyang.pastebin.service.PasteService;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

@Service
public class PasteServiceImpl implements PasteService {

    @Resource
    private MongoTemplate mongoTemplate;

    private Base62 base62 = Base62.createInstance();

    @Override
    public String postPaste(PostPasteVo pasteVo) {
        String pasteId = new String(base62.encode(String.valueOf(System.currentTimeMillis() + (Math.random()+1)*10000000).getBytes()));
        PasteDo pasteDo = new PasteDo();
        pasteDo.setId(pasteId);
        pasteDo.setCreateTime(new Date());
        Date expireDate = new Date();
        if("day".equals(pasteVo.getExpire())) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(expireDate);
            calendar.add(Calendar.DATE, 1);
            expireDate = calendar.getTime();
        } else if("week".equals(pasteVo.getExpire())) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(expireDate);
            calendar.add(Calendar.DATE, 7);
            expireDate = calendar.getTime();
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(expireDate);
            calendar.add(Calendar.MONTH, 1);
            expireDate = calendar.getTime();
        }
        pasteDo.setExpireTime(expireDate);
        if("".equals(pasteVo.getPassword())) {
            pasteDo.setNeedPass(false);
        } else {
            pasteDo.setNeedPass(true);
            pasteDo.setPassword(pasteVo.getPassword());
        }
        pasteDo.setBurn("on".equals(pasteVo.getBurn()));
        pasteDo.setType(Type.valueOf(pasteVo.getType()));
        pasteDo.setContent(pasteVo.getContent());
        mongoTemplate.insert(pasteDo);
        return pasteId;
    }

    @Override
    public PasteDto getPasteById(String pasteId) {
        PasteDo pasteDo = mongoTemplate.findOne(new Query(Criteria.where("id").is(pasteId)), PasteDo.class);
        if(pasteDo == null) {
            return null;
        }
        if(pasteDo.getExpireTime().before(new Date())) {
            mongoTemplate.remove(pasteDo);
            return null;
        }
        PasteDto pasteDto = new PasteDto();
        pasteDto.setNeedPassword(pasteDo.getNeedPass());
        pasteDto.setPasteDo(pasteDo);
        return pasteDto;
    }

    @Override
    public void deletePasteDo(PasteDo pasteDo) {
        mongoTemplate.remove(pasteDo);
    }

}
