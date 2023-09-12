package br.com.fiap.challenge.services;

import br.com.fiap.challenge.model.entities.Favorite;
import br.com.fiap.challenge.model.entities.User;
import br.com.fiap.challenge.model.request.FavoriteRequest;
import br.com.fiap.challenge.model.response.FavoriteResponse;
import br.com.fiap.challenge.repositories.FavoriteRepository;
import br.com.fiap.challenge.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;

    public FavoriteService(FavoriteRepository repository, UserRepository userRepository) {
        this.favoriteRepository = repository;
        this.userRepository = userRepository;
    }

    public List<FavoriteResponse> getAll(Long idUser) {
        return favoriteRepository.findAllByUserId(idUser).stream().map(favorite -> new FavoriteResponse(favorite.getIdFavorite(), favorite.getTitle(), favorite.getUrl())).toList();
    }

    public FavoriteResponse save(FavoriteRequest favoriteRequest) {
        Favorite favorite = new Favorite(favoriteRequest.idFavorite(), favoriteRequest.title(), favoriteRequest.url());
        User user = userRepository.findById(favoriteRequest.idUser()).orElseThrow();
        favorite.setUser(user);
        favorite = favoriteRepository.save(favorite);
        return new FavoriteResponse(favorite.getIdFavorite(), favorite.getTitle(), favorite.getUrl());
    }

    public void update(FavoriteRequest request) {
        Favorite favorite = new Favorite(request.idFavorite(), request.title(), request.url());
        User user = userRepository.findById(request.idUser()).orElseThrow();
        favorite.setUser(user);
        favoriteRepository.save(favorite);
    }

    public void delete(Long favoriteId) {

        favoriteRepository.deleteById(favoriteId);
    }
}
