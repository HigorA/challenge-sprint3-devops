package br.com.fiap.challenge.controllers;

import br.com.fiap.challenge.model.request.FavoriteRequest;
import br.com.fiap.challenge.model.response.FavoriteResponse;
import br.com.fiap.challenge.services.FavoriteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private final FavoriteService service;

    public FavoriteController(FavoriteService service) {
        this.service = service;
    }

    @GetMapping
    public List<FavoriteResponse> findAll(@RequestParam("userId") Long userId) {
        return service.getAll(userId);
    }

    @PostMapping
    public FavoriteResponse save(@RequestBody FavoriteRequest favoriteRequest) {
        return service.save(favoriteRequest);
    }

    @PutMapping
    public void update(@RequestBody FavoriteRequest request) {
        service.update(request);
    }

    @DeleteMapping
    @Transactional
    public void delete(@RequestParam("favorite-id") Long favoriteId) {
        service.delete(favoriteId);
    }
}
