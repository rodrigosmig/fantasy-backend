package br.com.backend.fantasygame.application.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

abstract class BaseController<T> {

    protected ResponseEntity<Page<T>> paginarResposta(List<T> list, Pageable pageable) {
        var page = new PageImpl<T>(list, pageable, list.size());

        return ResponseEntity.ok(page);
    }
}
