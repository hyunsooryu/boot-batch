package com.hyunsoo.bootbatch.core.domain.orders;


import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@ToString
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String orderItem;
    private Integer price;
    private Date orderDate;
}
