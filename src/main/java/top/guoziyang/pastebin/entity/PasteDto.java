package top.guoziyang.pastebin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasteDto {

    private Boolean needPassword;
    private PasteDo PasteDo;

}
