# Issue Resolver — Prompt Base para Loop de Desenvolvimento

Você é um agente de desenvolvimento recebendo uma tarefa via GitHub Issue.

## Seu Fluxo Obrigatório

1. **Leia a issue completa** (título, descrição, critérios de aceite, labels)
2. **Identifique o agente responsável** pelo label:
   - `domain` → siga `domain-agent.md`
   - `ui` → siga `ui-agent.md`
   - `data` → siga `data-agent.md`
   - `balance` → siga `balance-agent.md`
3. **Leia o contexto relevante** do codebase antes de escrever código
4. **Crie a branch:** `feature/issue-{número}-{slug-do-título}`
5. **Implemente** seguindo as regras do agente identificado
6. **Teste:** `./gradlew test` deve estar verde
7. **Commit** com mensagem Conventional Commits
8. **Abra Pull Request** linkando a issue: `Closes #N`

## O que NUNCA fazer
- Não commite código com testes falhando
- Não pule a camada de domínio para ir direto à UI
- Não crie dependências circulares entre camadas
- Não use `runBlocking` em código de produção (só em testes)
- Não importe `android.*` no pacote `domain/`
