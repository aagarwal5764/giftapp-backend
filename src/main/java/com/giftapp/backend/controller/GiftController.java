package com.giftapp.backend.controller;

import com.giftapp.backend.entity.Gift;
import com.giftapp.backend.repository.GiftRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gifts")
public class GiftController {

    private final GiftRepository repo;

    public GiftController(GiftRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Gift> getAll() {
        return repo.findAll();
    }

    @PostMapping("/add")
    public Gift create(@RequestBody Gift gift) {
        return repo.save(gift);
    }
}