package top.guoziyang.pastebin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import top.guoziyang.pastebin.entity.PasteDto;
import top.guoziyang.pastebin.entity.PostPasteVo;
import top.guoziyang.pastebin.entity.ShowPasteVo;
import top.guoziyang.pastebin.service.PasteService;

import javax.annotation.Resource;

@Controller
public class PasteController {

    @Resource
    private PasteService pasteService;

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @PostMapping("/")
    public String postPaste(PostPasteVo pasteVo, Model model) {
        String pasteId = pasteService.postPaste(pasteVo);
        model.addAttribute("pasteId", pasteId);
        return "success";
    }

    @GetMapping("/{pasteId}")
    public String getPaste(@PathVariable String pasteId, Model model) {
        PasteDto dto = pasteService.getPasteById(pasteId);
        if(dto == null) return "index";
        if(dto.getNeedPassword()) {
            return "password";
        }
        ShowPasteVo pasteVo = new ShowPasteVo();
        pasteVo.setType(dto.getPasteDo().getType().name());
        pasteVo.setCreateTime(dto.getPasteDo().getCreateTime());
        pasteVo.setContent(dto.getPasteDo().getContent());
        model.addAttribute("paste", pasteVo);
        return "show";
    }

    @PostMapping("/{pasteId}")
    public String getPasteByPass(@PathVariable String pasteId, String password, Model model) {
        PasteDto dto = pasteService.getPasteById(pasteId);
        if(dto == null) return "index";
        if(dto.getPasteDo().getPassword() == null || "".equals(dto.getPasteDo().getPassword()) && password.equals(dto.getPasteDo().getPassword())) {
            ShowPasteVo pasteVo = new ShowPasteVo();
            pasteVo.setType(dto.getPasteDo().getType().name());
            pasteVo.setCreateTime(dto.getPasteDo().getCreateTime());
            pasteVo.setContent(dto.getPasteDo().getContent());
            model.addAttribute("paste", pasteVo);
            return "show";
        }
        return "password";
    }

}
