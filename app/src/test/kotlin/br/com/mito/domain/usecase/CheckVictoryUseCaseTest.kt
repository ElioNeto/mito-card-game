package br.com.mito.domain.usecase

import br.com.mito.domain.entity.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CheckVictoryUseCaseTest {

    private val useCase = CheckVictoryUseCase()

    @Test
    fun `no victory when both players have normal stats`() {
        val state = GameState()
        assertEquals(VictoryResult.NoVictory, useCase(state))
    }

    @Test
    fun `p1 wins by tradicao when reaching 10`() {
        val state = GameState(p1 = PlayerState(id = PlayerId.P1, tradicao = 10))
        val result = useCase(state)
        assertEquals(VictoryResult.Victory(PlayerId.P1, VictoryReason.TRADICAO), result)
    }

    @Test
    fun `p2 wins by combat when p1 encantamento reaches 0`() {
        val state = GameState(p1 = PlayerState(id = PlayerId.P1, encantamento = 0))
        val result = useCase(state)
        assertEquals(VictoryResult.Victory(PlayerId.P2, VictoryReason.COMBAT), result)
    }

    @Test
    fun `tradicao victory takes priority over combat`() {
        val state = GameState(
            p1 = PlayerState(id = PlayerId.P1, tradicao = 10, encantamento = 0)
        )
        val result = useCase(state)
        assertEquals(VictoryReason.TRADICAO, (result as VictoryResult.Victory).reason)
    }
}
