package br.com.fiap.challenge.model.request;

public record FavoriteRequest(
        Long idFavorite,
        String title,
        String url,
        Long idUser
) {
}
