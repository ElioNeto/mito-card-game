package br.com.mito.domain.data

import br.com.mito.domain.entity.Card
import br.com.mito.domain.entity.CreatureCard
import br.com.mito.domain.entity.SpellCard
import kotlinx.coroutines.flow.Flow

/** Interface de repositório de cartas — implementada na camada data. */
interface CardRepository {
    fun getAllCreatures(): Flow<List<CreatureCard>>
    fun getAllSpells(): Flow<List<SpellCard>>
    suspend fun getCardById(id: String): Card?
    fun buildStarterDeck(): List<Card>
}
