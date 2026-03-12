package com.gerenciadorvoos;

public class BusinessFlight extends Flight {

    public BusinessFlight(String codigoVoo, int capacidadeMaxima) {
        super(codigoVoo, capacidadeMaxima);
    }

    @Override
    public boolean adicionarPassageiro(Passenger passageiro) {
        if (listaPassageiros.size() >= getCapacidadeMaxima()) return false;
        if (listaPassageiros.contains(passageiro)) return false;

        if (passageiro.isVip()) {
            return listaPassageiros.add(passageiro);
        }
        return false;
    }

    @Override
    public boolean removerPassageiro(Passenger passageiro) {
        return false; // Executivos não podem ser removidos nesta regra de negócio
    }
}