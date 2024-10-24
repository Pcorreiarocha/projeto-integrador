package com.faculdade.commons.support;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Setter
@Getter
public class PageDto<T> {

    private long        total;
    private List<T> content;
    private PageRequest pageable;

}