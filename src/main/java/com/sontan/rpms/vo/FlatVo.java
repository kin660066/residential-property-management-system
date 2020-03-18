package com.sontan.rpms.vo;

import com.sontan.rpms.entity.Flat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlatVo extends Flat {
    private String layout;
}
