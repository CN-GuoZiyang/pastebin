package top.guoziyang.pastebin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostPasteVo {

    private String type;
    private String password;
    private String expire;
    private String content;
    private String burn;

}
