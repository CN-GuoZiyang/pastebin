package top.guoziyang.pastebin.service;

import top.guoziyang.pastebin.entity.PasteDo;
import top.guoziyang.pastebin.entity.PasteDto;
import top.guoziyang.pastebin.entity.PostPasteVo;

public interface PasteService {

    String postPaste(PostPasteVo pasteVo);

    PasteDto getPasteById(String pasteId);

    void deletePasteDo(PasteDo pasteDo);

}
