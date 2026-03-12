package com.bingo;

import com.bingo.impl.BingoImpl;
import com.bingo.interfaces.Caller;
import com.bingo.interfaces.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BingoTest {

    @Mock
    private Caller callerMock;

    @Mock
    private Card cartaoMock1;

    @Mock
    private Card cartaoMock2;

    @Test
    @DisplayName("Deve coordenar a rodada, solicitar numero ao Caller e verificar vencedores")
    void testarRodadaComVencedor() {
        // arrange
        BingoImpl bingo = new BingoImpl(callerMock, Arrays.asList(cartaoMock1, cartaoMock2));

        // simulacao do comportamento dos dublês
        when(callerMock.sortearNumero()).thenReturn(42);
        when(cartaoMock1.isVencedor()).thenReturn(false);
        when(cartaoMock2.isVencedor()).thenReturn(true); // O cartão 2 vai bater bingo!

        // act
        bingo.jogarRodada();

        // assert
        verify(callerMock, times(1)).sortearNumero();
        verify(cartaoMock1, times(1)).marcarNumero(42);
        verify(cartaoMock2, times(1)).marcarNumero(42);
        verify(callerMock, times(1)).anunciarVencedores(anyList());

        assertTrue(bingo.temVencedor());
    }
}