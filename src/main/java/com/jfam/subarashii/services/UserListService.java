package com.jfam.subarashii.services;

import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.UserList;
import com.jfam.subarashii.repositories.AnimeRepository;
import com.jfam.subarashii.repositories.UserListRepository;
import com.jfam.subarashii.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class UserListService {

    @Autowired
    UserListRepository userListRepository;

    public List<UserList> createDefaultList(User user) {

        List<UserList> defaultList = Arrays.asList(
                new UserList(Constantes.DefaultList.A_VOIR, user, false),
                new UserList(Constantes.DefaultList.EN_COURS, user, false),
                new UserList(Constantes.DefaultList.TERMINEE, user, false),
                new UserList(Constantes.DefaultList.EN_ATTENTE, user, false),
                new UserList(Constantes.DefaultList.FAVORIS, user, false)
        );

        return userListRepository.saveAll(defaultList);
    }

    public UserList createCustomList(User user, String nameList) {
        UserList customList = new UserList(nameList, user, true);
        return userListRepository.save(customList);
    }

    public List<UserList> getCurrentUserList(User user) {
        List<UserList> userList = userListRepository.findAllByUser(user);
        return userList;
    }

    /***Récupère la liste de l'utilisateur
     * @param idUserList id de la liste
     * @param user l'utilisateur courant
     * @return si null la liste n'existe pas ou n'appartient pas à l'utilisateur
     */
    public UserList getOneUserListByIdForCurrentUser(Long idUserList, User user) {

        // check si existe grace à l'id user list and anime
        UserList userList = userListRepository.findByIdAndUser(idUserList, user);

        if (userList == null)
            return null;
        return userList;
    }

    public UserList addAnimeToUserList(Anime animeToAdd, List<Anime> animeListInUserList, UserList currentUserList) {
        animeListInUserList.add(animeToAdd);
        currentUserList.setAnimes(animeListInUserList);
        return userListRepository.save(currentUserList);
    }

    public List<Anime> getAllAnimeByUserList(User user, Long idList) {
        UserList userList = userListRepository.findAllByUserAndId(user, idList);
        if (userList == null)
            return null;
        List<Anime> animeList = userList.getAnimes();
        animeList.sort(Comparator.comparing(Anime::getOriginalName));
        return animeList;
    }

    public boolean deleteListByIdList(Long idUserList, User user) {
        UserList userList = userListRepository.findByIdAndUser(idUserList, user);
        if (userList == null || !userList.isDeletable()) return false;
        userListRepository.delete(userList);
        return true;
    }

    public UserList deleteAnimeList(Long idanime, Long iduserlist,User currentUser){
        // recuperer userlist grace à user et idUserlist

      UserList userList =   getOneUserListByIdForCurrentUser(iduserlist,currentUser);

        //si userlist n'existe pas renvoyer null
                //deja gerer dans la méthode
        if (userList == null){
            return null;
        }


        // userlist donne moi liste animés
       List<Anime> animeList =  userList.getAnimes();

       if(animeList.size() == 0 ){
           return null;
       }

        //dans liste animé est ce que anime avec idAnime passer existe

      Anime animeExist =   animeList.stream().filter(anime -> anime.getId() == idanime).findFirst().orElse(null);
    var  t = "null";
        //si non return nul


        //si oui delete


        //set nouvelle liste d'anime dans userlist


        //save userlist


        //return userlist



        return null ;
    }

}
