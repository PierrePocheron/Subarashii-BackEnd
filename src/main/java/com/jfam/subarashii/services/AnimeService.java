package com.jfam.subarashii.services;

import com.jfam.subarashii.configs.HttpClient;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeService {

    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    HttpClient httpClient;


    public Anime getOne(){
        Anime anime =  new Anime("Dragon ball Z" , "DBZ", "Akira Toriyama", "https://www.kamehashop.fr/24711-large_default/poster-dragon-ball-z-all-stars.jpg");
        return anime;
    }

    public List<Anime> getAll(){

        List<Anime> animeList = new ArrayList<>();
        Anime anime =  new Anime("Dragon ball Z" , "DBZ", "Akira Toriyama", "https://www.kamehashop.fr/24711-large_default/poster-dragon-ball-z-all-stars.jpg");
        Anime anime2 =  new Anime("Naruto" , "Naruto", "Masashi Kishimoto", "https://www.kamehashop.fr/24711-large_default/poster-dragon-ball-z-all-stars.jpg");
        Anime anime3 =  new Anime("One piece" , "One piece", "Eiichirō Oda", "https://media.ouest-france.fr/v1/pictures/MjAyMDEyOWZlNzlmZTlmM2VmNzMxM2IzYjBlZDM4MjYxM2Y4OTg?width=1260&height=708&focuspoint=50%2C25&cropresize=1&client_id=bpeditorial&sign=87704e84993984ab6724c7852fa13b7ace4e91171f4c20adf921e675597b59de");
        animeList.add(anime);
        animeList.add(anime2);
        animeList.add(anime3);
        return animeList;
    }

    public Anime getById(long id){

        var object =  httpClient.GetQuery("http://localhost:8080/animes");
        return animeRepository.findById(id).orElse(null);
    }
}
