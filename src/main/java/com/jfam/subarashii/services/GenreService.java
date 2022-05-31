package com.jfam.subarashii.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Genre;
import com.jfam.subarashii.repositories.GenreRepository;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.jfam.subarashii.utils.Constantes.ApiMovie.ROUTE_GENRE_ANIME;

@Service
public class GenreService {

    @Autowired
    HttpClient httpClient;

    @Autowired
    GenreRepository genreRepository;

    public List<Genre> getAllStartApplication() throws ResourceApiNotFoundException {

        List<Genre> genresList = genreRepository.findAll();

        if(genresList.isEmpty())
            genresList = fetchGenresFromApi();

        return genresList;
    }

    public List<Genre> getAll() throws ResourceApiNotFoundException {

        List<Genre> genresList = genreRepository.findAll();

        if(genresList.isEmpty())
            genresList = fetchGenresFromApi();

        return genresList;
    }

    public Genre getGenreByIdApi( Long idApi){
        return genreRepository.findByIdApi(idApi);
    }

    public List<Genre> convertJsonObjectGenreToListGenre(JsonObject jsonResult,String keywordGenre){
        JsonArray genresJsonArray = jsonResult.get(keywordGenre).getAsJsonArray();
        List<Genre> genresList = new ArrayList<>();
        genresJsonArray.forEach((jsonGenre)->{
            Long idGenre = jsonGenre.getAsJsonObject().get(Constantes.ApiMovie.JSON_KEY_ID).getAsLong();
            Genre gen = getGenreByIdApi(idGenre);
            genresList.add(gen);
        });
        return genresList;
    }

    // region === PRIVATE METHOD ===
    private List<Genre> fetchGenresFromApi() throws ResourceApiNotFoundException {
        JsonObject genreResult = httpClient.getQuery(ROUTE_GENRE_ANIME);
        JsonArray jsonArrayGenre = genreResult.get(Constantes.ApiMovie.JSON_KEY_GENRES).getAsJsonArray();
        List<Genre> genresList = new ArrayList<>();

        jsonArrayGenre.forEach((jsonGenre)->{
            JsonObject genreJO = jsonGenre.getAsJsonObject();
            Genre genre = new Genre(genreJO);
            genresList.add(genre);
        });
        return genreRepository.saveAll(genresList);
    }

}
