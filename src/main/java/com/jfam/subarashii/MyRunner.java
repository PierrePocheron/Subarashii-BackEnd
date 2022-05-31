package com.jfam.subarashii;

import com.jfam.subarashii.services.GenreService;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger loggers = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    GenreService genreService;

    @Override
    public void run(String... args) throws Exception {
        genreService.getAllStartApplication();

        loggers.error(String.format(Constantes.STARTMESSAGE, Helpers.getDateNow(), Constantes.ENVIRONNEMENT_TYPE));
        loggers.info(String.format(Constantes.STARTMESSAGE, Helpers.getDateNow(), Constantes.ENVIRONNEMENT_TYPE));
    }
}
