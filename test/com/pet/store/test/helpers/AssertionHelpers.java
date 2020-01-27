package com.pet.store.test.helpers;

import com.pet.store.test.models.Pet;
import org.testng.Assert;

public class AssertionHelpers {

    public static void assertPetsAreEqual(Pet actualPet, Pet expectedPet)
    {
        Assert.assertEquals(actualPet.id, expectedPet.id, "pet.id should be same");
        Assert.assertEquals(actualPet.category.id, expectedPet.category.id, "pet.category.id should be same");
        Assert.assertEquals(actualPet.category.name, expectedPet.category.name, "pet.category.name should be same");
        Assert.assertEquals(actualPet.name, expectedPet.name, "pet.name should be same");

        Assert.assertEquals(actualPet.photoUrls.length, expectedPet.photoUrls.length, "Number of photoUrls should be same");
        for (int i = 0; i < expectedPet.photoUrls.length; i ++)
        {
            Assert.assertEquals(actualPet.photoUrls[i], expectedPet.photoUrls[i], "photo urls should match at index " + i);
        }

        Assert.assertEquals(actualPet.tags.length, expectedPet.tags.length, "Number of pet tags should be same");
        for (int i = 0; i < expectedPet.tags.length; i ++)
        {
            Assert.assertEquals(actualPet.tags[i].id, expectedPet.tags[i].id, "tag.id should match at index " + i);
            Assert.assertEquals(actualPet.tags[i].name, expectedPet.tags[i].name, "tag.name should match at index " + i);
        }

        Assert.assertEquals(actualPet.status, expectedPet.status, "pet.status should be same");
    }
}
