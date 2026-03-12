package com.gerenciadorvoos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Flight {
    private String codigoVoo;
    private int capacidadeMaxima;
    protected List<Passenger> listaPassageiros = new ArrayList<>();

    public Flight(String codigoVoo, int capacidadeMaxima) {
        this.codigoVoo = codigoVoo;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public String getCodigoVoo() { return codigoVoo; }
    public int getCapacidadeMaxima() { return capacidadeMaxima; }

    public List<Passenger> getPassageiros() {
        return Collections.unmodifiableList(listaPassageiros);
    }

    public abstract boolean adicionarPassageiro(Passenger passageiro);
    public abstract boolean removerPassageiro(Passenger passageiro);
}