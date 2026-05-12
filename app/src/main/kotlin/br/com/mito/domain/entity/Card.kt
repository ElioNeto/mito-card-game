package br.com.mito.domain.entity

/** Base para todas as cartas do jogo. */
sealed interface Card {
    val id: String
    val name: String
    val emoji: String
    val cost: Int
    val flavorText: String
}

/** Carta de criatura do folclore brasileiro. */
data class CreatureCard(
    override val id: String,
    override val name: String,
    override val emoji: String,
    override val cost: Int,
    override val flavorText: String = "",
    val baseAtk: Int,
    val maxHp: Int,
    val abilityDescription: String,
    val abilityType: AbilityType,
    val abilityCost: Int = 0,
) : Card

/** Carta de feitiço. */
data class SpellCard(
    override val id: String,
    override val name: String,
    override val emoji: String,
    override val cost: Int,
    override val flavorText: String = "",
    val effectDescription: String,
    val spellEffect: SpellEffect,
) : Card

enum class AbilityType {
    NONE, PASSIVE, ACTIVE, ON_INVOKE, ON_ATTACK, ON_ATTACK_PLAYER, ON_ALLY_DEATH
}

enum class SpellEffect {
    HAND_VIEW, DESTROY_WEAK, BOTH_TRAD, DRAW, DIRECT_DAMAGE, BUFF_ATK
}
