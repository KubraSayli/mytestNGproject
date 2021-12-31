package com.techproed.tests;


import org.testng.annotations.Test;

public class DependsOnMethods {

    //Login->homePage->Search->results
    //HomePage testcase depends on login
    @Test(priority = 1)
    public void login(){
        System.out.println("Login Test");
       // Assert.assertTrue(false);//failing the code on purpose
    }
    @Test(dependsOnMethods = "login") //homePage runs only if login successfully passes
    public void homePage(){           //it will automatically run after each other
        System.out.println("Home Page Test");
    }
    @Test(priority = 3)
    public void search(){
        System.out.println("Search Test");
    }
    @Test(priority = 4)
    public void resultTest(){
        System.out.println("Result Test");
    }

    @Test
    public void zheckOut(){ //if you don't write priority it runs first
        System.out.println("Z Test");
    }


}
