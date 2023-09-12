package br.com.fiap.challenge.model.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "favorites")
public class Favorite implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorite")
    private Long idFavorite;

    private String title;

    @Column(length = 11400)
    private String url;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    public Favorite() {
    }

    public Favorite(Long idFavorite, String title, String url) {
        this.idFavorite = idFavorite;
        this.title = title;
        this.url = url;
    }

    public Long getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(Long idFavorite) {
        this.idFavorite = idFavorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return Objects.equals(idFavorite, favorite.idFavorite) && Objects.equals(title, favorite.title) && Objects.equals(url, favorite.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFavorite, title, url);
    }
}
