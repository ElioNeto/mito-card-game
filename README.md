# 🌿 Mito: O Poder da Tradição

> Card game mobile para Android inspirado no folclore brasileiro. Invoque criaturas míticas acumulando **Tradição** — a força das histórias contadas ao redor do fogo.

## Stack
- **Linguagem:** Kotlin
- **UI:** Jetpack Compose
- **Arquitetura:** MVVM + Clean Architecture
- **DI:** Hilt
- **Persistência:** Room
- **Testes:** JUnit5 + Turbine + Compose UI Test

## Estrutura do Projeto
```
app/
  src/
    main/
      kotlin/br/com/mito/
        core/        # DI, extensões, utilitários
        data/        # Room, repositórios, modelos de dados
        domain/      # Entidades, casos de uso, regras de jogo
        ui/
          theme/     # Design system (cores, tipografia, formas)
          components/ # Composables reutilizáveis (cards, slots, etc.)
          screens/   # Telas: Title, Setup, Game, Victory
          viewmodel/ # GameViewModel, SetupViewModel
```

## Mecânicas Principais
- 🎴 **Slots de Ação (1–3):** Planejamento secreto simultâneo
- ✨ **Tradição:** Recurso principal (máx. 10). Acumular 10 = vitória imediata
- ❤️ **Encantamento:** Pontos de vida do jogador (começa em 12)
- 🌿 **12 Criaturas do Folclore:** Caipora, Saci, Curupira, Iara, Boitatá e mais

## Desenvolvimento com OpenCode
Veja [AGENTS.md](./AGENTS.md) para instruções dos agentes de IA e o loop de desenvolvimento.

## Como Rodar
```bash
# Pré-requisitos: Android Studio Hedgehog+ / JDK 17
git clone https://github.com/ElioNeto/mito-card-game
cd mito-card-game
./gradlew assembleDebug
```

## Issues & Roadmap
Todas as tarefas de desenvolvimento estão organizadas como GitHub Issues com labels.
Consulte a aba [Issues](https://github.com/ElioNeto/mito-card-game/issues) para o backlog completo.
