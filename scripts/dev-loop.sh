#!/bin/bash
# dev-loop.sh — Inicia um ciclo de desenvolvimento OpenCode para uma issue
#
# Uso: bash scripts/dev-loop.sh <NÚMERO_DA_ISSUE>
#
# Pré-requisitos:
#   - opencode instalado (npm install -g opencode-ai)
#   - ANTHROPIC_API_KEY exportada
#   - GITHUB_TOKEN exportado com permissão de leitura de issues e escrita de PR

set -e

ISSUE_NUMBER="$1"

if [ -z "$ISSUE_NUMBER" ]; then
  echo "Uso: bash scripts/dev-loop.sh <NÚMERO_DA_ISSUE>"
  exit 1
fi

echo "🌿 Mito Dev Loop — Issue #$ISSUE_NUMBER"
echo "================================================"

# Busca dados da issue via GitHub CLI
ISSUE_TITLE=$(gh issue view "$ISSUE_NUMBER" --json title -q '.title')
ISSUE_BODY=$(gh issue view "$ISSUE_NUMBER" --json body -q '.body')
ISSUE_LABELS=$(gh issue view "$ISSUE_NUMBER" --json labels -q '[.labels[].name] | join(", ")')

echo "📋 Issue: $ISSUE_TITLE"
echo "🏷  Labels: $ISSUE_LABELS"
echo ""

# Monta slug para nome de branch
SLUG=$(echo "$ISSUE_TITLE" | tr '[:upper:]' '[:lower:]' | tr ' ' '-' | sed 's/[^a-z0-9-]//g' | cut -c1-40)
BRANCH="feature/issue-${ISSUE_NUMBER}-${SLUG}"

echo "🌿 Branch: $BRANCH"
git checkout -b "$BRANCH" 2>/dev/null || git checkout "$BRANCH"

# Cria prompt completo para o agente
PROMPT="Você está resolvendo a GitHub Issue #${ISSUE_NUMBER} do repositório ElioNeto/mito-card-game.\n\nTítulo: ${ISSUE_TITLE}\nLabels: ${ISSUE_LABELS}\n\nDescrição:\n${ISSUE_BODY}\n\nSiga as instruções em AGENTS.md e no prompt do agente correspondente ao label da issue."

echo ""
echo "🤖 Iniciando OpenCode..."
echo "$PROMPT" | opencode \
  --context AGENTS.md \
  --context opencode/prompts/issue-resolver.md \
  --non-interactive

echo ""
echo "✅ Ciclo finalizado para issue #$ISSUE_NUMBER"
