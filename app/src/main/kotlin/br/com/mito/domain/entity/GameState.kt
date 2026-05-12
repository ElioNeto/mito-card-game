package br.com.mito.domain.entity

/**
 * Estado imutável completo de uma partida de Mito.
 * Todas as mutações produzem uma nova cópia via [copy].
 */
data class GameState(
    val turn: Int = 1,
    val phase: Phase = Phase.PLANNING,
    val activePlayer: PlayerId = PlayerId.P1,
    val p1: PlayerState = PlayerState(id = PlayerId.P1),
    val p2: PlayerState = PlayerState(id = PlayerId.P2),
)

enum class Phase { DRAWING, PLANNING, RESOLVING, END }
enum class PlayerId { P1, P2 }

/**
 * Estado de um jogador em uma partida.
 */
data class PlayerState(
    val id: PlayerId,
    val name: String = "",
    val encantamento: Int = 12,
    val tradicao: Int = 2,
    val field: List<CreatureInstance> = emptyList(),
    val hand: List<Card> = emptyList(),
    val deck: List<Card> = emptyList(),
    val slots: List<SlotAction?> = listOf(null, null, null),
    val confirmed: Boolean = false,
    val defending: Boolean = false,
    val extraDefense: Int = 0,
)

/** Criatura em campo com estado de hp e cansaço. */
data class CreatureInstance(
    val instanceId: String,
    val card: CreatureCard,
    val currentHp: Int,
    val tired: Boolean = false,
)

/** Ação programada em um slot. */
sealed interface SlotAction {
    data class BasicAction(val type: BasicActionType) : SlotAction
    data class InvokeCreature(val card: CreatureCard) : SlotAction
    data class CastSpell(val card: SpellCard) : SlotAction
}

enum class BasicActionType { NARRAR, ATACAR, DEFENDER, INVOCAR }
