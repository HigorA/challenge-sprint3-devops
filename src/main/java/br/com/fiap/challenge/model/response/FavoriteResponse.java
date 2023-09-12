package br.com.fiap.challenge.model.response;

public record FavoriteResponse(
        Long idFavorite,
        String title,
        String url
) {
}
