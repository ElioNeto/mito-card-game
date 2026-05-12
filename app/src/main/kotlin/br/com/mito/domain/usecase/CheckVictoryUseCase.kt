package br.com.mito.domain.usecase

import br.com.mito.domain.entity.GameState
import br.com.mito.domain.entity.PlayerId

/** Resultado de verificação de vitória. */
sealed interface VictoryResult {
    data object NoVictory : VictoryResult
    data class Victory(val winner: PlayerId, val reason: VictoryReason) : VictoryResult
}

enum class VictoryReason { COMBAT, TRADICAO }

/**
 * Verifica se algum jogador atingiu uma condição de vitória.
 * Ordem: Tradição primeiro (vitória imediata), depois Encantamento.
 */
class CheckVictoryUseCase {
    operator fun invoke(state: GameState): VictoryResult {
        // Vitória por Tradição (imediata)
        if (state.p1.tradicao >= 10) return VictoryResult.Victory(PlayerId.P1, VictoryReason.TRADICAO)
        if (state.p2.tradicao >= 10) return VictoryResult.Victory(PlayerId.P2, VictoryReason.TRADICAO)
        // Vitória por Combate
        if (state.p2.encantamento <= 0) return VictoryResult.Victory(PlayerId.P1, VictoryReason.COMBAT)
        if (state.p1.encantamento <= 0) return VictoryResult.Victory(PlayerId.P2, VictoryReason.COMBAT)
        return VictoryResult.NoVictory
    }
}
