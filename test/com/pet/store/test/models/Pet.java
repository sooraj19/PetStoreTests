package com.pet.store.test.models;

public class Pet {
    public int id;
    public Category category;
    public String name;
    public String[] photoUrls;
    public Tag[] tags;
    public String status;

    public Pet createPet(int petId, Category petCategory, String petName, String[] petPhotoUrls, Tag[] petTags, String petStatus)
    {
        this.id = petId;
        this.category = petCategory;
        this.name = petName;
        this.photoUrls = petPhotoUrls;
        this.tags = petTags;
        this.status = petStatus;

        return  this;
    }
}
