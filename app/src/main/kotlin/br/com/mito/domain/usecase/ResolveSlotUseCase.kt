package br.com.mito.domain.usecase

import br.com.mito.domain.entity.*

/**
 * Resolve as ações de ambos os jogadores para um índice de slot (0, 1 ou 2).
 * Retorna o novo [GameState] após a resolução.
 */
class ResolveSlotUseCase {
    operator fun invoke(state: GameState, slotIndex: Int): Result<GameState> = runCatching {
        require(slotIndex in 0..2) { "Slot index deve ser 0, 1 ou 2" }
        var newState = state
        newState = resolvePlayerSlot(newState, PlayerId.P1, slotIndex)
        newState = resolvePlayerSlot(newState, PlayerId.P2, slotIndex)
        newState
    }

    private fun resolvePlayerSlot(state: GameState, playerId: PlayerId, slotIndex: Int): GameState {
        val player = if (playerId == PlayerId.P1) state.p1 else state.p2
        val slot = player.slots.getOrNull(slotIndex) ?: return state
        return when (slot) {
            is SlotAction.BasicAction -> resolveBasicAction(state, playerId, slot)
            is SlotAction.InvokeCreature -> resolveInvoke(state, playerId, slot)
            is SlotAction.CastSpell -> state // Spell resolution delegada a SpellUseCase
        }
    }

    private fun resolveBasicAction(state: GameState, pid: PlayerId, action: SlotAction.BasicAction): GameState {
        return when (action.type) {
            BasicActionType.NARRAR -> applyNarrar(state, pid)
            BasicActionType.DEFENDER -> applyDefender(state, pid)
            BasicActionType.ATACAR, BasicActionType.INVOCAR -> state // delegado
        }
    }

    private fun applyNarrar(state: GameState, pid: PlayerId): GameState {
        val p = state.player(pid)
        val newTrad = minOf(10, p.tradicao + 1)
        return state.updatePlayer(pid, p.copy(tradicao = newTrad))
    }

    private fun applyDefender(state: GameState, pid: PlayerId): GameState {
        val p = state.player(pid)
        return state.updatePlayer(pid, p.copy(defending = true, extraDefense = 2))
    }

    private fun resolveInvoke(state: GameState, pid: PlayerId, action: SlotAction.InvokeCreature): GameState {
        val p = state.player(pid)
        val card = action.card
        if (p.tradicao < card.cost) return state // Tradição insuficiente
        val instance = CreatureInstance(
            instanceId = "${card.id}_${System.nanoTime()}",
            card = card,
            currentHp = card.maxHp,
            tired = false,
        )
        val newHand = p.hand.filter { it !is br.com.mito.domain.entity.CreatureCard || (it as br.com.mito.domain.entity.CreatureCard).id != card.id }
        val updated = p.copy(
            tradicao = p.tradicao - card.cost,
            field = p.field + instance,
            hand = newHand,
        )
        return state.updatePlayer(pid, updated)
    }
}

// Extension helpers
fun GameState.player(id: PlayerId) = if (id == PlayerId.P1) p1 else p2
fun GameState.updatePlayer(id: PlayerId, player: PlayerState) =
    if (id == PlayerId.P1) copy(p1 = player) else copy(p2 = player)
