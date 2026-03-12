# Exercitando TDD (Gerenciador de Voos)

Na aplicação da metodologia Test-Driven Development (TDD), o ciclo estrito de Red -> Green -> Refactor deve ser respeitado para garantir a integridade das novas regras de negócio solicitadas.

## Parte A: Impedir a Adição de Passageiros Duplicados

**1. Fase Red (Escrevendo o Teste que Falha):**
Primeiro, criamos um teste para evidenciar a falha estrutural do sistema que atualmente permite duplicados na lista.

```java
@Test
@DisplayName("Garante que o sistema bloqueia o duplo embarque do mesmo passageiro")
public void deveBloquearEmbarqueDuploNoVoo() {
    // Setup
    Flight vooComercial = new EconomyFlight("Voo-TAP100");
    Passenger passageiro = new Passenger("Guto", false);

    // Exercise
    vooComercial.addPassenger(passageiro);
    boolean segundaTentativa = vooComercial.addPassenger(passageiro);

    // Verify
    assertAll(
        () -> assertFalse(segundaTentativa, "O sistema não deve retornar true numa duplicação"),
        () -> assertEquals(1, vooComercial.getPassengers().size(), "A lista não pode conter 2 registos iguais")
    );
}
```

**2. Fase Green (Implementando a Solução Mínima):**
O código funcional é agora adaptado nas classes `EconomyFlight` e `BusinessFlight` para satisfazer o teste, utilizando o método nativo `.contains()` das coleções Java.

```java
// Dentro das classes de voo (Economy e Business):
@Override
public boolean addPassenger(Passenger passenger) {
    if (passengers.contains(passenger)) {
        return false; // Regra Antifraude: Bloqueia duplicados
    }
    // Lógica normal de adição...
    return passengers.add(passenger);
}
```

---

## Parte B: Limite de Capacidade do Voo

**1. Fase Red (Teste de Lotação Máxima):**
Escrevemos um teste para instanciar um voo com uma capacidade rigorosa e tentamos ultrapassar esse limite estabelecido.

```java
@Test
@DisplayName("Impede a venda de bilhetes caso o voo atinja a lotação máxima")
public void deveRespeitarLotacaoMaximaDoVoo() {
    // Setup: Voo criado com capacidade máxima de 2 lugares
    Flight vooLotado = new EconomyFlight("Voo-TAP200", 2);
    Passenger passageiro1 = new Passenger("Ana", false);
    Passenger passageiro2 = new Passenger("Carlos", false);
    Passenger passageiroExcedente = new Passenger("Guto", false);

    // Exercise
    vooLotado.addPassenger(passageiro1);
    vooLotado.addPassenger(passageiro2);

    // Verify: O terceiro passageiro deve ser rejeitado no embarque
    assertFalse(vooLotado.addPassenger(passageiroExcedente));
    assertEquals(2, vooLotado.getPassengers().size());
}
```

**2. Fase Green (Aplicando o Limite no SUT):**
Adaptamos a assinatura da classe mãe para receber a capacidade e validamos no método de inserção.

```java
// Refatorização no código de produção:
@Override
public boolean addPassenger(Passenger passenger) {
    // Regra 1: Valida Lotação
    if (passengers.size() >= getCapacity()) {
        return false; // Voo cheio
    }
    
    // Regra 2: Valida Duplicação
    if (passengers.contains(passenger)) {
        return false; 
    }
    
    return passengers.add(passenger);
}
```