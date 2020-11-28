package com.example.sneakersandroidmobileapp;

public class SneakerModel {
    //instance variables
    //In most cases you will want to add id to the class instead of just having it exist in the database.
    private int id;
    private String brand;
    private String category;
    private String mainColour;
    private String designer;
    private String colourWay;
    private String gender;
    private String gridPicture;
    private String mainPicture;
    private String midsole;
    private String name;
    private String nickName;
    private String releaseDate;
    private int priceCents;
    private String shoeStory;
    private String upperMaterial;


    //constructors
    public SneakerModel() {
    }

    public SneakerModel(int id, String brand, String category, String mainColour, String designer,
                        String colourWay, String gender, String gridPicture, String mainPicture,
                        String midsole, String name, String nickName, String releaseDate, int priceCents,
                        String shoeStory, String upperMaterial) {
        this.id = id;
        this.brand = brand;
        this.category = category;
        this.mainColour = mainColour;
        this.designer = designer;
        this.colourWay = colourWay;
        this.gender = gender;
        this.gridPicture = gridPicture;
        this.mainPicture = mainPicture;
        this.midsole = midsole;
        this.name = name;
        this.nickName = nickName;
        this.releaseDate = releaseDate;
        this.priceCents = priceCents;
        this.shoeStory = shoeStory;
        this.upperMaterial = upperMaterial;
    }


    //toString for printing the contents of class object
    @Override
    public String toString() {
        return "SneakerModel{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", mainColour='" + mainColour + '\'' +
                ", designer='" + designer + '\'' +
                ", colourWay='" + colourWay + '\'' +
                ", gender='" + gender + '\'' +
                ", gridPicture='" + gridPicture + '\'' +
                ", mainPicture='" + mainPicture + '\'' +
                ", midsole='" + midsole + '\'' +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", priceCents=" + priceCents +
                ", shoeStory='" + shoeStory + '\'' +
                ", upperMaterial='" + upperMaterial + '\'' +
                '}';
    }


    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMainColour() {
        return mainColour;
    }

    public void setMainColour(String mainColour) {
        this.mainColour = mainColour;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getColourWay() {
        return colourWay;
    }

    public void setColourWay(String colourWay) {
        this.colourWay = colourWay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGridPicture() {
        return gridPicture;
    }

    public void setGridPicture(String gridPicture) {
        this.gridPicture = gridPicture;
    }

    public String getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(String mainPicture) {
        this.mainPicture = mainPicture;
    }

    public String getMidsole() {
        return midsole;
    }

    public void setMidsole(String midsole) {
        this.midsole = midsole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getPriceCents() {
        return priceCents;
    }

    public void setPriceCents(int priceCents) {
        this.priceCents = priceCents;
    }

    public String getShoeStory() {
        return shoeStory;
    }

    public void setShoeStory(String shoeStory) {
        this.shoeStory = shoeStory;
    }

    public String getUpperMaterial() {
        return upperMaterial;
    }

    public void setUpperMaterial(String upperMaterial) {
        this.upperMaterial = upperMaterial;
    }

}
