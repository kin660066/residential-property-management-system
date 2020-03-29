package com.sontan.rpms.vo;

import com.sontan.rpms.entity.Flat;
import com.sontan.rpms.entity.PaymentDetail;
import com.sontan.rpms.entity.PaymentItem;
import com.sontan.rpms.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PaymentVo extends PaymentDetail {
    private String username;
    private String item;
}
