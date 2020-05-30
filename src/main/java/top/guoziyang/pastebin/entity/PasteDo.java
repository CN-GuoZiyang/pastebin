package top.guoziyang.pastebin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class PasteDo {

    @Id
    private String id;
    private Date createTime;
    private Date expireTime;
    private Boolean needPass;
    private String password;
    private Boolean burn;
    private Type type;
    private String content;

}
