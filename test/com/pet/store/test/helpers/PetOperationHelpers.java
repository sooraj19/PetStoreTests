package com.pet.store.test.helpers;

import com.pet.store.test.models.Pet;
import com.pet.store.test.testdata.ValidData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetOperationHelpers {

    public static Pet createPet(Pet pet)
    {
        Pet createdPet = null;
        try {

            //RestAssured method for making the request
            RestAssured.baseURI = ValidData.BASE_URI;
            Response response = given().
                    body(pet).contentType(ContentType.JSON).
                    when().
                    post().
                    then().
                    extract().response();

            createdPet =  response.getBody().as(Pet.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return  createdPet;
    }

    //Return the Response so that other API aspects like Status, Headers, Body, etc can also be validated
    public static Response createPetWithFullResponse(Pet pet)
    {
        Response response = null;
        try {
            //RestAssured method for making the request
            RestAssured.baseURI = ValidData.BASE_URI;

            response = given().
                    body(pet).contentType(ContentType.JSON).log().all().
                    when().
                    post().
                    then().
                    extract().response();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  response;
    }

    public static Response updatePetByIdWithFullResponse(Pet pet)
    {
        Response response = null;
        try {
            //RestAssured method for making the request
            RestAssured.baseURI = ValidData.BASE_URI;

            response = given().
                    queryParam("name", pet.name).
                    queryParam("status", pet.status).
                    when().
                    post("/" + pet.id).
                    then().
                    extract().response();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  response;
    }

    public static Response getPetByIdWithFullResponse(int petId)
    {
        Response response = null;
        try {
            //RestAssured method for making the request
            RestAssured.baseURI = ValidData.BASE_URI;

            response = given().
                    when().log().all().
                    get("/" + petId).
                    then().
                    extract().response();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  response;
    }

    public static Response deletePetByIdWithFullResponse(int petId)
    {
        Response response = null;
        try {
            //RestAssured method for making the request
            RestAssured.baseURI = ValidData.BASE_URI;

            response = given().
                    header("api_key", "special-key").
                    when().
                    delete("/" + petId).
                    then().
                    extract().response();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  response;
    }

}
