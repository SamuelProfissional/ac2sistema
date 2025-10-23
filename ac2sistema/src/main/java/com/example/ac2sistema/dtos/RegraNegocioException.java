package com.example.ac2sistema.dtos;


public class RegraNegocioException  extends RuntimeException{
    public RegraNegocioException(String mensagem){
        super(mensagem);
    }
}