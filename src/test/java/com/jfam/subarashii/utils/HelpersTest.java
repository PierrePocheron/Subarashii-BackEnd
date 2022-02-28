package com.jfam.subarashii.utils;


import com.google.gson.JsonArray;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class HelpersTest {


    @ParameterizedTest(name = "La phrase {0} coupé a partir du mot: {1} donne: {2}")
    @CsvSource({
            "The simple sentence with word to skip,' to skip',The simple sentence with word",
            "Duplicate entry 'test@test.fr' for key 'UK_6dotkott2kjsp8vw4d0m25fb7',' for',Duplicate entry 'test@test.fr'",
    })
    void TestSubstringBefore(String sentence , String wordKey, String result){
        // given

        //when
        String textSkip= Helpers.SubstringBefore(sentence, wordKey);

        //then

        Assertions.assertThat(textSkip).isEqualTo(result);
    }

    @Test
    void JsonArrayToList() {
        //GIVEN
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(1);
        jsonArray.add(2);
        jsonArray.add(3);

        //WHEN
        List<Integer> integerList = Helpers.JsonArrayToList(jsonArray);

        //THEN
        Assertions.assertThat(integerList).hasSize(3);
    }
}
