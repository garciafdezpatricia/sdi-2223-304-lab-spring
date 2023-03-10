package com.uniovi.sdi2223304spring1.pageobjects;

import com.uniovi.sdi2223304spring1.util.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PO_PrivateView extends PO_NavView {

    static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {
        //Esperamos 5 segundos a que carge el DOM porque en algunos equipos falla
        SeleniumUtils.waitSeconds(driver, 5);
        //Seleccionamos el alumnos userOrder
        new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
        //Rellenamos el campo de descripción
        WebElement description = driver.findElement(By.name("description"));
        description.clear();
        description.sendKeys(descriptionp);
        WebElement score = driver.findElement(By.name("score"));
        score.click();
        score.clear();
        score.sendKeys(scorep);
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }

    /**
     *
     * @param driver
     * @param professor
     * @param password
     */
    static public void enterPrivateUserPage(WebDriver driver, String professor, String password){
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, professor, password);
        //Comprobamos que entramos en la página privada del Profesor
        PO_View.checkElementBy(driver, "text", professor);
    }

    /**
     *
     * @param driver
     */
    static public void checkLogout(WebDriver driver){
        //Ahora nos desconectamos comprobamas que aparece el menu de registrarse
        String loginText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);
    }

    /**
     *
     * @param driver
     * @param text
     * @param page
     */
    static public void waitAndClick(WebDriver driver, String text, int page) {
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", text);
        elements.get(page).click();
    }
}
