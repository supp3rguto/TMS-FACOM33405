package com.hellotests;

public class HelloJUnitTest {

    public static void main(String[] args) {
        HelloJUnit saudacaoTeste = new HelloJUnit();

        try {
            // Executando a rotina principal
            String retorno = saudacaoTeste.sayHello();

            // Validação customizada do retorno
            if ("Hello, Worlds to Tests!".equals(retorno)) {
                System.out.println("Hello, Worlds to Tests!");
            } else {
                System.out.println("Ocorreu uma falha na validação. Retorno obtido: " + retorno);
            }

        } catch (Exception ex) {
            System.out.println("Exceção disparada na execução do teste:");
            ex.printStackTrace();
        }
    }
}