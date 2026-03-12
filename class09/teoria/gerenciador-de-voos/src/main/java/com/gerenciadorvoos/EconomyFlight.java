package com.gerenciadorvoos;

public class EconomyFlight extends Flight {

    public EconomyFlight(String codigoVoo, int capacidadeMaxima) {
        super(codigoVoo, capacidadeMaxima);
    }

    @Override
    public boolean adicionarPassageiro(Passenger passageiro) {
        if (listaPassageiros.size() >= getCapacidadeMaxima()) return false;
        if (listaPassageiros.contains(passageiro)) return false;

        return listaPassageiros.add(passageiro);
    }

    @Override
    public boolean removerPassageiro(Passenger passageiro) {
        if (!passageiro.isVip()) {
            return listaPassageiros.remove(passageiro);
        }
        return false;
    }
}