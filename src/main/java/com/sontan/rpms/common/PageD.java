package com.sontan.rpms.common;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PageD  {
    private Integer page=1;
    private Integer limit=5;
}
