package com.pet.store.test.crudtests;

import com.pet.store.test.helpers.AssertionHelpers;
import com.pet.store.test.helpers.PetOperationHelpers;
import com.pet.store.test.models.Pet;
import com.pet.store.test.testdata.ValidData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetOperationTests {

    @Test
    public void verify_Pet_Created_Successfully()
    {
        Pet testPet = ValidData.getFirstPet();

        //Create the pet
        Pet createdPet = PetOperationHelpers.createPet(testPet);

        //TestNg Asserts
        Assert.assertNotNull(createdPet);
    }

    @Test
    public void verify_Pet_Created_With_Correct_Data()
    {
        Pet testPet = ValidData.getFirstPet();

        //Create the pet by making a POST Request
        Response createPetResponse = PetOperationHelpers.createPetWithFullResponse(testPet);
        Pet createdPetFromResponse = createPetResponse.getBody().as(Pet.class);

        Assert.assertEquals(createPetResponse.statusCode(), 200);

        //Verify the pet in the create pet post request's response matches the test pet
        AssertionHelpers.assertPetsAreEqual(createdPetFromResponse, testPet);

        //Get the pet using id
        Pet createdPetFromStore = PetOperationHelpers.getPetByIdWithFullResponse(testPet.id)
                .getBody().as(Pet.class);

        //Verify the pet in the store is same as test pet
        AssertionHelpers.assertPetsAreEqual(createdPetFromStore, testPet);
    }

    @Test
    public void verify_Pet_Details_UpdateById_Are_Reflected_As_Expected()
    {
        Pet testPet = ValidData.getFirstPet();

        //Create the pet by making a POST Request
        Response createPetResponse = PetOperationHelpers.createPetWithFullResponse(testPet);

        //Assets
        Assert.assertEquals(createPetResponse.statusCode(), 200);

        //Now update the pet
        testPet.name = "Caramel";
        testPet.status = "NotAvailable";

        Response updateResponse = PetOperationHelpers.updatePetByIdWithFullResponse(testPet);
        Assert.assertEquals(updateResponse.statusCode(), 200);

        Response getPetFromStoreAfterUpdateResponse = PetOperationHelpers.getPetByIdWithFullResponse(testPet.id);
        Assert.assertEquals(getPetFromStoreAfterUpdateResponse.statusCode(), 200);

        Pet getPetFromStoreAfterUpdate = PetOperationHelpers.getPetByIdWithFullResponse(testPet.id).getBody().as(Pet.class);
        AssertionHelpers.assertPetsAreEqual(getPetFromStoreAfterUpdate, testPet);
    }

    @Test
    public void verify_Pet_Deleted_By_Id_No_Longer_Exists_In_The_Store()
    {
        //a valid pet
        Pet testPet = ValidData.getFirstPet();

        //Create the pet by making a POST Request
        Response createPetResponse = PetOperationHelpers.createPetWithFullResponse(testPet);
        Assert.assertEquals(createPetResponse.statusCode(), 200);

        //Delete the pet by id
        Response deletePetResponse = PetOperationHelpers.deletePetByIdWithFullResponse(testPet.id);
        Assert.assertEquals(deletePetResponse.statusCode(), 200);

        //Get from store to check it doesn't exist any more
        Response getPetFromStoreAfterDelete = PetOperationHelpers.getPetByIdWithFullResponse(testPet.id);
        Assert.assertEquals(getPetFromStoreAfterDelete.statusCode(), 404); // as pet not found
    }
}
