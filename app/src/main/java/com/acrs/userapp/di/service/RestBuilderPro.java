package com.acrs.userapp.di.service;

/**
 * Created by sreelal on 6/12/17.
 */


public class RestBuilderPro {

    public static <S> S getService(Class<S> classes) {

        return ServiceGeneratorpro.createService(classes);
    }
}