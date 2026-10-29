package com.parabank.page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterPage {
    public String firstNameTxt = "#customer\\.firstName";
    public String lastNameTxt = "#customer\\.lastName";
    public String addressStreetTxt = "#customer\\.address\\.street";
    public String addressCityTxt = "#customer\\.address\\.city";
    public String addressStateTxt = "#customer\\.address\\.state";
    public String addressZipCodeTxt = "#customer\\.address\\.zipCode";
    public String phoneNumberTxt = "#customer\\.phoneNumber";
    public String ssnTxt = "#customer\\.ssn";

    public String usernameTxt = "#customer\\.username";
    public String passwordTxt = "#customer\\.password";
    public String repeatedPasswordTxt = "#repeatedPassword";
    public String registerBtn = "input.button[value='Register']";
}