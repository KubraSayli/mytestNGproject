package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day10_FileExistTest extends TestBase {
    @Test
    public void isExist(){
        //Path of a file that we want to test
        String path = "C:\\Users\\Acer\\Desktop\\flower.jpg";

        //We need Java to deal with windows path
        boolean isExist = Files.exists(Paths.get(path));
        Assert.assertTrue(isExist);

    }
}
