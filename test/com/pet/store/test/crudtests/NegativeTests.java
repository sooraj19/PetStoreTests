package com.pet.store.test.crudtests;

import com.pet.store.test.helpers.PetOperationHelpers;
import com.pet.store.test.models.ApiResponse;
import com.pet.store.test.models.Pet;
import com.pet.store.test.testdata.ValidData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {

    @Test
    public void verify_Get_Pet_For_Invalid_PetId_Returns_404()
    {
        Pet testPet = ValidData.getFirstPet();
        testPet.id = 999; //make the request invalid as 999 doesn't (hopefully) exist

        //to ensure pet with id 999 doesn't exist
        PetOperationHelpers.deletePetByIdWithFullResponse(testPet.id);

        //Create the pet
        Response getPetResponse = PetOperationHelpers.getPetByIdWithFullResponse(testPet.id);
        Assert.assertEquals(getPetResponse.statusCode(), 404);

        //validate the API response
        ApiResponse getPetApiResponse = getPetResponse.getBody().as(ApiResponse.class);

        Assert.assertEquals(getPetApiResponse.code, 1);
        Assert.assertEquals(getPetApiResponse.type, "error");
        Assert.assertEquals(getPetApiResponse.message, "Pet not found");
    }
}
