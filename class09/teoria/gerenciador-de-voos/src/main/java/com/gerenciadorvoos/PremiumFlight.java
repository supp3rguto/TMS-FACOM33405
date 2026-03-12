package com.gerenciadorvoos;

public class PremiumFlight extends Flight {

    public PremiumFlight(String codigoVoo, int capacidadeMaxima) {
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
        if (passageiro.isVip()) {
            return listaPassageiros.remove(passageiro);
        }
        return false;
    }
}