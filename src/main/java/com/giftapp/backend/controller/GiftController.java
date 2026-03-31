package com.giftapp.backend.controller;

import com.giftapp.backend.dto.GiftDTO;
import com.giftapp.backend.service.GiftService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gifts")
public class GiftController {

    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @GetMapping
    public List<GiftDTO> getAll() {
        return giftService.getAllGifts();
    }

    @PostMapping("/add")
    public GiftDTO add(@RequestBody GiftDTO giftDTO) {
        return giftService.addGift(giftDTO);
    }
}