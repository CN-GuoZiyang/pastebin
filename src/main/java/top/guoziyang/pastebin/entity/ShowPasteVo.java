package top.guoziyang.pastebin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowPasteVo {

    private String type;
    private Date createTime;
    private String content;

}
