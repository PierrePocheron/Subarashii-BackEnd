package com.jfam.subarashii.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.Genre;
import com.jfam.subarashii.repositories.GenreRepository;
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

    public List<Genre> getAll() throws ResourceApiNotFoundException {

        List<Genre> genresList = genreRepository.findAll();

        if(genresList.size() == 0)
            genresList = fetchApi();

        return genresList;
    }


    public List<Genre> convertJsonObjectGenreToListGenre(JsonObject jsonObject,String keywordGenre){
        JsonArray genresJsonArray = jsonObject.get(keywordGenre).getAsJsonArray();
        List<Genre> genresList = new ArrayList<>();
        genresJsonArray.forEach((JsonGenre)->{
            Genre gen = new Genre(JsonGenre.getAsJsonObject());
            genresList.add(gen);
        });
        return genresList;
    }


    public List<Genre> convertJsonArrayIdGenreToListGenre(JsonArray jsonArray){
        List<Genre> genresList = new ArrayList<>();
        jsonArray.forEach((jsonIdGenre)->{
            Genre genre =  genreRepository.findByIdApi(jsonIdGenre.getAsLong());
            genresList.add(genre);
        });
        return genresList;
    }



    public List<Genre> getAllByIdAPi(List<Long> idApiGenreList){
        return  genreRepository.findByIdApiIn(idApiGenreList);
    }

    // region === PRIVATE METHOD ===

    private List<Genre> fetchApi() throws ResourceApiNotFoundException {
        JsonObject jsonObject = httpClient.GetQuery(ROUTE_GENRE_ANIME);
        JsonArray jsonArray = jsonObject.get("genres").getAsJsonArray();
        List<Genre> genresList = new ArrayList<>();

        jsonArray.forEach((json_genre)->{
            JsonObject genreJO = json_genre.getAsJsonObject();
            Genre genre = new Genre(genreJO);
            genresList.add(genre);
        });
        return genreRepository.saveAll(genresList);
    }




    //endregion
}
