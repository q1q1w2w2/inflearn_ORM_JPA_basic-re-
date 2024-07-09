package jpabook.domain;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractDomain {

    private Long ab1;
    private String ab2;
    private String ab3;
}
